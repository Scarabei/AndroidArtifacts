package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

@RequiresApi(14)
class DrawableWrapperApi14 extends Drawable implements Callback, DrawableWrapper, TintAwareDrawable {
   static final Mode DEFAULT_TINT_MODE;
   private int mCurrentColor;
   private Mode mCurrentMode;
   private boolean mColorFilterSet;
   DrawableWrapperApi14.DrawableWrapperState mState;
   private boolean mMutated;
   Drawable mDrawable;

   DrawableWrapperApi14(@NonNull DrawableWrapperApi14.DrawableWrapperState state, @Nullable Resources res) {
      this.mState = state;
      this.updateLocalState(res);
   }

   DrawableWrapperApi14(@Nullable Drawable dr) {
      this.mState = this.mutateConstantState();
      this.setWrappedDrawable(dr);
   }

   private void updateLocalState(@Nullable Resources res) {
      if (this.mState != null && this.mState.mDrawableState != null) {
         Drawable dr = this.newDrawableFromState(this.mState.mDrawableState, res);
         this.setWrappedDrawable(dr);
      }

   }

   protected Drawable newDrawableFromState(@NonNull ConstantState state, @Nullable Resources res) {
      return state.newDrawable(res);
   }

   public void jumpToCurrentState() {
      this.mDrawable.jumpToCurrentState();
   }

   public void draw(Canvas canvas) {
      this.mDrawable.draw(canvas);
   }

   protected void onBoundsChange(Rect bounds) {
      if (this.mDrawable != null) {
         this.mDrawable.setBounds(bounds);
      }

   }

   public void setChangingConfigurations(int configs) {
      this.mDrawable.setChangingConfigurations(configs);
   }

   public int getChangingConfigurations() {
      return super.getChangingConfigurations() | (this.mState != null ? this.mState.getChangingConfigurations() : 0) | this.mDrawable.getChangingConfigurations();
   }

   public void setDither(boolean dither) {
      this.mDrawable.setDither(dither);
   }

   public void setFilterBitmap(boolean filter) {
      this.mDrawable.setFilterBitmap(filter);
   }

   public void setAlpha(int alpha) {
      this.mDrawable.setAlpha(alpha);
   }

   public void setColorFilter(ColorFilter cf) {
      this.mDrawable.setColorFilter(cf);
   }

   public boolean isStateful() {
      ColorStateList tintList = this.isCompatTintEnabled() && this.mState != null ? this.mState.mTint : null;
      return tintList != null && tintList.isStateful() || this.mDrawable.isStateful();
   }

   public boolean setState(int[] stateSet) {
      boolean handled = this.mDrawable.setState(stateSet);
      handled = this.updateTint(stateSet) || handled;
      return handled;
   }

   public int[] getState() {
      return this.mDrawable.getState();
   }

   public Drawable getCurrent() {
      return this.mDrawable.getCurrent();
   }

   public boolean setVisible(boolean visible, boolean restart) {
      return super.setVisible(visible, restart) || this.mDrawable.setVisible(visible, restart);
   }

   public int getOpacity() {
      return this.mDrawable.getOpacity();
   }

   public Region getTransparentRegion() {
      return this.mDrawable.getTransparentRegion();
   }

   public int getIntrinsicWidth() {
      return this.mDrawable.getIntrinsicWidth();
   }

   public int getIntrinsicHeight() {
      return this.mDrawable.getIntrinsicHeight();
   }

   public int getMinimumWidth() {
      return this.mDrawable.getMinimumWidth();
   }

   public int getMinimumHeight() {
      return this.mDrawable.getMinimumHeight();
   }

   public boolean getPadding(Rect padding) {
      return this.mDrawable.getPadding(padding);
   }

   @Nullable
   public ConstantState getConstantState() {
      if (this.mState != null && this.mState.canConstantState()) {
         this.mState.mChangingConfigurations = this.getChangingConfigurations();
         return this.mState;
      } else {
         return null;
      }
   }

   public Drawable mutate() {
      if (!this.mMutated && super.mutate() == this) {
         this.mState = this.mutateConstantState();
         if (this.mDrawable != null) {
            this.mDrawable.mutate();
         }

         if (this.mState != null) {
            this.mState.mDrawableState = this.mDrawable != null ? this.mDrawable.getConstantState() : null;
         }

         this.mMutated = true;
      }

      return this;
   }

   @NonNull
   DrawableWrapperApi14.DrawableWrapperState mutateConstantState() {
      return new DrawableWrapperApi14.DrawableWrapperStateBase(this.mState, (Resources)null);
   }

   public void invalidateDrawable(Drawable who) {
      this.invalidateSelf();
   }

   public void scheduleDrawable(Drawable who, Runnable what, long when) {
      this.scheduleSelf(what, when);
   }

   public void unscheduleDrawable(Drawable who, Runnable what) {
      this.unscheduleSelf(what);
   }

   protected boolean onLevelChange(int level) {
      return this.mDrawable.setLevel(level);
   }

   public void setTint(int tint) {
      this.setTintList(ColorStateList.valueOf(tint));
   }

   public void setTintList(ColorStateList tint) {
      this.mState.mTint = tint;
      this.updateTint(this.getState());
   }

   public void setTintMode(Mode tintMode) {
      this.mState.mTintMode = tintMode;
      this.updateTint(this.getState());
   }

   private boolean updateTint(int[] state) {
      if (!this.isCompatTintEnabled()) {
         return false;
      } else {
         ColorStateList tintList = this.mState.mTint;
         Mode tintMode = this.mState.mTintMode;
         if (tintList != null && tintMode != null) {
            int color = tintList.getColorForState(state, tintList.getDefaultColor());
            if (!this.mColorFilterSet || color != this.mCurrentColor || tintMode != this.mCurrentMode) {
               this.setColorFilter(color, tintMode);
               this.mCurrentColor = color;
               this.mCurrentMode = tintMode;
               this.mColorFilterSet = true;
               return true;
            }
         } else {
            this.mColorFilterSet = false;
            this.clearColorFilter();
         }

         return false;
      }
   }

   public final Drawable getWrappedDrawable() {
      return this.mDrawable;
   }

   public final void setWrappedDrawable(Drawable dr) {
      if (this.mDrawable != null) {
         this.mDrawable.setCallback((Callback)null);
      }

      this.mDrawable = dr;
      if (dr != null) {
         dr.setCallback(this);
         this.setVisible(dr.isVisible(), true);
         this.setState(dr.getState());
         this.setLevel(dr.getLevel());
         this.setBounds(dr.getBounds());
         if (this.mState != null) {
            this.mState.mDrawableState = dr.getConstantState();
         }
      }

      this.invalidateSelf();
   }

   protected boolean isCompatTintEnabled() {
      return true;
   }

   static {
      DEFAULT_TINT_MODE = Mode.SRC_IN;
   }

   private static class DrawableWrapperStateBase extends DrawableWrapperApi14.DrawableWrapperState {
      DrawableWrapperStateBase(@Nullable DrawableWrapperApi14.DrawableWrapperState orig, @Nullable Resources res) {
         super(orig, res);
      }

      public Drawable newDrawable(@Nullable Resources res) {
         return new DrawableWrapperApi14(this, res);
      }
   }

   protected abstract static class DrawableWrapperState extends ConstantState {
      int mChangingConfigurations;
      ConstantState mDrawableState;
      ColorStateList mTint = null;
      Mode mTintMode;

      DrawableWrapperState(@Nullable DrawableWrapperApi14.DrawableWrapperState orig, @Nullable Resources res) {
         this.mTintMode = DrawableWrapperApi14.DEFAULT_TINT_MODE;
         if (orig != null) {
            this.mChangingConfigurations = orig.mChangingConfigurations;
            this.mDrawableState = orig.mDrawableState;
            this.mTint = orig.mTint;
            this.mTintMode = orig.mTintMode;
         }

      }

      public Drawable newDrawable() {
         return this.newDrawable((Resources)null);
      }

      public abstract Drawable newDrawable(@Nullable Resources var1);

      public int getChangingConfigurations() {
         return this.mChangingConfigurations | (this.mDrawableState != null ? this.mDrawableState.getChangingConfigurations() : 0);
      }

      boolean canConstantState() {
         return this.mDrawableState != null;
      }
   }
}
