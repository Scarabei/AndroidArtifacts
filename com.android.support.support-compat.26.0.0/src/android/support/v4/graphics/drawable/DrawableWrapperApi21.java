package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Outline;
import android.graphics.Rect;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.util.Log;
import java.lang.reflect.Method;

@RequiresApi(21)
class DrawableWrapperApi21 extends DrawableWrapperApi19 {
   private static final String TAG = "DrawableWrapperApi21";
   private static Method sIsProjectedDrawableMethod;

   DrawableWrapperApi21(Drawable drawable) {
      super(drawable);
      this.findAndCacheIsProjectedDrawableMethod();
   }

   DrawableWrapperApi21(DrawableWrapperApi14.DrawableWrapperState state, Resources resources) {
      super(state, resources);
      this.findAndCacheIsProjectedDrawableMethod();
   }

   public void setHotspot(float x, float y) {
      this.mDrawable.setHotspot(x, y);
   }

   public void setHotspotBounds(int left, int top, int right, int bottom) {
      this.mDrawable.setHotspotBounds(left, top, right, bottom);
   }

   public void getOutline(Outline outline) {
      this.mDrawable.getOutline(outline);
   }

   public Rect getDirtyBounds() {
      return this.mDrawable.getDirtyBounds();
   }

   public void setTintList(ColorStateList tint) {
      if (this.isCompatTintEnabled()) {
         super.setTintList(tint);
      } else {
         this.mDrawable.setTintList(tint);
      }

   }

   public void setTint(int tintColor) {
      if (this.isCompatTintEnabled()) {
         super.setTint(tintColor);
      } else {
         this.mDrawable.setTint(tintColor);
      }

   }

   public void setTintMode(Mode tintMode) {
      if (this.isCompatTintEnabled()) {
         super.setTintMode(tintMode);
      } else {
         this.mDrawable.setTintMode(tintMode);
      }

   }

   public boolean setState(int[] stateSet) {
      if (super.setState(stateSet)) {
         this.invalidateSelf();
         return true;
      } else {
         return false;
      }
   }

   protected boolean isCompatTintEnabled() {
      if (VERSION.SDK_INT != 21) {
         return false;
      } else {
         Drawable drawable = this.mDrawable;
         return drawable instanceof GradientDrawable || drawable instanceof DrawableContainer || drawable instanceof InsetDrawable || drawable instanceof RippleDrawable;
      }
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public boolean isProjected() {
      if (this.mDrawable != null && sIsProjectedDrawableMethod != null) {
         try {
            return ((Boolean)sIsProjectedDrawableMethod.invoke(this.mDrawable)).booleanValue();
         } catch (Exception var2) {
            Log.w("DrawableWrapperApi21", "Error calling Drawable#isProjected() method", var2);
         }
      }

      return false;
   }

   @NonNull
   DrawableWrapperApi14.DrawableWrapperState mutateConstantState() {
      return new DrawableWrapperApi21.DrawableWrapperStateLollipop(this.mState, (Resources)null);
   }

   private void findAndCacheIsProjectedDrawableMethod() {
      if (sIsProjectedDrawableMethod == null) {
         try {
            sIsProjectedDrawableMethod = Drawable.class.getDeclaredMethod("isProjected");
         } catch (Exception var2) {
            Log.w("DrawableWrapperApi21", "Failed to retrieve Drawable#isProjected() method", var2);
         }
      }

   }

   private static class DrawableWrapperStateLollipop extends DrawableWrapperApi14.DrawableWrapperState {
      DrawableWrapperStateLollipop(@Nullable DrawableWrapperApi14.DrawableWrapperState orig, @Nullable Resources res) {
         super(orig, res);
      }

      public Drawable newDrawable(@Nullable Resources res) {
         return new DrawableWrapperApi21(this, res);
      }
   }
}
