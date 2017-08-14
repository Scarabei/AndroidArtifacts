package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.content.res.Resources.Theme;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Build.VERSION;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NavUtils;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.color;
import android.support.v7.appcompat.R.id;
import android.support.v7.appcompat.R.layout;
import android.support.v7.appcompat.R.style;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.view.ActionMode;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.view.StandaloneActionMode;
import android.support.v7.view.menu.ListMenuPresenter;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.AppCompatDrawableManager;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.DecorContentParent;
import android.support.v7.widget.FitWindowsViewGroup;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.VectorEnabledTintResources;
import android.support.v7.widget.ViewStubCompat;
import android.support.v7.widget.ViewUtils;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.view.LayoutInflater.Factory;
import android.view.LayoutInflater.Factory2;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window.Callback;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import org.xmlpull.v1.XmlPullParser;

@RequiresApi(14)
class AppCompatDelegateImplV9 extends AppCompatDelegateImplBase implements MenuBuilder.Callback, Factory2 {
   private static final boolean IS_PRE_LOLLIPOP;
   private DecorContentParent mDecorContentParent;
   private AppCompatDelegateImplV9.ActionMenuPresenterCallback mActionMenuPresenterCallback;
   private AppCompatDelegateImplV9.PanelMenuPresenterCallback mPanelMenuPresenterCallback;
   ActionMode mActionMode;
   ActionBarContextView mActionModeView;
   PopupWindow mActionModePopup;
   Runnable mShowActionModePopup;
   ViewPropertyAnimatorCompat mFadeAnim = null;
   private boolean mSubDecorInstalled;
   private ViewGroup mSubDecor;
   private TextView mTitleView;
   private View mStatusGuard;
   private boolean mFeatureProgress;
   private boolean mFeatureIndeterminateProgress;
   private boolean mClosingActionMenu;
   private AppCompatDelegateImplV9.PanelFeatureState[] mPanels;
   private AppCompatDelegateImplV9.PanelFeatureState mPreparedPanel;
   private boolean mLongPressBackDown;
   boolean mInvalidatePanelMenuPosted;
   int mInvalidatePanelMenuFeatures;
   private final Runnable mInvalidatePanelMenuRunnable = new Runnable() {
      public void run() {
         if ((AppCompatDelegateImplV9.this.mInvalidatePanelMenuFeatures & 1) != 0) {
            AppCompatDelegateImplV9.this.doInvalidatePanelMenu(0);
         }

         if ((AppCompatDelegateImplV9.this.mInvalidatePanelMenuFeatures & 4096) != 0) {
            AppCompatDelegateImplV9.this.doInvalidatePanelMenu(108);
         }

         AppCompatDelegateImplV9.this.mInvalidatePanelMenuPosted = false;
         AppCompatDelegateImplV9.this.mInvalidatePanelMenuFeatures = 0;
      }
   };
   private boolean mEnableDefaultActionBarUp;
   private Rect mTempRect1;
   private Rect mTempRect2;
   private AppCompatViewInflater mAppCompatViewInflater;

   AppCompatDelegateImplV9(Context context, Window window, AppCompatCallback callback) {
      super(context, window, callback);
   }

   public void onCreate(Bundle savedInstanceState) {
      if (this.mOriginalWindowCallback instanceof Activity && NavUtils.getParentActivityName((Activity)this.mOriginalWindowCallback) != null) {
         ActionBar ab = this.peekSupportActionBar();
         if (ab == null) {
            this.mEnableDefaultActionBarUp = true;
         } else {
            ab.setDefaultDisplayHomeAsUpEnabled(true);
         }
      }

   }

   public void onPostCreate(Bundle savedInstanceState) {
      this.ensureSubDecor();
   }

   public void initWindowDecorActionBar() {
      this.ensureSubDecor();
      if (this.mHasActionBar && this.mActionBar == null) {
         if (this.mOriginalWindowCallback instanceof Activity) {
            this.mActionBar = new WindowDecorActionBar((Activity)this.mOriginalWindowCallback, this.mOverlayActionBar);
         } else if (this.mOriginalWindowCallback instanceof Dialog) {
            this.mActionBar = new WindowDecorActionBar((Dialog)this.mOriginalWindowCallback);
         }

         if (this.mActionBar != null) {
            this.mActionBar.setDefaultDisplayHomeAsUpEnabled(this.mEnableDefaultActionBarUp);
         }

      }
   }

   public void setSupportActionBar(Toolbar toolbar) {
      if (this.mOriginalWindowCallback instanceof Activity) {
         ActionBar ab = this.getSupportActionBar();
         if (ab instanceof WindowDecorActionBar) {
            throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
         } else {
            this.mMenuInflater = null;
            if (ab != null) {
               ab.onDestroy();
            }

            if (toolbar != null) {
               ToolbarActionBar tbab = new ToolbarActionBar(toolbar, ((Activity)this.mOriginalWindowCallback).getTitle(), this.mAppCompatWindowCallback);
               this.mActionBar = tbab;
               this.mWindow.setCallback(tbab.getWrappedWindowCallback());
            } else {
               this.mActionBar = null;
               this.mWindow.setCallback(this.mAppCompatWindowCallback);
            }

            this.invalidateOptionsMenu();
         }
      }
   }

   @Nullable
   public View findViewById(@IdRes int id) {
      this.ensureSubDecor();
      return this.mWindow.findViewById(id);
   }

   public void onConfigurationChanged(Configuration newConfig) {
      if (this.mHasActionBar && this.mSubDecorInstalled) {
         ActionBar ab = this.getSupportActionBar();
         if (ab != null) {
            ab.onConfigurationChanged(newConfig);
         }
      }

      AppCompatDrawableManager.get().onConfigurationChanged(this.mContext);
      this.applyDayNight();
   }

   public void onStop() {
      ActionBar ab = this.getSupportActionBar();
      if (ab != null) {
         ab.setShowHideAnimationEnabled(false);
      }

   }

   public void onPostResume() {
      ActionBar ab = this.getSupportActionBar();
      if (ab != null) {
         ab.setShowHideAnimationEnabled(true);
      }

   }

   public void setContentView(View v) {
      this.ensureSubDecor();
      ViewGroup contentParent = (ViewGroup)this.mSubDecor.findViewById(16908290);
      contentParent.removeAllViews();
      contentParent.addView(v);
      this.mOriginalWindowCallback.onContentChanged();
   }

   public void setContentView(int resId) {
      this.ensureSubDecor();
      ViewGroup contentParent = (ViewGroup)this.mSubDecor.findViewById(16908290);
      contentParent.removeAllViews();
      LayoutInflater.from(this.mContext).inflate(resId, contentParent);
      this.mOriginalWindowCallback.onContentChanged();
   }

   public void setContentView(View v, LayoutParams lp) {
      this.ensureSubDecor();
      ViewGroup contentParent = (ViewGroup)this.mSubDecor.findViewById(16908290);
      contentParent.removeAllViews();
      contentParent.addView(v, lp);
      this.mOriginalWindowCallback.onContentChanged();
   }

   public void addContentView(View v, LayoutParams lp) {
      this.ensureSubDecor();
      ViewGroup contentParent = (ViewGroup)this.mSubDecor.findViewById(16908290);
      contentParent.addView(v, lp);
      this.mOriginalWindowCallback.onContentChanged();
   }

   public void onDestroy() {
      if (this.mInvalidatePanelMenuPosted) {
         this.mWindow.getDecorView().removeCallbacks(this.mInvalidatePanelMenuRunnable);
      }

      super.onDestroy();
      if (this.mActionBar != null) {
         this.mActionBar.onDestroy();
      }

   }

   private void ensureSubDecor() {
      if (!this.mSubDecorInstalled) {
         this.mSubDecor = this.createSubDecor();
         CharSequence title = this.getTitle();
         if (!TextUtils.isEmpty(title)) {
            this.onTitleChanged(title);
         }

         this.applyFixedSizeWindow();
         this.onSubDecorInstalled(this.mSubDecor);
         this.mSubDecorInstalled = true;
         AppCompatDelegateImplV9.PanelFeatureState st = this.getPanelState(0, false);
         if (!this.isDestroyed() && (st == null || st.menu == null)) {
            this.invalidatePanelMenu(108);
         }
      }

   }

   private ViewGroup createSubDecor() {
      TypedArray a = this.mContext.obtainStyledAttributes(styleable.AppCompatTheme);
      if (!a.hasValue(styleable.AppCompatTheme_windowActionBar)) {
         a.recycle();
         throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
      } else {
         if (a.getBoolean(styleable.AppCompatTheme_windowNoTitle, false)) {
            this.requestWindowFeature(1);
         } else if (a.getBoolean(styleable.AppCompatTheme_windowActionBar, false)) {
            this.requestWindowFeature(108);
         }

         if (a.getBoolean(styleable.AppCompatTheme_windowActionBarOverlay, false)) {
            this.requestWindowFeature(109);
         }

         if (a.getBoolean(styleable.AppCompatTheme_windowActionModeOverlay, false)) {
            this.requestWindowFeature(10);
         }

         this.mIsFloating = a.getBoolean(styleable.AppCompatTheme_android_windowIsFloating, false);
         a.recycle();
         this.mWindow.getDecorView();
         LayoutInflater inflater = LayoutInflater.from(this.mContext);
         ViewGroup subDecor = null;
         if (!this.mWindowNoTitle) {
            if (this.mIsFloating) {
               subDecor = (ViewGroup)inflater.inflate(layout.abc_dialog_title_material, (ViewGroup)null);
               this.mHasActionBar = this.mOverlayActionBar = false;
            } else if (this.mHasActionBar) {
               TypedValue outValue = new TypedValue();
               this.mContext.getTheme().resolveAttribute(attr.actionBarTheme, outValue, true);
               Object themedContext;
               if (outValue.resourceId != 0) {
                  themedContext = new ContextThemeWrapper(this.mContext, outValue.resourceId);
               } else {
                  themedContext = this.mContext;
               }

               subDecor = (ViewGroup)LayoutInflater.from((Context)themedContext).inflate(layout.abc_screen_toolbar, (ViewGroup)null);
               this.mDecorContentParent = (DecorContentParent)subDecor.findViewById(id.decor_content_parent);
               this.mDecorContentParent.setWindowCallback(this.getWindowCallback());
               if (this.mOverlayActionBar) {
                  this.mDecorContentParent.initFeature(109);
               }

               if (this.mFeatureProgress) {
                  this.mDecorContentParent.initFeature(2);
               }

               if (this.mFeatureIndeterminateProgress) {
                  this.mDecorContentParent.initFeature(5);
               }
            }
         } else {
            if (this.mOverlayActionMode) {
               subDecor = (ViewGroup)inflater.inflate(layout.abc_screen_simple_overlay_action_mode, (ViewGroup)null);
            } else {
               subDecor = (ViewGroup)inflater.inflate(layout.abc_screen_simple, (ViewGroup)null);
            }

            if (VERSION.SDK_INT >= 21) {
               ViewCompat.setOnApplyWindowInsetsListener(subDecor, new OnApplyWindowInsetsListener() {
                  public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                     int top = insets.getSystemWindowInsetTop();
                     int newTop = AppCompatDelegateImplV9.this.updateStatusGuard(top);
                     if (top != newTop) {
                        insets = insets.replaceSystemWindowInsets(insets.getSystemWindowInsetLeft(), newTop, insets.getSystemWindowInsetRight(), insets.getSystemWindowInsetBottom());
                     }

                     return ViewCompat.onApplyWindowInsets(v, insets);
                  }
               });
            } else {
               ((FitWindowsViewGroup)subDecor).setOnFitSystemWindowsListener(new FitWindowsViewGroup.OnFitSystemWindowsListener() {
                  public void onFitSystemWindows(Rect insets) {
                     insets.top = AppCompatDelegateImplV9.this.updateStatusGuard(insets.top);
                  }
               });
            }
         }

         if (subDecor == null) {
            throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.mHasActionBar + ", windowActionBarOverlay: " + this.mOverlayActionBar + ", android:windowIsFloating: " + this.mIsFloating + ", windowActionModeOverlay: " + this.mOverlayActionMode + ", windowNoTitle: " + this.mWindowNoTitle + " }");
         } else {
            if (this.mDecorContentParent == null) {
               this.mTitleView = (TextView)subDecor.findViewById(id.title);
            }

            ViewUtils.makeOptionalFitsSystemWindows(subDecor);
            ContentFrameLayout contentView = (ContentFrameLayout)subDecor.findViewById(id.action_bar_activity_content);
            ViewGroup windowContentView = (ViewGroup)this.mWindow.findViewById(16908290);
            if (windowContentView != null) {
               while(windowContentView.getChildCount() > 0) {
                  View child = windowContentView.getChildAt(0);
                  windowContentView.removeViewAt(0);
                  contentView.addView(child);
               }

               windowContentView.setId(-1);
               contentView.setId(16908290);
               if (windowContentView instanceof FrameLayout) {
                  ((FrameLayout)windowContentView).setForeground((Drawable)null);
               }
            }

            this.mWindow.setContentView(subDecor);
            contentView.setAttachListener(new ContentFrameLayout.OnAttachListener() {
               public void onAttachedFromWindow() {
               }

               public void onDetachedFromWindow() {
                  AppCompatDelegateImplV9.this.dismissPopups();
               }
            });
            return subDecor;
         }
      }
   }

   void onSubDecorInstalled(ViewGroup subDecor) {
   }

   private void applyFixedSizeWindow() {
      ContentFrameLayout cfl = (ContentFrameLayout)this.mSubDecor.findViewById(16908290);
      View windowDecor = this.mWindow.getDecorView();
      cfl.setDecorPadding(windowDecor.getPaddingLeft(), windowDecor.getPaddingTop(), windowDecor.getPaddingRight(), windowDecor.getPaddingBottom());
      TypedArray a = this.mContext.obtainStyledAttributes(styleable.AppCompatTheme);
      a.getValue(styleable.AppCompatTheme_windowMinWidthMajor, cfl.getMinWidthMajor());
      a.getValue(styleable.AppCompatTheme_windowMinWidthMinor, cfl.getMinWidthMinor());
      if (a.hasValue(styleable.AppCompatTheme_windowFixedWidthMajor)) {
         a.getValue(styleable.AppCompatTheme_windowFixedWidthMajor, cfl.getFixedWidthMajor());
      }

      if (a.hasValue(styleable.AppCompatTheme_windowFixedWidthMinor)) {
         a.getValue(styleable.AppCompatTheme_windowFixedWidthMinor, cfl.getFixedWidthMinor());
      }

      if (a.hasValue(styleable.AppCompatTheme_windowFixedHeightMajor)) {
         a.getValue(styleable.AppCompatTheme_windowFixedHeightMajor, cfl.getFixedHeightMajor());
      }

      if (a.hasValue(styleable.AppCompatTheme_windowFixedHeightMinor)) {
         a.getValue(styleable.AppCompatTheme_windowFixedHeightMinor, cfl.getFixedHeightMinor());
      }

      a.recycle();
      cfl.requestLayout();
   }

   public boolean requestWindowFeature(int featureId) {
      featureId = this.sanitizeWindowFeatureId(featureId);
      if (this.mWindowNoTitle && featureId == 108) {
         return false;
      } else {
         if (this.mHasActionBar && featureId == 1) {
            this.mHasActionBar = false;
         }

         switch(featureId) {
         case 1:
            this.throwFeatureRequestIfSubDecorInstalled();
            this.mWindowNoTitle = true;
            return true;
         case 2:
            this.throwFeatureRequestIfSubDecorInstalled();
            this.mFeatureProgress = true;
            return true;
         case 5:
            this.throwFeatureRequestIfSubDecorInstalled();
            this.mFeatureIndeterminateProgress = true;
            return true;
         case 10:
            this.throwFeatureRequestIfSubDecorInstalled();
            this.mOverlayActionMode = true;
            return true;
         case 108:
            this.throwFeatureRequestIfSubDecorInstalled();
            this.mHasActionBar = true;
            return true;
         case 109:
            this.throwFeatureRequestIfSubDecorInstalled();
            this.mOverlayActionBar = true;
            return true;
         default:
            return this.mWindow.requestFeature(featureId);
         }
      }
   }

   public boolean hasWindowFeature(int featureId) {
      featureId = this.sanitizeWindowFeatureId(featureId);
      switch(featureId) {
      case 1:
         return this.mWindowNoTitle;
      case 2:
         return this.mFeatureProgress;
      case 5:
         return this.mFeatureIndeterminateProgress;
      case 10:
         return this.mOverlayActionMode;
      case 108:
         return this.mHasActionBar;
      case 109:
         return this.mOverlayActionBar;
      default:
         return false;
      }
   }

   void onTitleChanged(CharSequence title) {
      if (this.mDecorContentParent != null) {
         this.mDecorContentParent.setWindowTitle(title);
      } else if (this.peekSupportActionBar() != null) {
         this.peekSupportActionBar().setWindowTitle(title);
      } else if (this.mTitleView != null) {
         this.mTitleView.setText(title);
      }

   }

   void onPanelClosed(int featureId, Menu menu) {
      if (featureId == 108) {
         ActionBar ab = this.getSupportActionBar();
         if (ab != null) {
            ab.dispatchMenuVisibilityChanged(false);
         }
      } else if (featureId == 0) {
         AppCompatDelegateImplV9.PanelFeatureState st = this.getPanelState(featureId, true);
         if (st.isOpen) {
            this.closePanel(st, false);
         }
      }

   }

   boolean onMenuOpened(int featureId, Menu menu) {
      if (featureId == 108) {
         ActionBar ab = this.getSupportActionBar();
         if (ab != null) {
            ab.dispatchMenuVisibilityChanged(true);
         }

         return true;
      } else {
         return false;
      }
   }

   public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
      Callback cb = this.getWindowCallback();
      if (cb != null && !this.isDestroyed()) {
         AppCompatDelegateImplV9.PanelFeatureState panel = this.findMenuPanel(menu.getRootMenu());
         if (panel != null) {
            return cb.onMenuItemSelected(panel.featureId, item);
         }
      }

      return false;
   }

   public void onMenuModeChange(MenuBuilder menu) {
      this.reopenMenu(menu, true);
   }

   public ActionMode startSupportActionMode(@NonNull ActionMode.Callback callback) {
      if (callback == null) {
         throw new IllegalArgumentException("ActionMode callback can not be null.");
      } else {
         if (this.mActionMode != null) {
            this.mActionMode.finish();
         }

         ActionMode.Callback wrappedCallback = new AppCompatDelegateImplV9.ActionModeCallbackWrapperV9(callback);
         ActionBar ab = this.getSupportActionBar();
         if (ab != null) {
            this.mActionMode = ab.startActionMode(wrappedCallback);
            if (this.mActionMode != null && this.mAppCompatCallback != null) {
               this.mAppCompatCallback.onSupportActionModeStarted(this.mActionMode);
            }
         }

         if (this.mActionMode == null) {
            this.mActionMode = this.startSupportActionModeFromWindow(wrappedCallback);
         }

         return this.mActionMode;
      }
   }

   public void invalidateOptionsMenu() {
      ActionBar ab = this.getSupportActionBar();
      if (ab == null || !ab.invalidateOptionsMenu()) {
         this.invalidatePanelMenu(0);
      }
   }

   ActionMode startSupportActionModeFromWindow(@NonNull ActionMode.Callback callback) {
      this.endOnGoingFadeAnimation();
      if (this.mActionMode != null) {
         this.mActionMode.finish();
      }

      if (!(callback instanceof AppCompatDelegateImplV9.ActionModeCallbackWrapperV9)) {
         callback = new AppCompatDelegateImplV9.ActionModeCallbackWrapperV9((ActionMode.Callback)callback);
      }

      ActionMode mode = null;
      if (this.mAppCompatCallback != null && !this.isDestroyed()) {
         try {
            mode = this.mAppCompatCallback.onWindowStartingSupportActionMode((ActionMode.Callback)callback);
         } catch (AbstractMethodError var7) {
            ;
         }
      }

      if (mode != null) {
         this.mActionMode = mode;
      } else {
         if (this.mActionModeView == null) {
            if (this.mIsFloating) {
               TypedValue outValue = new TypedValue();
               Theme baseTheme = this.mContext.getTheme();
               baseTheme.resolveAttribute(attr.actionBarTheme, outValue, true);
               Object actionBarContext;
               if (outValue.resourceId != 0) {
                  Theme actionBarTheme = this.mContext.getResources().newTheme();
                  actionBarTheme.setTo(baseTheme);
                  actionBarTheme.applyStyle(outValue.resourceId, true);
                  actionBarContext = new ContextThemeWrapper(this.mContext, 0);
                  ((Context)actionBarContext).getTheme().setTo(actionBarTheme);
               } else {
                  actionBarContext = this.mContext;
               }

               this.mActionModeView = new ActionBarContextView((Context)actionBarContext);
               this.mActionModePopup = new PopupWindow((Context)actionBarContext, (AttributeSet)null, attr.actionModePopupWindowStyle);
               PopupWindowCompat.setWindowLayoutType(this.mActionModePopup, 2);
               this.mActionModePopup.setContentView(this.mActionModeView);
               this.mActionModePopup.setWidth(-1);
               ((Context)actionBarContext).getTheme().resolveAttribute(attr.actionBarSize, outValue, true);
               int height = TypedValue.complexToDimensionPixelSize(outValue.data, ((Context)actionBarContext).getResources().getDisplayMetrics());
               this.mActionModeView.setContentHeight(height);
               this.mActionModePopup.setHeight(-2);
               this.mShowActionModePopup = new Runnable() {
                  public void run() {
                     AppCompatDelegateImplV9.this.mActionModePopup.showAtLocation(AppCompatDelegateImplV9.this.mActionModeView, 55, 0, 0);
                     AppCompatDelegateImplV9.this.endOnGoingFadeAnimation();
                     if (AppCompatDelegateImplV9.this.shouldAnimateActionModeView()) {
                        AppCompatDelegateImplV9.this.mActionModeView.setAlpha(0.0F);
                        AppCompatDelegateImplV9.this.mFadeAnim = ViewCompat.animate(AppCompatDelegateImplV9.this.mActionModeView).alpha(1.0F);
                        AppCompatDelegateImplV9.this.mFadeAnim.setListener(new ViewPropertyAnimatorListenerAdapter() {
                           public void onAnimationStart(View view) {
                              AppCompatDelegateImplV9.this.mActionModeView.setVisibility(0);
                           }

                           public void onAnimationEnd(View view) {
                              AppCompatDelegateImplV9.this.mActionModeView.setAlpha(1.0F);
                              AppCompatDelegateImplV9.this.mFadeAnim.setListener((ViewPropertyAnimatorListener)null);
                              AppCompatDelegateImplV9.this.mFadeAnim = null;
                           }
                        });
                     } else {
                        AppCompatDelegateImplV9.this.mActionModeView.setAlpha(1.0F);
                        AppCompatDelegateImplV9.this.mActionModeView.setVisibility(0);
                     }

                  }
               };
            } else {
               ViewStubCompat stub = (ViewStubCompat)this.mSubDecor.findViewById(id.action_mode_bar_stub);
               if (stub != null) {
                  stub.setLayoutInflater(LayoutInflater.from(this.getActionBarThemedContext()));
                  this.mActionModeView = (ActionBarContextView)stub.inflate();
               }
            }
         }

         if (this.mActionModeView != null) {
            this.endOnGoingFadeAnimation();
            this.mActionModeView.killMode();
            ActionMode mode = new StandaloneActionMode(this.mActionModeView.getContext(), this.mActionModeView, (ActionMode.Callback)callback, this.mActionModePopup == null);
            if (((ActionMode.Callback)callback).onCreateActionMode(mode, mode.getMenu())) {
               mode.invalidate();
               this.mActionModeView.initForMode(mode);
               this.mActionMode = mode;
               if (this.shouldAnimateActionModeView()) {
                  this.mActionModeView.setAlpha(0.0F);
                  this.mFadeAnim = ViewCompat.animate(this.mActionModeView).alpha(1.0F);
                  this.mFadeAnim.setListener(new ViewPropertyAnimatorListenerAdapter() {
                     public void onAnimationStart(View view) {
                        AppCompatDelegateImplV9.this.mActionModeView.setVisibility(0);
                        AppCompatDelegateImplV9.this.mActionModeView.sendAccessibilityEvent(32);
                        if (AppCompatDelegateImplV9.this.mActionModeView.getParent() instanceof View) {
                           ViewCompat.requestApplyInsets((View)AppCompatDelegateImplV9.this.mActionModeView.getParent());
                        }

                     }

                     public void onAnimationEnd(View view) {
                        AppCompatDelegateImplV9.this.mActionModeView.setAlpha(1.0F);
                        AppCompatDelegateImplV9.this.mFadeAnim.setListener((ViewPropertyAnimatorListener)null);
                        AppCompatDelegateImplV9.this.mFadeAnim = null;
                     }
                  });
               } else {
                  this.mActionModeView.setAlpha(1.0F);
                  this.mActionModeView.setVisibility(0);
                  this.mActionModeView.sendAccessibilityEvent(32);
                  if (this.mActionModeView.getParent() instanceof View) {
                     ViewCompat.requestApplyInsets((View)this.mActionModeView.getParent());
                  }
               }

               if (this.mActionModePopup != null) {
                  this.mWindow.getDecorView().post(this.mShowActionModePopup);
               }
            } else {
               this.mActionMode = null;
            }
         }
      }

      if (this.mActionMode != null && this.mAppCompatCallback != null) {
         this.mAppCompatCallback.onSupportActionModeStarted(this.mActionMode);
      }

      return this.mActionMode;
   }

   final boolean shouldAnimateActionModeView() {
      return this.mSubDecorInstalled && this.mSubDecor != null && ViewCompat.isLaidOut(this.mSubDecor);
   }

   void endOnGoingFadeAnimation() {
      if (this.mFadeAnim != null) {
         this.mFadeAnim.cancel();
      }

   }

   boolean onBackPressed() {
      if (this.mActionMode != null) {
         this.mActionMode.finish();
         return true;
      } else {
         ActionBar ab = this.getSupportActionBar();
         return ab != null && ab.collapseActionView();
      }
   }

   boolean onKeyShortcut(int keyCode, KeyEvent ev) {
      ActionBar ab = this.getSupportActionBar();
      if (ab != null && ab.onKeyShortcut(keyCode, ev)) {
         return true;
      } else {
         boolean handled;
         if (this.mPreparedPanel != null) {
            handled = this.performPanelShortcut(this.mPreparedPanel, ev.getKeyCode(), ev, 1);
            if (handled) {
               if (this.mPreparedPanel != null) {
                  this.mPreparedPanel.isHandled = true;
               }

               return true;
            }
         }

         if (this.mPreparedPanel == null) {
            AppCompatDelegateImplV9.PanelFeatureState st = this.getPanelState(0, true);
            this.preparePanel(st, ev);
            handled = this.performPanelShortcut(st, ev.getKeyCode(), ev, 1);
            st.isPrepared = false;
            if (handled) {
               return true;
            }
         }

         return false;
      }
   }

   boolean dispatchKeyEvent(KeyEvent event) {
      if (event.getKeyCode() == 82 && this.mOriginalWindowCallback.dispatchKeyEvent(event)) {
         return true;
      } else {
         int keyCode = event.getKeyCode();
         int action = event.getAction();
         boolean isDown = action == 0;
         return isDown ? this.onKeyDown(keyCode, event) : this.onKeyUp(keyCode, event);
      }
   }

   boolean onKeyUp(int keyCode, KeyEvent event) {
      switch(keyCode) {
      case 4:
         boolean wasLongPressBackDown = this.mLongPressBackDown;
         this.mLongPressBackDown = false;
         AppCompatDelegateImplV9.PanelFeatureState st = this.getPanelState(0, false);
         if (st != null && st.isOpen) {
            if (!wasLongPressBackDown) {
               this.closePanel(st, true);
            }

            return true;
         } else if (this.onBackPressed()) {
            return true;
         }
      default:
         return false;
      case 82:
         this.onKeyUpPanel(0, event);
         return true;
      }
   }

   boolean onKeyDown(int keyCode, KeyEvent event) {
      switch(keyCode) {
      case 4:
         this.mLongPressBackDown = (event.getFlags() & 128) != 0;
      default:
         if (VERSION.SDK_INT < 11) {
            this.onKeyShortcut(keyCode, event);
         }

         return false;
      case 82:
         this.onKeyDownPanel(0, event);
         return true;
      }
   }

   public View createView(View parent, String name, @NonNull Context context, @NonNull AttributeSet attrs) {
      if (this.mAppCompatViewInflater == null) {
         this.mAppCompatViewInflater = new AppCompatViewInflater();
      }

      boolean inheritContext = false;
      if (IS_PRE_LOLLIPOP) {
         inheritContext = attrs instanceof XmlPullParser ? ((XmlPullParser)attrs).getDepth() > 1 : this.shouldInheritContext((ViewParent)parent);
      }

      return this.mAppCompatViewInflater.createView(parent, name, context, attrs, inheritContext, IS_PRE_LOLLIPOP, true, VectorEnabledTintResources.shouldBeUsed());
   }

   private boolean shouldInheritContext(ViewParent parent) {
      if (parent == null) {
         return false;
      } else {
         for(View windowDecor = this.mWindow.getDecorView(); parent != null; parent = parent.getParent()) {
            if (parent == windowDecor || !(parent instanceof View) || ViewCompat.isAttachedToWindow((View)parent)) {
               return false;
            }
         }

         return true;
      }
   }

   public void installViewFactory() {
      LayoutInflater layoutInflater = LayoutInflater.from(this.mContext);
      if (layoutInflater.getFactory() == null) {
         LayoutInflaterCompat.setFactory2(layoutInflater, this);
      } else if (!(layoutInflater.getFactory2() instanceof AppCompatDelegateImplV9)) {
         Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
      }

   }

   public final View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
      View view = this.callActivityOnCreateView(parent, name, context, attrs);
      return view != null ? view : this.createView(parent, name, context, attrs);
   }

   public View onCreateView(String name, Context context, AttributeSet attrs) {
      return this.onCreateView((View)null, name, context, attrs);
   }

   View callActivityOnCreateView(View parent, String name, Context context, AttributeSet attrs) {
      if (this.mOriginalWindowCallback instanceof Factory) {
         View result = ((Factory)this.mOriginalWindowCallback).onCreateView(name, context, attrs);
         if (result != null) {
            return result;
         }
      }

      return null;
   }

   private void openPanel(AppCompatDelegateImplV9.PanelFeatureState st, KeyEvent event) {
      if (!st.isOpen && !this.isDestroyed()) {
         if (st.featureId == 0) {
            Context context = this.mContext;
            Configuration config = context.getResources().getConfiguration();
            boolean isXLarge = (config.screenLayout & 15) == 4;
            boolean isHoneycombApp = context.getApplicationInfo().targetSdkVersion >= 11;
            if (isXLarge && isHoneycombApp) {
               return;
            }
         }

         Callback cb = this.getWindowCallback();
         if (cb != null && !cb.onMenuOpened(st.featureId, st.menu)) {
            this.closePanel(st, true);
         } else {
            WindowManager wm = (WindowManager)this.mContext.getSystemService("window");
            if (wm != null) {
               if (this.preparePanel(st, event)) {
                  int width = -2;
                  LayoutParams lp;
                  if (st.decorView != null && !st.refreshDecorView) {
                     if (st.createdPanelView != null) {
                        lp = st.createdPanelView.getLayoutParams();
                        if (lp != null && lp.width == -1) {
                           width = -1;
                        }
                     }
                  } else {
                     if (st.decorView == null) {
                        if (!this.initializePanelDecor(st) || st.decorView == null) {
                           return;
                        }
                     } else if (st.refreshDecorView && st.decorView.getChildCount() > 0) {
                        st.decorView.removeAllViews();
                     }

                     if (!this.initializePanelContent(st) || !st.hasPanelItems()) {
                        return;
                     }

                     lp = st.shownPanelView.getLayoutParams();
                     if (lp == null) {
                        lp = new LayoutParams(-2, -2);
                     }

                     int backgroundResId = st.background;
                     st.decorView.setBackgroundResource(backgroundResId);
                     ViewParent shownPanelParent = st.shownPanelView.getParent();
                     if (shownPanelParent != null && shownPanelParent instanceof ViewGroup) {
                        ((ViewGroup)shownPanelParent).removeView(st.shownPanelView);
                     }

                     st.decorView.addView(st.shownPanelView, lp);
                     if (!st.shownPanelView.hasFocus()) {
                        st.shownPanelView.requestFocus();
                     }
                  }

                  st.isHandled = false;
                  android.view.WindowManager.LayoutParams lp = new android.view.WindowManager.LayoutParams(width, -2, st.x, st.y, 1002, 8519680, -3);
                  lp.gravity = st.gravity;
                  lp.windowAnimations = st.windowAnimations;
                  wm.addView(st.decorView, lp);
                  st.isOpen = true;
               }
            }
         }
      }
   }

   private boolean initializePanelDecor(AppCompatDelegateImplV9.PanelFeatureState st) {
      st.setStyle(this.getActionBarThemedContext());
      st.decorView = new AppCompatDelegateImplV9.ListMenuDecorView(st.listPresenterContext);
      st.gravity = 81;
      return true;
   }

   private void reopenMenu(MenuBuilder menu, boolean toggleMenuMode) {
      if (this.mDecorContentParent != null && this.mDecorContentParent.canShowOverflowMenu() && (!ViewConfiguration.get(this.mContext).hasPermanentMenuKey() || this.mDecorContentParent.isOverflowMenuShowPending())) {
         Callback cb = this.getWindowCallback();
         AppCompatDelegateImplV9.PanelFeatureState st;
         if (this.mDecorContentParent.isOverflowMenuShowing() && toggleMenuMode) {
            this.mDecorContentParent.hideOverflowMenu();
            if (!this.isDestroyed()) {
               st = this.getPanelState(0, true);
               cb.onPanelClosed(108, st.menu);
            }
         } else if (cb != null && !this.isDestroyed()) {
            if (this.mInvalidatePanelMenuPosted && (this.mInvalidatePanelMenuFeatures & 1) != 0) {
               this.mWindow.getDecorView().removeCallbacks(this.mInvalidatePanelMenuRunnable);
               this.mInvalidatePanelMenuRunnable.run();
            }

            st = this.getPanelState(0, true);
            if (st.menu != null && !st.refreshMenuContent && cb.onPreparePanel(0, st.createdPanelView, st.menu)) {
               cb.onMenuOpened(108, st.menu);
               this.mDecorContentParent.showOverflowMenu();
            }
         }

      } else {
         AppCompatDelegateImplV9.PanelFeatureState st = this.getPanelState(0, true);
         st.refreshDecorView = true;
         this.closePanel(st, false);
         this.openPanel(st, (KeyEvent)null);
      }
   }

   private boolean initializePanelMenu(AppCompatDelegateImplV9.PanelFeatureState st) {
      Context context = this.mContext;
      if ((st.featureId == 0 || st.featureId == 108) && this.mDecorContentParent != null) {
         TypedValue outValue = new TypedValue();
         Theme baseTheme = ((Context)context).getTheme();
         baseTheme.resolveAttribute(attr.actionBarTheme, outValue, true);
         Theme widgetTheme = null;
         if (outValue.resourceId != 0) {
            widgetTheme = ((Context)context).getResources().newTheme();
            widgetTheme.setTo(baseTheme);
            widgetTheme.applyStyle(outValue.resourceId, true);
            widgetTheme.resolveAttribute(attr.actionBarWidgetTheme, outValue, true);
         } else {
            baseTheme.resolveAttribute(attr.actionBarWidgetTheme, outValue, true);
         }

         if (outValue.resourceId != 0) {
            if (widgetTheme == null) {
               widgetTheme = ((Context)context).getResources().newTheme();
               widgetTheme.setTo(baseTheme);
            }

            widgetTheme.applyStyle(outValue.resourceId, true);
         }

         if (widgetTheme != null) {
            context = new ContextThemeWrapper((Context)context, 0);
            ((Context)context).getTheme().setTo(widgetTheme);
         }
      }

      MenuBuilder menu = new MenuBuilder((Context)context);
      menu.setCallback(this);
      st.setMenu(menu);
      return true;
   }

   private boolean initializePanelContent(AppCompatDelegateImplV9.PanelFeatureState st) {
      if (st.createdPanelView != null) {
         st.shownPanelView = st.createdPanelView;
         return true;
      } else if (st.menu == null) {
         return false;
      } else {
         if (this.mPanelMenuPresenterCallback == null) {
            this.mPanelMenuPresenterCallback = new AppCompatDelegateImplV9.PanelMenuPresenterCallback();
         }

         MenuView menuView = st.getListMenuView(this.mPanelMenuPresenterCallback);
         st.shownPanelView = (View)menuView;
         return st.shownPanelView != null;
      }
   }

   private boolean preparePanel(AppCompatDelegateImplV9.PanelFeatureState st, KeyEvent event) {
      if (this.isDestroyed()) {
         return false;
      } else if (st.isPrepared) {
         return true;
      } else {
         if (this.mPreparedPanel != null && this.mPreparedPanel != st) {
            this.closePanel(this.mPreparedPanel, false);
         }

         Callback cb = this.getWindowCallback();
         if (cb != null) {
            st.createdPanelView = cb.onCreatePanelView(st.featureId);
         }

         boolean isActionBarMenu = st.featureId == 0 || st.featureId == 108;
         if (isActionBarMenu && this.mDecorContentParent != null) {
            this.mDecorContentParent.setMenuPrepared();
         }

         if (st.createdPanelView == null && (!isActionBarMenu || !(this.peekSupportActionBar() instanceof ToolbarActionBar))) {
            if (st.menu == null || st.refreshMenuContent) {
               if (st.menu == null && (!this.initializePanelMenu(st) || st.menu == null)) {
                  return false;
               }

               if (isActionBarMenu && this.mDecorContentParent != null) {
                  if (this.mActionMenuPresenterCallback == null) {
                     this.mActionMenuPresenterCallback = new AppCompatDelegateImplV9.ActionMenuPresenterCallback();
                  }

                  this.mDecorContentParent.setMenu(st.menu, this.mActionMenuPresenterCallback);
               }

               st.menu.stopDispatchingItemsChanged();
               if (!cb.onCreatePanelMenu(st.featureId, st.menu)) {
                  st.setMenu((MenuBuilder)null);
                  if (isActionBarMenu && this.mDecorContentParent != null) {
                     this.mDecorContentParent.setMenu((Menu)null, this.mActionMenuPresenterCallback);
                  }

                  return false;
               }

               st.refreshMenuContent = false;
            }

            st.menu.stopDispatchingItemsChanged();
            if (st.frozenActionViewState != null) {
               st.menu.restoreActionViewStates(st.frozenActionViewState);
               st.frozenActionViewState = null;
            }

            if (!cb.onPreparePanel(0, st.createdPanelView, st.menu)) {
               if (isActionBarMenu && this.mDecorContentParent != null) {
                  this.mDecorContentParent.setMenu((Menu)null, this.mActionMenuPresenterCallback);
               }

               st.menu.startDispatchingItemsChanged();
               return false;
            }

            KeyCharacterMap kmap = KeyCharacterMap.load(event != null ? event.getDeviceId() : -1);
            st.qwertyMode = kmap.getKeyboardType() != 1;
            st.menu.setQwertyMode(st.qwertyMode);
            st.menu.startDispatchingItemsChanged();
         }

         st.isPrepared = true;
         st.isHandled = false;
         this.mPreparedPanel = st;
         return true;
      }
   }

   void checkCloseActionMenu(MenuBuilder menu) {
      if (!this.mClosingActionMenu) {
         this.mClosingActionMenu = true;
         this.mDecorContentParent.dismissPopups();
         Callback cb = this.getWindowCallback();
         if (cb != null && !this.isDestroyed()) {
            cb.onPanelClosed(108, menu);
         }

         this.mClosingActionMenu = false;
      }
   }

   void closePanel(int featureId) {
      this.closePanel(this.getPanelState(featureId, true), true);
   }

   void closePanel(AppCompatDelegateImplV9.PanelFeatureState st, boolean doCallback) {
      if (doCallback && st.featureId == 0 && this.mDecorContentParent != null && this.mDecorContentParent.isOverflowMenuShowing()) {
         this.checkCloseActionMenu(st.menu);
      } else {
         WindowManager wm = (WindowManager)this.mContext.getSystemService("window");
         if (wm != null && st.isOpen && st.decorView != null) {
            wm.removeView(st.decorView);
            if (doCallback) {
               this.callOnPanelClosed(st.featureId, st, (Menu)null);
            }
         }

         st.isPrepared = false;
         st.isHandled = false;
         st.isOpen = false;
         st.shownPanelView = null;
         st.refreshDecorView = true;
         if (this.mPreparedPanel == st) {
            this.mPreparedPanel = null;
         }

      }
   }

   private boolean onKeyDownPanel(int featureId, KeyEvent event) {
      if (event.getRepeatCount() == 0) {
         AppCompatDelegateImplV9.PanelFeatureState st = this.getPanelState(featureId, true);
         if (!st.isOpen) {
            return this.preparePanel(st, event);
         }
      }

      return false;
   }

   private boolean onKeyUpPanel(int featureId, KeyEvent event) {
      if (this.mActionMode != null) {
         return false;
      } else {
         boolean handled = false;
         AppCompatDelegateImplV9.PanelFeatureState st = this.getPanelState(featureId, true);
         if (featureId == 0 && this.mDecorContentParent != null && this.mDecorContentParent.canShowOverflowMenu() && !ViewConfiguration.get(this.mContext).hasPermanentMenuKey()) {
            if (!this.mDecorContentParent.isOverflowMenuShowing()) {
               if (!this.isDestroyed() && this.preparePanel(st, event)) {
                  handled = this.mDecorContentParent.showOverflowMenu();
               }
            } else {
               handled = this.mDecorContentParent.hideOverflowMenu();
            }
         } else if (!st.isOpen && !st.isHandled) {
            if (st.isPrepared) {
               boolean show = true;
               if (st.refreshMenuContent) {
                  st.isPrepared = false;
                  show = this.preparePanel(st, event);
               }

               if (show) {
                  this.openPanel(st, event);
                  handled = true;
               }
            }
         } else {
            handled = st.isOpen;
            this.closePanel(st, true);
         }

         if (handled) {
            AudioManager audioManager = (AudioManager)this.mContext.getSystemService("audio");
            if (audioManager != null) {
               audioManager.playSoundEffect(0);
            } else {
               Log.w("AppCompatDelegate", "Couldn't get audio manager");
            }
         }

         return handled;
      }
   }

   void callOnPanelClosed(int featureId, AppCompatDelegateImplV9.PanelFeatureState panel, Menu menu) {
      if (menu == null) {
         if (panel == null && featureId >= 0 && featureId < this.mPanels.length) {
            panel = this.mPanels[featureId];
         }

         if (panel != null) {
            menu = panel.menu;
         }
      }

      if (panel == null || panel.isOpen) {
         if (!this.isDestroyed()) {
            this.mOriginalWindowCallback.onPanelClosed(featureId, (Menu)menu);
         }

      }
   }

   AppCompatDelegateImplV9.PanelFeatureState findMenuPanel(Menu menu) {
      AppCompatDelegateImplV9.PanelFeatureState[] panels = this.mPanels;
      int N = panels != null ? panels.length : 0;

      for(int i = 0; i < N; ++i) {
         AppCompatDelegateImplV9.PanelFeatureState panel = panels[i];
         if (panel != null && panel.menu == menu) {
            return panel;
         }
      }

      return null;
   }

   protected AppCompatDelegateImplV9.PanelFeatureState getPanelState(int featureId, boolean required) {
      AppCompatDelegateImplV9.PanelFeatureState[] ar = this.mPanels;
      if (this.mPanels == null || ar.length <= featureId) {
         AppCompatDelegateImplV9.PanelFeatureState[] nar = new AppCompatDelegateImplV9.PanelFeatureState[featureId + 1];
         if (ar != null) {
            System.arraycopy(ar, 0, nar, 0, ar.length);
         }

         ar = nar;
         this.mPanels = nar;
      }

      AppCompatDelegateImplV9.PanelFeatureState st = ar[featureId];
      if (st == null) {
         ar[featureId] = st = new AppCompatDelegateImplV9.PanelFeatureState(featureId);
      }

      return st;
   }

   private boolean performPanelShortcut(AppCompatDelegateImplV9.PanelFeatureState st, int keyCode, KeyEvent event, int flags) {
      if (event.isSystem()) {
         return false;
      } else {
         boolean handled = false;
         if ((st.isPrepared || this.preparePanel(st, event)) && st.menu != null) {
            handled = st.menu.performShortcut(keyCode, event, flags);
         }

         if (handled && (flags & 1) == 0 && this.mDecorContentParent == null) {
            this.closePanel(st, true);
         }

         return handled;
      }
   }

   private void invalidatePanelMenu(int featureId) {
      this.mInvalidatePanelMenuFeatures |= 1 << featureId;
      if (!this.mInvalidatePanelMenuPosted) {
         ViewCompat.postOnAnimation(this.mWindow.getDecorView(), this.mInvalidatePanelMenuRunnable);
         this.mInvalidatePanelMenuPosted = true;
      }

   }

   void doInvalidatePanelMenu(int featureId) {
      AppCompatDelegateImplV9.PanelFeatureState st = this.getPanelState(featureId, true);
      Bundle savedActionViewStates = null;
      if (st.menu != null) {
         savedActionViewStates = new Bundle();
         st.menu.saveActionViewStates(savedActionViewStates);
         if (savedActionViewStates.size() > 0) {
            st.frozenActionViewState = savedActionViewStates;
         }

         st.menu.stopDispatchingItemsChanged();
         st.menu.clear();
      }

      st.refreshMenuContent = true;
      st.refreshDecorView = true;
      if ((featureId == 108 || featureId == 0) && this.mDecorContentParent != null) {
         st = this.getPanelState(0, false);
         if (st != null) {
            st.isPrepared = false;
            this.preparePanel(st, (KeyEvent)null);
         }
      }

   }

   int updateStatusGuard(int insetTop) {
      boolean showStatusGuard = false;
      if (this.mActionModeView != null && this.mActionModeView.getLayoutParams() instanceof MarginLayoutParams) {
         MarginLayoutParams mlp = (MarginLayoutParams)this.mActionModeView.getLayoutParams();
         boolean mlpChanged = false;
         if (this.mActionModeView.isShown()) {
            if (this.mTempRect1 == null) {
               this.mTempRect1 = new Rect();
               this.mTempRect2 = new Rect();
            }

            Rect insets = this.mTempRect1;
            Rect localInsets = this.mTempRect2;
            insets.set(0, insetTop, 0, 0);
            ViewUtils.computeFitSystemWindows(this.mSubDecor, insets, localInsets);
            int newMargin = localInsets.top == 0 ? insetTop : 0;
            if (mlp.topMargin != newMargin) {
               mlpChanged = true;
               mlp.topMargin = insetTop;
               if (this.mStatusGuard == null) {
                  this.mStatusGuard = new View(this.mContext);
                  this.mStatusGuard.setBackgroundColor(this.mContext.getResources().getColor(color.abc_input_method_navigation_guard));
                  this.mSubDecor.addView(this.mStatusGuard, -1, new LayoutParams(-1, insetTop));
               } else {
                  LayoutParams lp = this.mStatusGuard.getLayoutParams();
                  if (lp.height != insetTop) {
                     lp.height = insetTop;
                     this.mStatusGuard.setLayoutParams(lp);
                  }
               }
            }

            showStatusGuard = this.mStatusGuard != null;
            if (!this.mOverlayActionMode && showStatusGuard) {
               insetTop = 0;
            }
         } else if (mlp.topMargin != 0) {
            mlpChanged = true;
            mlp.topMargin = 0;
         }

         if (mlpChanged) {
            this.mActionModeView.setLayoutParams(mlp);
         }
      }

      if (this.mStatusGuard != null) {
         this.mStatusGuard.setVisibility(showStatusGuard ? 0 : 8);
      }

      return insetTop;
   }

   private void throwFeatureRequestIfSubDecorInstalled() {
      if (this.mSubDecorInstalled) {
         throw new AndroidRuntimeException("Window feature must be requested before adding content");
      }
   }

   private int sanitizeWindowFeatureId(int featureId) {
      if (featureId == 8) {
         Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
         return 108;
      } else if (featureId == 9) {
         Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
         return 109;
      } else {
         return featureId;
      }
   }

   ViewGroup getSubDecor() {
      return this.mSubDecor;
   }

   void dismissPopups() {
      if (this.mDecorContentParent != null) {
         this.mDecorContentParent.dismissPopups();
      }

      if (this.mActionModePopup != null) {
         this.mWindow.getDecorView().removeCallbacks(this.mShowActionModePopup);
         if (this.mActionModePopup.isShowing()) {
            try {
               this.mActionModePopup.dismiss();
            } catch (IllegalArgumentException var2) {
               ;
            }
         }

         this.mActionModePopup = null;
      }

      this.endOnGoingFadeAnimation();
      AppCompatDelegateImplV9.PanelFeatureState st = this.getPanelState(0, false);
      if (st != null && st.menu != null) {
         st.menu.close();
      }

   }

   static {
      IS_PRE_LOLLIPOP = VERSION.SDK_INT < 21;
   }

   private class ListMenuDecorView extends ContentFrameLayout {
      public ListMenuDecorView(Context context) {
         super(context);
      }

      public boolean dispatchKeyEvent(KeyEvent event) {
         return AppCompatDelegateImplV9.this.dispatchKeyEvent(event) || super.dispatchKeyEvent(event);
      }

      public boolean onInterceptTouchEvent(MotionEvent event) {
         int action = event.getAction();
         if (action == 0) {
            int x = (int)event.getX();
            int y = (int)event.getY();
            if (this.isOutOfBounds(x, y)) {
               AppCompatDelegateImplV9.this.closePanel(0);
               return true;
            }
         }

         return super.onInterceptTouchEvent(event);
      }

      public void setBackgroundResource(int resid) {
         this.setBackgroundDrawable(AppCompatResources.getDrawable(this.getContext(), resid));
      }

      private boolean isOutOfBounds(int x, int y) {
         return x < -5 || y < -5 || x > this.getWidth() + 5 || y > this.getHeight() + 5;
      }
   }

   protected static final class PanelFeatureState {
      int featureId;
      int background;
      int gravity;
      int x;
      int y;
      int windowAnimations;
      ViewGroup decorView;
      View shownPanelView;
      View createdPanelView;
      MenuBuilder menu;
      ListMenuPresenter listMenuPresenter;
      Context listPresenterContext;
      boolean isPrepared;
      boolean isHandled;
      boolean isOpen;
      public boolean qwertyMode;
      boolean refreshDecorView;
      boolean refreshMenuContent;
      boolean wasLastOpen;
      Bundle frozenMenuState;
      Bundle frozenActionViewState;

      PanelFeatureState(int featureId) {
         this.featureId = featureId;
         this.refreshDecorView = false;
      }

      public boolean hasPanelItems() {
         if (this.shownPanelView == null) {
            return false;
         } else if (this.createdPanelView != null) {
            return true;
         } else {
            return this.listMenuPresenter.getAdapter().getCount() > 0;
         }
      }

      public void clearMenuPresenters() {
         if (this.menu != null) {
            this.menu.removeMenuPresenter(this.listMenuPresenter);
         }

         this.listMenuPresenter = null;
      }

      void setStyle(Context context) {
         TypedValue outValue = new TypedValue();
         Theme widgetTheme = context.getResources().newTheme();
         widgetTheme.setTo(context.getTheme());
         widgetTheme.resolveAttribute(attr.actionBarPopupTheme, outValue, true);
         if (outValue.resourceId != 0) {
            widgetTheme.applyStyle(outValue.resourceId, true);
         }

         widgetTheme.resolveAttribute(attr.panelMenuListTheme, outValue, true);
         if (outValue.resourceId != 0) {
            widgetTheme.applyStyle(outValue.resourceId, true);
         } else {
            widgetTheme.applyStyle(style.Theme_AppCompat_CompactMenu, true);
         }

         Context context = new ContextThemeWrapper(context, 0);
         context.getTheme().setTo(widgetTheme);
         this.listPresenterContext = context;
         TypedArray a = context.obtainStyledAttributes(styleable.AppCompatTheme);
         this.background = a.getResourceId(styleable.AppCompatTheme_panelBackground, 0);
         this.windowAnimations = a.getResourceId(styleable.AppCompatTheme_android_windowAnimationStyle, 0);
         a.recycle();
      }

      void setMenu(MenuBuilder menu) {
         if (menu != this.menu) {
            if (this.menu != null) {
               this.menu.removeMenuPresenter(this.listMenuPresenter);
            }

            this.menu = menu;
            if (menu != null && this.listMenuPresenter != null) {
               menu.addMenuPresenter(this.listMenuPresenter);
            }

         }
      }

      MenuView getListMenuView(MenuPresenter.Callback cb) {
         if (this.menu == null) {
            return null;
         } else {
            if (this.listMenuPresenter == null) {
               this.listMenuPresenter = new ListMenuPresenter(this.listPresenterContext, layout.abc_list_menu_item_layout);
               this.listMenuPresenter.setCallback(cb);
               this.menu.addMenuPresenter(this.listMenuPresenter);
            }

            MenuView result = this.listMenuPresenter.getMenuView(this.decorView);
            return result;
         }
      }

      Parcelable onSaveInstanceState() {
         AppCompatDelegateImplV9.PanelFeatureState.SavedState savedState = new AppCompatDelegateImplV9.PanelFeatureState.SavedState();
         savedState.featureId = this.featureId;
         savedState.isOpen = this.isOpen;
         if (this.menu != null) {
            savedState.menuState = new Bundle();
            this.menu.savePresenterStates(savedState.menuState);
         }

         return savedState;
      }

      void onRestoreInstanceState(Parcelable state) {
         AppCompatDelegateImplV9.PanelFeatureState.SavedState savedState = (AppCompatDelegateImplV9.PanelFeatureState.SavedState)state;
         this.featureId = savedState.featureId;
         this.wasLastOpen = savedState.isOpen;
         this.frozenMenuState = savedState.menuState;
         this.shownPanelView = null;
         this.decorView = null;
      }

      void applyFrozenState() {
         if (this.menu != null && this.frozenMenuState != null) {
            this.menu.restorePresenterStates(this.frozenMenuState);
            this.frozenMenuState = null;
         }

      }

      private static class SavedState implements Parcelable {
         int featureId;
         boolean isOpen;
         Bundle menuState;
         public static final Creator CREATOR = new ClassLoaderCreator() {
            public AppCompatDelegateImplV9.PanelFeatureState.SavedState createFromParcel(Parcel in, ClassLoader loader) {
               return AppCompatDelegateImplV9.PanelFeatureState.SavedState.readFromParcel(in, loader);
            }

            public AppCompatDelegateImplV9.PanelFeatureState.SavedState createFromParcel(Parcel in) {
               return AppCompatDelegateImplV9.PanelFeatureState.SavedState.readFromParcel(in, (ClassLoader)null);
            }

            public AppCompatDelegateImplV9.PanelFeatureState.SavedState[] newArray(int size) {
               return new AppCompatDelegateImplV9.PanelFeatureState.SavedState[size];
            }
         };

         public int describeContents() {
            return 0;
         }

         public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.featureId);
            dest.writeInt(this.isOpen ? 1 : 0);
            if (this.isOpen) {
               dest.writeBundle(this.menuState);
            }

         }

         static AppCompatDelegateImplV9.PanelFeatureState.SavedState readFromParcel(Parcel source, ClassLoader loader) {
            AppCompatDelegateImplV9.PanelFeatureState.SavedState savedState = new AppCompatDelegateImplV9.PanelFeatureState.SavedState();
            savedState.featureId = source.readInt();
            savedState.isOpen = source.readInt() == 1;
            if (savedState.isOpen) {
               savedState.menuState = source.readBundle(loader);
            }

            return savedState;
         }
      }
   }

   private final class ActionMenuPresenterCallback implements MenuPresenter.Callback {
      public boolean onOpenSubMenu(MenuBuilder subMenu) {
         Callback cb = AppCompatDelegateImplV9.this.getWindowCallback();
         if (cb != null) {
            cb.onMenuOpened(108, subMenu);
         }

         return true;
      }

      public void onCloseMenu(MenuBuilder menu, boolean allMenusAreClosing) {
         AppCompatDelegateImplV9.this.checkCloseActionMenu(menu);
      }
   }

   private final class PanelMenuPresenterCallback implements MenuPresenter.Callback {
      public void onCloseMenu(MenuBuilder menu, boolean allMenusAreClosing) {
         Menu parentMenu = menu.getRootMenu();
         boolean isSubMenu = parentMenu != menu;
         AppCompatDelegateImplV9.PanelFeatureState panel = AppCompatDelegateImplV9.this.findMenuPanel(isSubMenu ? parentMenu : menu);
         if (panel != null) {
            if (isSubMenu) {
               AppCompatDelegateImplV9.this.callOnPanelClosed(panel.featureId, panel, parentMenu);
               AppCompatDelegateImplV9.this.closePanel(panel, true);
            } else {
               AppCompatDelegateImplV9.this.closePanel(panel, allMenusAreClosing);
            }
         }

      }

      public boolean onOpenSubMenu(MenuBuilder subMenu) {
         if (subMenu == null && AppCompatDelegateImplV9.this.mHasActionBar) {
            Callback cb = AppCompatDelegateImplV9.this.getWindowCallback();
            if (cb != null && !AppCompatDelegateImplV9.this.isDestroyed()) {
               cb.onMenuOpened(108, subMenu);
            }
         }

         return true;
      }
   }

   class ActionModeCallbackWrapperV9 implements ActionMode.Callback {
      private ActionMode.Callback mWrapped;

      public ActionModeCallbackWrapperV9(ActionMode.Callback wrapped) {
         this.mWrapped = wrapped;
      }

      public boolean onCreateActionMode(ActionMode mode, Menu menu) {
         return this.mWrapped.onCreateActionMode(mode, menu);
      }

      public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
         return this.mWrapped.onPrepareActionMode(mode, menu);
      }

      public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
         return this.mWrapped.onActionItemClicked(mode, item);
      }

      public void onDestroyActionMode(ActionMode mode) {
         this.mWrapped.onDestroyActionMode(mode);
         if (AppCompatDelegateImplV9.this.mActionModePopup != null) {
            AppCompatDelegateImplV9.this.mWindow.getDecorView().removeCallbacks(AppCompatDelegateImplV9.this.mShowActionModePopup);
         }

         if (AppCompatDelegateImplV9.this.mActionModeView != null) {
            AppCompatDelegateImplV9.this.endOnGoingFadeAnimation();
            AppCompatDelegateImplV9.this.mFadeAnim = ViewCompat.animate(AppCompatDelegateImplV9.this.mActionModeView).alpha(0.0F);
            AppCompatDelegateImplV9.this.mFadeAnim.setListener(new ViewPropertyAnimatorListenerAdapter() {
               public void onAnimationEnd(View view) {
                  AppCompatDelegateImplV9.this.mActionModeView.setVisibility(8);
                  if (AppCompatDelegateImplV9.this.mActionModePopup != null) {
                     AppCompatDelegateImplV9.this.mActionModePopup.dismiss();
                  } else if (AppCompatDelegateImplV9.this.mActionModeView.getParent() instanceof View) {
                     ViewCompat.requestApplyInsets((View)AppCompatDelegateImplV9.this.mActionModeView.getParent());
                  }

                  AppCompatDelegateImplV9.this.mActionModeView.removeAllViews();
                  AppCompatDelegateImplV9.this.mFadeAnim.setListener((ViewPropertyAnimatorListener)null);
                  AppCompatDelegateImplV9.this.mFadeAnim = null;
               }
            });
         }

         if (AppCompatDelegateImplV9.this.mAppCompatCallback != null) {
            AppCompatDelegateImplV9.this.mAppCompatCallback.onSupportActionModeFinished(AppCompatDelegateImplV9.this.mActionMode);
         }

         AppCompatDelegateImplV9.this.mActionMode = null;
      }
   }
}
