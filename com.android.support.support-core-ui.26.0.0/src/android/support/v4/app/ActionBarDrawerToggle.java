package android.support.v4.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.Drawable.Callback;
import android.os.Build.VERSION;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.lang.reflect.Method;

/** @deprecated */
@Deprecated
public class ActionBarDrawerToggle implements DrawerLayout.DrawerListener {
   private static final String TAG = "ActionBarDrawerToggle";
   private static final int[] THEME_ATTRS = new int[]{16843531};
   private static final float TOGGLE_DRAWABLE_OFFSET = 0.33333334F;
   private static final int ID_HOME = 16908332;
   final Activity mActivity;
   private final ActionBarDrawerToggle.Delegate mActivityImpl;
   private final DrawerLayout mDrawerLayout;
   private boolean mDrawerIndicatorEnabled;
   private boolean mHasCustomUpIndicator;
   private Drawable mHomeAsUpIndicator;
   private Drawable mDrawerImage;
   private ActionBarDrawerToggle.SlideDrawable mSlider;
   private final int mDrawerImageResource;
   private final int mOpenDrawerContentDescRes;
   private final int mCloseDrawerContentDescRes;
   private ActionBarDrawerToggle.SetIndicatorInfo mSetIndicatorInfo;

   public ActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, @DrawableRes int drawerImageRes, @StringRes int openDrawerContentDescRes, @StringRes int closeDrawerContentDescRes) {
      this(activity, drawerLayout, !assumeMaterial(activity), drawerImageRes, openDrawerContentDescRes, closeDrawerContentDescRes);
   }

   private static boolean assumeMaterial(Context context) {
      return context.getApplicationInfo().targetSdkVersion >= 21 && VERSION.SDK_INT >= 21;
   }

   public ActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, boolean animate, @DrawableRes int drawerImageRes, @StringRes int openDrawerContentDescRes, @StringRes int closeDrawerContentDescRes) {
      this.mDrawerIndicatorEnabled = true;
      this.mActivity = activity;
      if (activity instanceof ActionBarDrawerToggle.DelegateProvider) {
         this.mActivityImpl = ((ActionBarDrawerToggle.DelegateProvider)activity).getDrawerToggleDelegate();
      } else {
         this.mActivityImpl = null;
      }

      this.mDrawerLayout = drawerLayout;
      this.mDrawerImageResource = drawerImageRes;
      this.mOpenDrawerContentDescRes = openDrawerContentDescRes;
      this.mCloseDrawerContentDescRes = closeDrawerContentDescRes;
      this.mHomeAsUpIndicator = this.getThemeUpIndicator();
      this.mDrawerImage = ContextCompat.getDrawable(activity, drawerImageRes);
      this.mSlider = new ActionBarDrawerToggle.SlideDrawable(this.mDrawerImage);
      this.mSlider.setOffset(animate ? 0.33333334F : 0.0F);
   }

   public void syncState() {
      if (this.mDrawerLayout.isDrawerOpen(8388611)) {
         this.mSlider.setPosition(1.0F);
      } else {
         this.mSlider.setPosition(0.0F);
      }

      if (this.mDrawerIndicatorEnabled) {
         this.setActionBarUpIndicator(this.mSlider, this.mDrawerLayout.isDrawerOpen(8388611) ? this.mCloseDrawerContentDescRes : this.mOpenDrawerContentDescRes);
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
         indicator = ContextCompat.getDrawable(this.mActivity, resId);
      }

      this.setHomeAsUpIndicator(indicator);
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

   public boolean isDrawerIndicatorEnabled() {
      return this.mDrawerIndicatorEnabled;
   }

   public void onConfigurationChanged(Configuration newConfig) {
      if (!this.mHasCustomUpIndicator) {
         this.mHomeAsUpIndicator = this.getThemeUpIndicator();
      }

      this.mDrawerImage = ContextCompat.getDrawable(this.mActivity, this.mDrawerImageResource);
      this.syncState();
   }

   public boolean onOptionsItemSelected(MenuItem item) {
      if (item != null && item.getItemId() == 16908332 && this.mDrawerIndicatorEnabled) {
         if (this.mDrawerLayout.isDrawerVisible(8388611)) {
            this.mDrawerLayout.closeDrawer(8388611);
         } else {
            this.mDrawerLayout.openDrawer(8388611);
         }

         return true;
      } else {
         return false;
      }
   }

   public void onDrawerSlide(View drawerView, float slideOffset) {
      float glyphOffset = this.mSlider.getPosition();
      if (slideOffset > 0.5F) {
         glyphOffset = Math.max(glyphOffset, Math.max(0.0F, slideOffset - 0.5F) * 2.0F);
      } else {
         glyphOffset = Math.min(glyphOffset, slideOffset * 2.0F);
      }

      this.mSlider.setPosition(glyphOffset);
   }

   public void onDrawerOpened(View drawerView) {
      this.mSlider.setPosition(1.0F);
      if (this.mDrawerIndicatorEnabled) {
         this.setActionBarDescription(this.mCloseDrawerContentDescRes);
      }

   }

   public void onDrawerClosed(View drawerView) {
      this.mSlider.setPosition(0.0F);
      if (this.mDrawerIndicatorEnabled) {
         this.setActionBarDescription(this.mOpenDrawerContentDescRes);
      }

   }

   public void onDrawerStateChanged(int newState) {
   }

   private Drawable getThemeUpIndicator() {
      if (this.mActivityImpl != null) {
         return this.mActivityImpl.getThemeUpIndicator();
      } else if (VERSION.SDK_INT >= 18) {
         ActionBar actionBar = this.mActivity.getActionBar();
         Object context;
         if (actionBar != null) {
            context = actionBar.getThemedContext();
         } else {
            context = this.mActivity;
         }

         TypedArray a = ((Context)context).obtainStyledAttributes((AttributeSet)null, THEME_ATTRS, 16843470, 0);
         Drawable result = a.getDrawable(0);
         a.recycle();
         return result;
      } else {
         TypedArray a = this.mActivity.obtainStyledAttributes(THEME_ATTRS);
         Drawable result = a.getDrawable(0);
         a.recycle();
         return result;
      }
   }

   private void setActionBarUpIndicator(Drawable upDrawable, int contentDescRes) {
      if (this.mActivityImpl != null) {
         this.mActivityImpl.setActionBarUpIndicator(upDrawable, contentDescRes);
      } else {
         ActionBar actionBar;
         if (VERSION.SDK_INT >= 18) {
            actionBar = this.mActivity.getActionBar();
            if (actionBar != null) {
               actionBar.setHomeAsUpIndicator(upDrawable);
               actionBar.setHomeActionContentDescription(contentDescRes);
            }
         } else {
            if (this.mSetIndicatorInfo == null) {
               this.mSetIndicatorInfo = new ActionBarDrawerToggle.SetIndicatorInfo(this.mActivity);
            }

            if (this.mSetIndicatorInfo.mSetHomeAsUpIndicator != null) {
               try {
                  actionBar = this.mActivity.getActionBar();
                  this.mSetIndicatorInfo.mSetHomeAsUpIndicator.invoke(actionBar, upDrawable);
                  this.mSetIndicatorInfo.mSetHomeActionContentDescription.invoke(actionBar, contentDescRes);
               } catch (Exception var4) {
                  Log.w("ActionBarDrawerToggle", "Couldn't set home-as-up indicator via JB-MR2 API", var4);
               }
            } else if (this.mSetIndicatorInfo.mUpIndicatorView != null) {
               this.mSetIndicatorInfo.mUpIndicatorView.setImageDrawable(upDrawable);
            } else {
               Log.w("ActionBarDrawerToggle", "Couldn't set home-as-up indicator");
            }
         }

      }
   }

   private void setActionBarDescription(int contentDescRes) {
      if (this.mActivityImpl != null) {
         this.mActivityImpl.setActionBarDescription(contentDescRes);
      } else {
         ActionBar actionBar;
         if (VERSION.SDK_INT >= 18) {
            actionBar = this.mActivity.getActionBar();
            if (actionBar != null) {
               actionBar.setHomeActionContentDescription(contentDescRes);
            }
         } else {
            if (this.mSetIndicatorInfo == null) {
               this.mSetIndicatorInfo = new ActionBarDrawerToggle.SetIndicatorInfo(this.mActivity);
            }

            if (this.mSetIndicatorInfo.mSetHomeAsUpIndicator != null) {
               try {
                  actionBar = this.mActivity.getActionBar();
                  this.mSetIndicatorInfo.mSetHomeActionContentDescription.invoke(actionBar, contentDescRes);
                  actionBar.setSubtitle(actionBar.getSubtitle());
               } catch (Exception var3) {
                  Log.w("ActionBarDrawerToggle", "Couldn't set content description via JB-MR2 API", var3);
               }
            }
         }

      }
   }

   private class SlideDrawable extends InsetDrawable implements Callback {
      private final boolean mHasMirroring;
      private final Rect mTmpRect;
      private float mPosition;
      private float mOffset;

      SlideDrawable(Drawable wrapped) {
         super(wrapped, 0);
         this.mHasMirroring = VERSION.SDK_INT > 18;
         this.mTmpRect = new Rect();
      }

      public void setPosition(float position) {
         this.mPosition = position;
         this.invalidateSelf();
      }

      public float getPosition() {
         return this.mPosition;
      }

      public void setOffset(float offset) {
         this.mOffset = offset;
         this.invalidateSelf();
      }

      public void draw(@NonNull Canvas canvas) {
         this.copyBounds(this.mTmpRect);
         canvas.save();
         boolean isLayoutRTL = ViewCompat.getLayoutDirection(ActionBarDrawerToggle.this.mActivity.getWindow().getDecorView()) == 1;
         int flipRtl = isLayoutRTL ? -1 : 1;
         int width = this.mTmpRect.width();
         canvas.translate(-this.mOffset * (float)width * this.mPosition * (float)flipRtl, 0.0F);
         if (isLayoutRTL && !this.mHasMirroring) {
            canvas.translate((float)width, 0.0F);
            canvas.scale(-1.0F, 1.0F);
         }

         super.draw(canvas);
         canvas.restore();
      }
   }

   private static class SetIndicatorInfo {
      Method mSetHomeAsUpIndicator;
      Method mSetHomeActionContentDescription;
      ImageView mUpIndicatorView;

      SetIndicatorInfo(Activity activity) {
         try {
            this.mSetHomeAsUpIndicator = ActionBar.class.getDeclaredMethod("setHomeAsUpIndicator", Drawable.class);
            this.mSetHomeActionContentDescription = ActionBar.class.getDeclaredMethod("setHomeActionContentDescription", Integer.TYPE);
         } catch (NoSuchMethodException var8) {
            View home = activity.findViewById(16908332);
            if (home != null) {
               ViewGroup parent = (ViewGroup)home.getParent();
               int childCount = parent.getChildCount();
               if (childCount == 2) {
                  View first = parent.getChildAt(0);
                  View second = parent.getChildAt(1);
                  View up = first.getId() == 16908332 ? second : first;
                  if (up instanceof ImageView) {
                     this.mUpIndicatorView = (ImageView)up;
                  }

               }
            }
         }
      }
   }

   /** @deprecated */
   @Deprecated
   public interface Delegate {
      @Nullable
      Drawable getThemeUpIndicator();

      void setActionBarUpIndicator(Drawable var1, @StringRes int var2);

      void setActionBarDescription(@StringRes int var1);
   }

   /** @deprecated */
   @Deprecated
   public interface DelegateProvider {
      @Nullable
      ActionBarDrawerToggle.Delegate getDrawerToggleDelegate();
   }
}
