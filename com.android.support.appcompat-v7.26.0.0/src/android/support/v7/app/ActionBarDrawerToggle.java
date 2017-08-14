package android.support.v7.app;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class ActionBarDrawerToggle implements DrawerListener {
   private final ActionBarDrawerToggle.Delegate mActivityImpl;
   private final DrawerLayout mDrawerLayout;
   private DrawerArrowDrawable mSlider;
   private boolean mDrawerSlideAnimationEnabled;
   private Drawable mHomeAsUpIndicator;
   boolean mDrawerIndicatorEnabled;
   private boolean mHasCustomUpIndicator;
   private final int mOpenDrawerContentDescRes;
   private final int mCloseDrawerContentDescRes;
   OnClickListener mToolbarNavigationClickListener;
   private boolean mWarnedForDisplayHomeAsUp;

   public ActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, @StringRes int openDrawerContentDescRes, @StringRes int closeDrawerContentDescRes) {
      this(activity, (Toolbar)null, drawerLayout, (DrawerArrowDrawable)null, openDrawerContentDescRes, closeDrawerContentDescRes);
   }

   public ActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, @StringRes int openDrawerContentDescRes, @StringRes int closeDrawerContentDescRes) {
      this(activity, toolbar, drawerLayout, (DrawerArrowDrawable)null, openDrawerContentDescRes, closeDrawerContentDescRes);
   }

   ActionBarDrawerToggle(Activity activity, Toolbar toolbar, DrawerLayout drawerLayout, DrawerArrowDrawable slider, @StringRes int openDrawerContentDescRes, @StringRes int closeDrawerContentDescRes) {
      this.mDrawerSlideAnimationEnabled = true;
      this.mDrawerIndicatorEnabled = true;
      this.mWarnedForDisplayHomeAsUp = false;
      if (toolbar != null) {
         this.mActivityImpl = new ActionBarDrawerToggle.ToolbarCompatDelegate(toolbar);
         toolbar.setNavigationOnClickListener(new OnClickListener() {
            public void onClick(View v) {
               if (ActionBarDrawerToggle.this.mDrawerIndicatorEnabled) {
                  ActionBarDrawerToggle.this.toggle();
               } else if (ActionBarDrawerToggle.this.mToolbarNavigationClickListener != null) {
                  ActionBarDrawerToggle.this.mToolbarNavigationClickListener.onClick(v);
               }

            }
         });
      } else if (activity instanceof ActionBarDrawerToggle.DelegateProvider) {
         this.mActivityImpl = ((ActionBarDrawerToggle.DelegateProvider)activity).getDrawerToggleDelegate();
      } else if (VERSION.SDK_INT >= 18) {
         this.mActivityImpl = new ActionBarDrawerToggle.JellybeanMr2Delegate(activity);
      } else if (VERSION.SDK_INT >= 14) {
         this.mActivityImpl = new ActionBarDrawerToggle.IcsDelegate(activity);
      } else if (VERSION.SDK_INT >= 11) {
         this.mActivityImpl = new ActionBarDrawerToggle.HoneycombDelegate(activity);
      } else {
         this.mActivityImpl = new ActionBarDrawerToggle.DummyDelegate(activity);
      }

      this.mDrawerLayout = drawerLayout;
      this.mOpenDrawerContentDescRes = openDrawerContentDescRes;
      this.mCloseDrawerContentDescRes = closeDrawerContentDescRes;
      if (slider == null) {
         this.mSlider = new DrawerArrowDrawable(this.mActivityImpl.getActionBarThemedContext());
      } else {
         this.mSlider = slider;
      }

      this.mHomeAsUpIndicator = this.getThemeUpIndicator();
   }

   public void syncState() {
      if (this.mDrawerLayout.isDrawerOpen(8388611)) {
         this.setPosition(1.0F);
      } else {
         this.setPosition(0.0F);
      }

      if (this.mDrawerIndicatorEnabled) {
         this.setActionBarUpIndicator(this.mSlider, this.mDrawerLayout.isDrawerOpen(8388611) ? this.mCloseDrawerContentDescRes : this.mOpenDrawerContentDescRes);
      }

   }

   public void onConfigurationChanged(Configuration newConfig) {
      if (!this.mHasCustomUpIndicator) {
         this.mHomeAsUpIndicator = this.getThemeUpIndicator();
      }

      this.syncState();
   }

   public boolean onOptionsItemSelected(MenuItem item) {
      if (item != null && item.getItemId() == 16908332 && this.mDrawerIndicatorEnabled) {
         this.toggle();
         return true;
      } else {
         return false;
      }
   }

   void toggle() {
      int drawerLockMode = this.mDrawerLayout.getDrawerLockMode(8388611);
      if (this.mDrawerLayout.isDrawerVisible(8388611) && drawerLockMode != 2) {
         this.mDrawerLayout.closeDrawer(8388611);
      } else if (drawerLockMode != 1) {
         this.mDrawerLayout.openDrawer(8388611);
      }

   }

   public void setHomeAsUpIndicator(Drawable indicator) {
      if (indicator == null) {
         this.mHomeAsUpIndicator = this.getThemeUpIndicator();
         this.mHasCustomUpIndicator = false;
      } else {
         this.mHomeAsUpIndicator = indicator;
         this.mHasCustomUpIndicator = true;
      }

      if (!this.mDrawerIndicatorEnabled) {
         this.setActionBarUpIndicator(this.mHomeAsUpIndicator, 0);
      }

   }

   public void setHomeAsUpIndicator(int resId) {
      Drawable indicator = null;
      if (resId != 0) {
         indicator = this.mDrawerLayout.getResources().getDrawable(resId);
      }

      this.setHomeAsUpIndicator(indicator);
   }

   public boolean isDrawerIndicatorEnabled() {
      return this.mDrawerIndicatorEnabled;
   }

   public void setDrawerIndicatorEnabled(boolean enable) {
      if (enable != this.mDrawerIndicatorEnabled) {
         if (enable) {
            this.setActionBarUpIndicator(this.mSlider, this.mDrawerLayout.isDrawerOpen(8388611) ? this.mCloseDrawerContentDescRes : this.mOpenDrawerContentDescRes);
         } else {
            this.setActionBarUpIndicator(this.mHomeAsUpIndicator, 0);
         }

         this.mDrawerIndicatorEnabled = enable;
      }

   }

   @NonNull
   public DrawerArrowDrawable getDrawerArrowDrawable() {
      return this.mSlider;
   }

   public void setDrawerArrowDrawable(@NonNull DrawerArrowDrawable drawable) {
      this.mSlider = drawable;
      this.syncState();
   }

   public void setDrawerSlideAnimationEnabled(boolean enabled) {
      this.mDrawerSlideAnimationEnabled = enabled;
      if (!enabled) {
         this.setPosition(0.0F);
      }

   }

   public boolean isDrawerSlideAnimationEnabled() {
      return this.mDrawerSlideAnimationEnabled;
   }

   public void onDrawerSlide(View drawerView, float slideOffset) {
      if (this.mDrawerSlideAnimationEnabled) {
         this.setPosition(Math.min(1.0F, Math.max(0.0F, slideOffset)));
      } else {
         this.setPosition(0.0F);
      }

   }

   public void onDrawerOpened(View drawerView) {
      this.setPosition(1.0F);
      if (this.mDrawerIndicatorEnabled) {
         this.setActionBarDescription(this.mCloseDrawerContentDescRes);
      }

   }

   public void onDrawerClosed(View drawerView) {
      this.setPosition(0.0F);
      if (this.mDrawerIndicatorEnabled) {
         this.setActionBarDescription(this.mOpenDrawerContentDescRes);
      }

   }

   public void onDrawerStateChanged(int newState) {
   }

   public OnClickListener getToolbarNavigationClickListener() {
      return this.mToolbarNavigationClickListener;
   }

   public void setToolbarNavigationClickListener(OnClickListener onToolbarNavigationClickListener) {
      this.mToolbarNavigationClickListener = onToolbarNavigationClickListener;
   }

   void setActionBarUpIndicator(Drawable upDrawable, int contentDescRes) {
      if (!this.mWarnedForDisplayHomeAsUp && !this.mActivityImpl.isNavigationVisible()) {
         Log.w("ActionBarDrawerToggle", "DrawerToggle may not show up because NavigationIcon is not visible. You may need to call actionbar.setDisplayHomeAsUpEnabled(true);");
         this.mWarnedForDisplayHomeAsUp = true;
      }

      this.mActivityImpl.setActionBarUpIndicator(upDrawable, contentDescRes);
   }

   void setActionBarDescription(int contentDescRes) {
      this.mActivityImpl.setActionBarDescription(contentDescRes);
   }

   Drawable getThemeUpIndicator() {
      return this.mActivityImpl.getThemeUpIndicator();
   }

   private void setPosition(float position) {
      if (position == 1.0F) {
         this.mSlider.setVerticalMirror(true);
      } else if (position == 0.0F) {
         this.mSlider.setVerticalMirror(false);
      }

      this.mSlider.setProgress(position);
   }

   static class DummyDelegate implements ActionBarDrawerToggle.Delegate {
      final Activity mActivity;

      DummyDelegate(Activity activity) {
         this.mActivity = activity;
      }

      public void setActionBarUpIndicator(Drawable upDrawable, @StringRes int contentDescRes) {
      }

      public void setActionBarDescription(@StringRes int contentDescRes) {
      }

      public Drawable getThemeUpIndicator() {
         return null;
      }

      public Context getActionBarThemedContext() {
         return this.mActivity;
      }

      public boolean isNavigationVisible() {
         return true;
      }
   }

   static class ToolbarCompatDelegate implements ActionBarDrawerToggle.Delegate {
      final Toolbar mToolbar;
      final Drawable mDefaultUpIndicator;
      final CharSequence mDefaultContentDescription;

      ToolbarCompatDelegate(Toolbar toolbar) {
         this.mToolbar = toolbar;
         this.mDefaultUpIndicator = toolbar.getNavigationIcon();
         this.mDefaultContentDescription = toolbar.getNavigationContentDescription();
      }

      public void setActionBarUpIndicator(Drawable upDrawable, @StringRes int contentDescRes) {
         this.mToolbar.setNavigationIcon(upDrawable);
         this.setActionBarDescription(contentDescRes);
      }

      public void setActionBarDescription(@StringRes int contentDescRes) {
         if (contentDescRes == 0) {
            this.mToolbar.setNavigationContentDescription(this.mDefaultContentDescription);
         } else {
            this.mToolbar.setNavigationContentDescription(contentDescRes);
         }

      }

      public Drawable getThemeUpIndicator() {
         return this.mDefaultUpIndicator;
      }

      public Context getActionBarThemedContext() {
         return this.mToolbar.getContext();
      }

      public boolean isNavigationVisible() {
         return true;
      }
   }

   @RequiresApi(18)
   private static class JellybeanMr2Delegate implements ActionBarDrawerToggle.Delegate {
      final Activity mActivity;

      JellybeanMr2Delegate(Activity activity) {
         this.mActivity = activity;
      }

      public Drawable getThemeUpIndicator() {
         TypedArray a = this.getActionBarThemedContext().obtainStyledAttributes((AttributeSet)null, new int[]{16843531}, 16843470, 0);
         Drawable result = a.getDrawable(0);
         a.recycle();
         return result;
      }

      public Context getActionBarThemedContext() {
         android.app.ActionBar actionBar = this.mActivity.getActionBar();
         Object context;
         if (actionBar != null) {
            context = actionBar.getThemedContext();
         } else {
            context = this.mActivity;
         }

         return (Context)context;
      }

      public boolean isNavigationVisible() {
         android.app.ActionBar actionBar = this.mActivity.getActionBar();
         return actionBar != null && (actionBar.getDisplayOptions() & 4) != 0;
      }

      public void setActionBarUpIndicator(Drawable drawable, int contentDescRes) {
         android.app.ActionBar actionBar = this.mActivity.getActionBar();
         if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(drawable);
            actionBar.setHomeActionContentDescription(contentDescRes);
         }

      }

      public void setActionBarDescription(int contentDescRes) {
         android.app.ActionBar actionBar = this.mActivity.getActionBar();
         if (actionBar != null) {
            actionBar.setHomeActionContentDescription(contentDescRes);
         }

      }
   }

   @RequiresApi(14)
   private static class IcsDelegate extends ActionBarDrawerToggle.HoneycombDelegate {
      IcsDelegate(Activity activity) {
         super(activity);
      }

      public Context getActionBarThemedContext() {
         android.app.ActionBar actionBar = this.mActivity.getActionBar();
         Object context;
         if (actionBar != null) {
            context = actionBar.getThemedContext();
         } else {
            context = this.mActivity;
         }

         return (Context)context;
      }
   }

   @RequiresApi(11)
   private static class HoneycombDelegate implements ActionBarDrawerToggle.Delegate {
      final Activity mActivity;
      ActionBarDrawerToggleHoneycomb.SetIndicatorInfo mSetIndicatorInfo;

      HoneycombDelegate(Activity activity) {
         this.mActivity = activity;
      }

      public Drawable getThemeUpIndicator() {
         return ActionBarDrawerToggleHoneycomb.getThemeUpIndicator(this.mActivity);
      }

      public Context getActionBarThemedContext() {
         return this.mActivity;
      }

      public boolean isNavigationVisible() {
         android.app.ActionBar actionBar = this.mActivity.getActionBar();
         return actionBar != null && (actionBar.getDisplayOptions() & 4) != 0;
      }

      public void setActionBarUpIndicator(Drawable themeImage, int contentDescRes) {
         android.app.ActionBar actionBar = this.mActivity.getActionBar();
         if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            this.mSetIndicatorInfo = ActionBarDrawerToggleHoneycomb.setActionBarUpIndicator(this.mSetIndicatorInfo, this.mActivity, themeImage, contentDescRes);
            actionBar.setDisplayShowHomeEnabled(false);
         }

      }

      public void setActionBarDescription(int contentDescRes) {
         this.mSetIndicatorInfo = ActionBarDrawerToggleHoneycomb.setActionBarDescription(this.mSetIndicatorInfo, this.mActivity, contentDescRes);
      }
   }

   public interface Delegate {
      void setActionBarUpIndicator(Drawable var1, @StringRes int var2);

      void setActionBarDescription(@StringRes int var1);

      Drawable getThemeUpIndicator();

      Context getActionBarThemedContext();

      boolean isNavigationVisible();
   }

   public interface DelegateProvider {
      @Nullable
      ActionBarDrawerToggle.Delegate getDrawerToggleDelegate();
   }
}
