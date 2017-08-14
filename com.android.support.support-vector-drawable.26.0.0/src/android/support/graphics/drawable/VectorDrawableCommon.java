package android.support.graphics.drawable;

import android.content.res.Resources.Theme;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.graphics.drawable.TintAwareDrawable;

abstract class VectorDrawableCommon extends Drawable implements TintAwareDrawable {
   Drawable mDelegateDrawable;

   public void setColorFilter(int color, Mode mode) {
      if (this.mDelegateDrawable != null) {
         this.mDelegateDrawable.setColorFilter(color, mode);
      } else {
         super.setColorFilter(color, mode);
      }
   }

   public ColorFilter getColorFilter() {
      return this.mDelegateDrawable != null ? DrawableCompat.getColorFilter(this.mDelegateDrawable) : null;
   }

   protected boolean onLevelChange(int level) {
      return this.mDelegateDrawable != null ? this.mDelegateDrawable.setLevel(level) : super.onLevelChange(level);
   }

   protected void onBoundsChange(Rect bounds) {
      if (this.mDelegateDrawable != null) {
         this.mDelegateDrawable.setBounds(bounds);
      } else {
         super.onBoundsChange(bounds);
      }
   }

   public void setHotspot(float x, float y) {
      if (this.mDelegateDrawable != null) {
         DrawableCompat.setHotspot(this.mDelegateDrawable, x, y);
      }

   }

   public void setHotspotBounds(int left, int top, int right, int bottom) {
      if (this.mDelegateDrawable != null) {
         DrawableCompat.setHotspotBounds(this.mDelegateDrawable, left, top, right, bottom);
      }
   }

   public void setFilterBitmap(boolean filter) {
      if (this.mDelegateDrawable != null) {
         this.mDelegateDrawable.setFilterBitmap(filter);
      }
   }

   public void jumpToCurrentState() {
      if (this.mDelegateDrawable != null) {
         DrawableCompat.jumpToCurrentState(this.mDelegateDrawable);
      }
   }

   public void applyTheme(Theme t) {
      if (this.mDelegateDrawable != null) {
         DrawableCompat.applyTheme(this.mDelegateDrawable, t);
      }
   }

   public void clearColorFilter() {
      if (this.mDelegateDrawable != null) {
         this.mDelegateDrawable.clearColorFilter();
      } else {
         super.clearColorFilter();
      }
   }

   public Drawable getCurrent() {
      return this.mDelegateDrawable != null ? this.mDelegateDrawable.getCurrent() : super.getCurrent();
   }

   public int getMinimumWidth() {
      return this.mDelegateDrawable != null ? this.mDelegateDrawable.getMinimumWidth() : super.getMinimumWidth();
   }

   public int getMinimumHeight() {
      return this.mDelegateDrawable != null ? this.mDelegateDrawable.getMinimumHeight() : super.getMinimumHeight();
   }

   public boolean getPadding(Rect padding) {
      return this.mDelegateDrawable != null ? this.mDelegateDrawable.getPadding(padding) : super.getPadding(padding);
   }

   public int[] getState() {
      return this.mDelegateDrawable != null ? this.mDelegateDrawable.getState() : super.getState();
   }

   public Region getTransparentRegion() {
      return this.mDelegateDrawable != null ? this.mDelegateDrawable.getTransparentRegion() : super.getTransparentRegion();
   }

   public void setChangingConfigurations(int configs) {
      if (this.mDelegateDrawable != null) {
         this.mDelegateDrawable.setChangingConfigurations(configs);
      } else {
         super.setChangingConfigurations(configs);
      }
   }

   public boolean setState(int[] stateSet) {
      return this.mDelegateDrawable != null ? this.mDelegateDrawable.setState(stateSet) : super.setState(stateSet);
   }
}
