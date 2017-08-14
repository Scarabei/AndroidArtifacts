package android.support.v7.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StringRes;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.view.ActionMode;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.SpinnerAdapter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class ActionBar {
   /** @deprecated */
   @Deprecated
   public static final int NAVIGATION_MODE_STANDARD = 0;
   /** @deprecated */
   @Deprecated
   public static final int NAVIGATION_MODE_LIST = 1;
   /** @deprecated */
   @Deprecated
   public static final int NAVIGATION_MODE_TABS = 2;
   public static final int DISPLAY_USE_LOGO = 1;
   public static final int DISPLAY_SHOW_HOME = 2;
   public static final int DISPLAY_HOME_AS_UP = 4;
   public static final int DISPLAY_SHOW_TITLE = 8;
   public static final int DISPLAY_SHOW_CUSTOM = 16;

   public abstract void setCustomView(View var1);

   public abstract void setCustomView(View var1, ActionBar.LayoutParams var2);

   public abstract void setCustomView(int var1);

   public abstract void setIcon(@DrawableRes int var1);

   public abstract void setIcon(Drawable var1);

   public abstract void setLogo(@DrawableRes int var1);

   public abstract void setLogo(Drawable var1);

   /** @deprecated */
   @Deprecated
   public abstract void setListNavigationCallbacks(SpinnerAdapter var1, ActionBar.OnNavigationListener var2);

   /** @deprecated */
   @Deprecated
   public abstract void setSelectedNavigationItem(int var1);

   /** @deprecated */
   @Deprecated
   public abstract int getSelectedNavigationIndex();

   /** @deprecated */
   @Deprecated
   public abstract int getNavigationItemCount();

   public abstract void setTitle(CharSequence var1);

   public abstract void setTitle(@StringRes int var1);

   public abstract void setSubtitle(CharSequence var1);

   public abstract void setSubtitle(int var1);

   public abstract void setDisplayOptions(int var1);

   public abstract void setDisplayOptions(int var1, int var2);

   public abstract void setDisplayUseLogoEnabled(boolean var1);

   public abstract void setDisplayShowHomeEnabled(boolean var1);

   public abstract void setDisplayHomeAsUpEnabled(boolean var1);

   public abstract void setDisplayShowTitleEnabled(boolean var1);

   public abstract void setDisplayShowCustomEnabled(boolean var1);

   public abstract void setBackgroundDrawable(@Nullable Drawable var1);

   public void setStackedBackgroundDrawable(Drawable d) {
   }

   public void setSplitBackgroundDrawable(Drawable d) {
   }

   public abstract View getCustomView();

   @Nullable
   public abstract CharSequence getTitle();

   @Nullable
   public abstract CharSequence getSubtitle();

   /** @deprecated */
   @Deprecated
   public abstract int getNavigationMode();

   /** @deprecated */
   @Deprecated
   public abstract void setNavigationMode(int var1);

   public abstract int getDisplayOptions();

   /** @deprecated */
   @Deprecated
   public abstract ActionBar.Tab newTab();

   /** @deprecated */
   @Deprecated
   public abstract void addTab(ActionBar.Tab var1);

   /** @deprecated */
   @Deprecated
   public abstract void addTab(ActionBar.Tab var1, boolean var2);

   /** @deprecated */
   @Deprecated
   public abstract void addTab(ActionBar.Tab var1, int var2);

   /** @deprecated */
   @Deprecated
   public abstract void addTab(ActionBar.Tab var1, int var2, boolean var3);

   /** @deprecated */
   @Deprecated
   public abstract void removeTab(ActionBar.Tab var1);

   /** @deprecated */
   @Deprecated
   public abstract void removeTabAt(int var1);

   /** @deprecated */
   @Deprecated
   public abstract void removeAllTabs();

   /** @deprecated */
   @Deprecated
   public abstract void selectTab(ActionBar.Tab var1);

   /** @deprecated */
   @Deprecated
   @Nullable
   public abstract ActionBar.Tab getSelectedTab();

   /** @deprecated */
   @Deprecated
   public abstract ActionBar.Tab getTabAt(int var1);

   /** @deprecated */
   @Deprecated
   public abstract int getTabCount();

   public abstract int getHeight();

   public abstract void show();

   public abstract void hide();

   public abstract boolean isShowing();

   public abstract void addOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener var1);

   public abstract void removeOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener var1);

   public void setHomeButtonEnabled(boolean enabled) {
   }

   public Context getThemedContext() {
      return null;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public boolean isTitleTruncated() {
      return false;
   }

   public void setHomeAsUpIndicator(@Nullable Drawable indicator) {
   }

   public void setHomeAsUpIndicator(@DrawableRes int resId) {
   }

   public void setHomeActionContentDescription(@Nullable CharSequence description) {
   }

   public void setHomeActionContentDescription(@StringRes int resId) {
   }

   public void setHideOnContentScrollEnabled(boolean hideOnContentScroll) {
      if (hideOnContentScroll) {
         throw new UnsupportedOperationException("Hide on content scroll is not supported in this action bar configuration.");
      }
   }

   public boolean isHideOnContentScrollEnabled() {
      return false;
   }

   public int getHideOffset() {
      return 0;
   }

   public void setHideOffset(int offset) {
      if (offset != 0) {
         throw new UnsupportedOperationException("Setting an explicit action bar hide offset is not supported in this action bar configuration.");
      }
   }

   public void setElevation(float elevation) {
      if (elevation != 0.0F) {
         throw new UnsupportedOperationException("Setting a non-zero elevation is not supported in this action bar configuration.");
      }
   }

   public float getElevation() {
      return 0.0F;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public void setDefaultDisplayHomeAsUpEnabled(boolean enabled) {
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public void setShowHideAnimationEnabled(boolean enabled) {
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public void onConfigurationChanged(Configuration config) {
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public void dispatchMenuVisibilityChanged(boolean visible) {
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public ActionMode startActionMode(ActionMode.Callback callback) {
      return null;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public boolean openOptionsMenu() {
      return false;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public boolean closeOptionsMenu() {
      return false;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public boolean invalidateOptionsMenu() {
      return false;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public boolean onMenuKeyEvent(KeyEvent event) {
      return false;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public boolean onKeyShortcut(int keyCode, KeyEvent ev) {
      return false;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public boolean collapseActionView() {
      return false;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public void setWindowTitle(CharSequence title) {
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   boolean requestFocus() {
      return false;
   }

   void onDestroy() {
   }

   public static class LayoutParams extends MarginLayoutParams {
      public int gravity;

      public LayoutParams(@NonNull Context c, AttributeSet attrs) {
         super(c, attrs);
         this.gravity = 0;
         TypedArray a = c.obtainStyledAttributes(attrs, styleable.ActionBarLayout);
         this.gravity = a.getInt(styleable.ActionBarLayout_android_layout_gravity, 0);
         a.recycle();
      }

      public LayoutParams(int width, int height) {
         super(width, height);
         this.gravity = 0;
         this.gravity = 8388627;
      }

      public LayoutParams(int width, int height, int gravity) {
         super(width, height);
         this.gravity = 0;
         this.gravity = gravity;
      }

      public LayoutParams(int gravity) {
         this(-2, -1, gravity);
      }

      public LayoutParams(ActionBar.LayoutParams source) {
         super(source);
         this.gravity = 0;
         this.gravity = source.gravity;
      }

      public LayoutParams(android.view.ViewGroup.LayoutParams source) {
         super(source);
         this.gravity = 0;
      }
   }

   /** @deprecated */
   @Deprecated
   public interface TabListener {
      void onTabSelected(ActionBar.Tab var1, FragmentTransaction var2);

      void onTabUnselected(ActionBar.Tab var1, FragmentTransaction var2);

      void onTabReselected(ActionBar.Tab var1, FragmentTransaction var2);
   }

   /** @deprecated */
   @Deprecated
   public abstract static class Tab {
      public static final int INVALID_POSITION = -1;

      public abstract int getPosition();

      public abstract Drawable getIcon();

      public abstract CharSequence getText();

      public abstract ActionBar.Tab setIcon(Drawable var1);

      public abstract ActionBar.Tab setIcon(@DrawableRes int var1);

      public abstract ActionBar.Tab setText(CharSequence var1);

      public abstract ActionBar.Tab setText(int var1);

      public abstract ActionBar.Tab setCustomView(View var1);

      public abstract ActionBar.Tab setCustomView(int var1);

      public abstract View getCustomView();

      public abstract ActionBar.Tab setTag(Object var1);

      public abstract Object getTag();

      public abstract ActionBar.Tab setTabListener(ActionBar.TabListener var1);

      public abstract void select();

      public abstract ActionBar.Tab setContentDescription(@StringRes int var1);

      public abstract ActionBar.Tab setContentDescription(CharSequence var1);

      public abstract CharSequence getContentDescription();
   }

   public interface OnMenuVisibilityListener {
      void onMenuVisibilityChanged(boolean var1);
   }

   /** @deprecated */
   @Deprecated
   public interface OnNavigationListener {
      boolean onNavigationItemSelected(int var1, long var2);
   }

   @Retention(RetentionPolicy.SOURCE)
   @RestrictTo({Scope.LIBRARY_GROUP})
   public @interface DisplayOptions {
   }

   @Retention(RetentionPolicy.SOURCE)
   @RestrictTo({Scope.LIBRARY_GROUP})
   public @interface NavigationMode {
   }
}
