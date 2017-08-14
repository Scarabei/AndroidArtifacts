package android.support.v7.app;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.support.v7.appcompat.R.attr;
import android.support.v7.view.ActionMode;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.WindowCallbackWrapper;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.view.Window.Callback;
import java.lang.Thread.UncaughtExceptionHandler;

@RequiresApi(14)
abstract class AppCompatDelegateImplBase extends AppCompatDelegate {
   static final boolean DEBUG = false;
   private static boolean sInstalledExceptionHandler;
   private static final boolean SHOULD_INSTALL_EXCEPTION_HANDLER;
   static final String EXCEPTION_HANDLER_MESSAGE_SUFFIX = ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.";
   private static final int[] sWindowBackgroundStyleable;
   final Context mContext;
   final Window mWindow;
   final Callback mOriginalWindowCallback;
   final Callback mAppCompatWindowCallback;
   final AppCompatCallback mAppCompatCallback;
   ActionBar mActionBar;
   MenuInflater mMenuInflater;
   boolean mHasActionBar;
   boolean mOverlayActionBar;
   boolean mOverlayActionMode;
   boolean mIsFloating;
   boolean mWindowNoTitle;
   private CharSequence mTitle;
   private boolean mIsStarted;
   private boolean mIsDestroyed;
   private boolean mEatKeyUpEvent;

   AppCompatDelegateImplBase(Context context, Window window, AppCompatCallback callback) {
      this.mContext = context;
      this.mWindow = window;
      this.mAppCompatCallback = callback;
      this.mOriginalWindowCallback = this.mWindow.getCallback();
      if (this.mOriginalWindowCallback instanceof AppCompatDelegateImplBase.AppCompatWindowCallbackBase) {
         throw new IllegalStateException("AppCompat has already installed itself into the Window");
      } else {
         this.mAppCompatWindowCallback = this.wrapWindowCallback(this.mOriginalWindowCallback);
         this.mWindow.setCallback(this.mAppCompatWindowCallback);
         TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, (AttributeSet)null, sWindowBackgroundStyleable);
         Drawable winBg = a.getDrawableIfKnown(0);
         if (winBg != null) {
            this.mWindow.setBackgroundDrawable(winBg);
         }

         a.recycle();
      }
   }

   abstract void initWindowDecorActionBar();

   Callback wrapWindowCallback(Callback callback) {
      return new AppCompatDelegateImplBase.AppCompatWindowCallbackBase(callback);
   }

   public ActionBar getSupportActionBar() {
      this.initWindowDecorActionBar();
      return this.mActionBar;
   }

   final ActionBar peekSupportActionBar() {
      return this.mActionBar;
   }

   public MenuInflater getMenuInflater() {
      if (this.mMenuInflater == null) {
         this.initWindowDecorActionBar();
         this.mMenuInflater = new SupportMenuInflater(this.mActionBar != null ? this.mActionBar.getThemedContext() : this.mContext);
      }

      return this.mMenuInflater;
   }

   abstract void onPanelClosed(int var1, Menu var2);

   abstract boolean onMenuOpened(int var1, Menu var2);

   abstract boolean dispatchKeyEvent(KeyEvent var1);

   abstract boolean onKeyShortcut(int var1, KeyEvent var2);

   public void setLocalNightMode(int mode) {
   }

   public final ActionBarDrawerToggle.Delegate getDrawerToggleDelegate() {
      return new AppCompatDelegateImplBase.ActionBarDrawableToggleImpl();
   }

   final Context getActionBarThemedContext() {
      Context context = null;
      ActionBar ab = this.getSupportActionBar();
      if (ab != null) {
         context = ab.getThemedContext();
      }

      if (context == null) {
         context = this.mContext;
      }

      return context;
   }

   abstract ActionMode startSupportActionModeFromWindow(ActionMode.Callback var1);

   public void onStart() {
      this.mIsStarted = true;
   }

   public void onStop() {
      this.mIsStarted = false;
   }

   public void onDestroy() {
      this.mIsDestroyed = true;
   }

   public void setHandleNativeActionModesEnabled(boolean enabled) {
   }

   public boolean isHandleNativeActionModesEnabled() {
      return false;
   }

   public boolean applyDayNight() {
      return false;
   }

   final boolean isDestroyed() {
      return this.mIsDestroyed;
   }

   final boolean isStarted() {
      return this.mIsStarted;
   }

   final Callback getWindowCallback() {
      return this.mWindow.getCallback();
   }

   public final void setTitle(CharSequence title) {
      this.mTitle = title;
      this.onTitleChanged(title);
   }

   public void onSaveInstanceState(Bundle outState) {
   }

   abstract void onTitleChanged(CharSequence var1);

   final CharSequence getTitle() {
      return this.mOriginalWindowCallback instanceof Activity ? ((Activity)this.mOriginalWindowCallback).getTitle() : this.mTitle;
   }

   static {
      SHOULD_INSTALL_EXCEPTION_HANDLER = VERSION.SDK_INT < 21;
      if (SHOULD_INSTALL_EXCEPTION_HANDLER && !sInstalledExceptionHandler) {
         final UncaughtExceptionHandler defHandler = Thread.getDefaultUncaughtExceptionHandler();
         Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            public void uncaughtException(Thread thread, Throwable thowable) {
               if (this.shouldWrapException(thowable)) {
                  Throwable wrapped = new NotFoundException(thowable.getMessage() + ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.");
                  wrapped.initCause(thowable.getCause());
                  wrapped.setStackTrace(thowable.getStackTrace());
                  defHandler.uncaughtException(thread, wrapped);
               } else {
                  defHandler.uncaughtException(thread, thowable);
               }

            }

            private boolean shouldWrapException(Throwable throwable) {
               if (!(throwable instanceof NotFoundException)) {
                  return false;
               } else {
                  String message = throwable.getMessage();
                  return message != null && (message.contains("drawable") || message.contains("Drawable"));
               }
            }
         });
         sInstalledExceptionHandler = true;
      }

      sWindowBackgroundStyleable = new int[]{16842836};
   }

   class AppCompatWindowCallbackBase extends WindowCallbackWrapper {
      AppCompatWindowCallbackBase(Callback callback) {
         super(callback);
      }

      public boolean dispatchKeyEvent(KeyEvent event) {
         return AppCompatDelegateImplBase.this.dispatchKeyEvent(event) || super.dispatchKeyEvent(event);
      }

      public boolean dispatchKeyShortcutEvent(KeyEvent event) {
         return super.dispatchKeyShortcutEvent(event) || AppCompatDelegateImplBase.this.onKeyShortcut(event.getKeyCode(), event);
      }

      public boolean onCreatePanelMenu(int featureId, Menu menu) {
         return featureId == 0 && !(menu instanceof MenuBuilder) ? false : super.onCreatePanelMenu(featureId, menu);
      }

      public void onContentChanged() {
      }

      public boolean onPreparePanel(int featureId, View view, Menu menu) {
         MenuBuilder mb = menu instanceof MenuBuilder ? (MenuBuilder)menu : null;
         if (featureId == 0 && mb == null) {
            return false;
         } else {
            if (mb != null) {
               mb.setOverrideVisibleItems(true);
            }

            boolean handled = super.onPreparePanel(featureId, view, menu);
            if (mb != null) {
               mb.setOverrideVisibleItems(false);
            }

            return handled;
         }
      }

      public boolean onMenuOpened(int featureId, Menu menu) {
         super.onMenuOpened(featureId, menu);
         AppCompatDelegateImplBase.this.onMenuOpened(featureId, menu);
         return true;
      }

      public void onPanelClosed(int featureId, Menu menu) {
         super.onPanelClosed(featureId, menu);
         AppCompatDelegateImplBase.this.onPanelClosed(featureId, menu);
      }
   }

   private class ActionBarDrawableToggleImpl implements ActionBarDrawerToggle.Delegate {
      public Drawable getThemeUpIndicator() {
         TintTypedArray a = TintTypedArray.obtainStyledAttributes(this.getActionBarThemedContext(), (AttributeSet)null, new int[]{attr.homeAsUpIndicator});
         Drawable result = a.getDrawable(0);
         a.recycle();
         return result;
      }

      public Context getActionBarThemedContext() {
         return AppCompatDelegateImplBase.this.getActionBarThemedContext();
      }

      public boolean isNavigationVisible() {
         ActionBar ab = AppCompatDelegateImplBase.this.getSupportActionBar();
         return ab != null && (ab.getDisplayOptions() & 4) != 0;
      }

      public void setActionBarUpIndicator(Drawable upDrawable, int contentDescRes) {
         ActionBar ab = AppCompatDelegateImplBase.this.getSupportActionBar();
         if (ab != null) {
            ab.setHomeAsUpIndicator(upDrawable);
            ab.setHomeActionContentDescription(contentDescRes);
         }

      }

      public void setActionBarDescription(int contentDescRes) {
         ActionBar ab = AppCompatDelegateImplBase.this.getSupportActionBar();
         if (ab != null) {
            ab.setHomeActionContentDescription(contentDescRes);
         }

      }
   }
}
