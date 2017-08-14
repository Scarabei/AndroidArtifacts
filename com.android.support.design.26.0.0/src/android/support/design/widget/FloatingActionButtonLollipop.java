package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

@RequiresApi(21)
class FloatingActionButtonLollipop extends FloatingActionButtonImpl {
   private InsetDrawable mInsetDrawable;

   FloatingActionButtonLollipop(VisibilityAwareImageButton view, ShadowViewDelegate shadowViewDelegate) {
      super(view, shadowViewDelegate);
   }

   void setBackgroundDrawable(ColorStateList backgroundTint, Mode backgroundTintMode, int rippleColor, int borderWidth) {
      this.mShapeDrawable = DrawableCompat.wrap(this.createShapeDrawable());
      DrawableCompat.setTintList(this.mShapeDrawable, backgroundTint);
      if (backgroundTintMode != null) {
         DrawableCompat.setTintMode(this.mShapeDrawable, backgroundTintMode);
      }

      Object rippleContent;
      if (borderWidth > 0) {
         this.mBorderDrawable = this.createBorderDrawable(borderWidth, backgroundTint);
         rippleContent = new LayerDrawable(new Drawable[]{this.mBorderDrawable, this.mShapeDrawable});
      } else {
         this.mBorderDrawable = null;
         rippleContent = this.mShapeDrawable;
      }

      this.mRippleDrawable = new RippleDrawable(ColorStateList.valueOf(rippleColor), (Drawable)rippleContent, (Drawable)null);
      this.mContentBackground = this.mRippleDrawable;
      this.mShadowViewDelegate.setBackgroundDrawable(this.mRippleDrawable);
   }

   void setRippleColor(int rippleColor) {
      if (this.mRippleDrawable instanceof RippleDrawable) {
         ((RippleDrawable)this.mRippleDrawable).setColor(ColorStateList.valueOf(rippleColor));
      } else {
         super.setRippleColor(rippleColor);
      }

   }

   void onElevationsChanged(float elevation, float pressedTranslationZ) {
      if (VERSION.SDK_INT == 21) {
         if (this.mView.isEnabled()) {
            this.mView.setElevation(elevation);
            if (!this.mView.isFocused() && !this.mView.isPressed()) {
               this.mView.setTranslationZ(0.0F);
            } else {
               this.mView.setTranslationZ(pressedTranslationZ);
            }
         } else {
            this.mView.setElevation(0.0F);
            this.mView.setTranslationZ(0.0F);
         }
      } else {
         android.animation.StateListAnimator stateListAnimator = new android.animation.StateListAnimator();
         AnimatorSet set = new AnimatorSet();
         set.play(ObjectAnimator.ofFloat(this.mView, "elevation", new float[]{elevation}).setDuration(0L)).with(ObjectAnimator.ofFloat(this.mView, View.TRANSLATION_Z, new float[]{pressedTranslationZ}).setDuration(100L));
         set.setInterpolator(ANIM_INTERPOLATOR);
         stateListAnimator.addState(PRESSED_ENABLED_STATE_SET, set);
         set = new AnimatorSet();
         set.play(ObjectAnimator.ofFloat(this.mView, "elevation", new float[]{elevation}).setDuration(0L)).with(ObjectAnimator.ofFloat(this.mView, View.TRANSLATION_Z, new float[]{pressedTranslationZ}).setDuration(100L));
         set.setInterpolator(ANIM_INTERPOLATOR);
         stateListAnimator.addState(FOCUSED_ENABLED_STATE_SET, set);
         set = new AnimatorSet();
         List animators = new ArrayList();
         animators.add(ObjectAnimator.ofFloat(this.mView, "elevation", new float[]{elevation}).setDuration(0L));
         if (VERSION.SDK_INT >= 22 && VERSION.SDK_INT <= 24) {
            animators.add(ObjectAnimator.ofFloat(this.mView, View.TRANSLATION_Z, new float[]{this.mView.getTranslationZ()}).setDuration(100L));
         }

         animators.add(ObjectAnimator.ofFloat(this.mView, View.TRANSLATION_Z, new float[]{0.0F}).setDuration(100L));
         set.playSequentially((Animator[])animators.toArray(new ObjectAnimator[0]));
         set.setInterpolator(ANIM_INTERPOLATOR);
         stateListAnimator.addState(ENABLED_STATE_SET, set);
         set = new AnimatorSet();
         set.play(ObjectAnimator.ofFloat(this.mView, "elevation", new float[]{0.0F}).setDuration(0L)).with(ObjectAnimator.ofFloat(this.mView, View.TRANSLATION_Z, new float[]{0.0F}).setDuration(0L));
         set.setInterpolator(ANIM_INTERPOLATOR);
         stateListAnimator.addState(EMPTY_STATE_SET, set);
         this.mView.setStateListAnimator(stateListAnimator);
      }

      if (this.mShadowViewDelegate.isCompatPaddingEnabled()) {
         this.updatePadding();
      }

   }

   public float getElevation() {
      return this.mView.getElevation();
   }

   void onCompatShadowChanged() {
      this.updatePadding();
   }

   void onPaddingUpdated(Rect padding) {
      if (this.mShadowViewDelegate.isCompatPaddingEnabled()) {
         this.mInsetDrawable = new InsetDrawable(this.mRippleDrawable, padding.left, padding.top, padding.right, padding.bottom);
         this.mShadowViewDelegate.setBackgroundDrawable(this.mInsetDrawable);
      } else {
         this.mShadowViewDelegate.setBackgroundDrawable(this.mRippleDrawable);
      }

   }

   void onDrawableStateChanged(int[] state) {
   }

   void jumpDrawableToCurrentState() {
   }

   boolean requirePreDrawListener() {
      return false;
   }

   CircularBorderDrawable newCircularDrawable() {
      return new CircularBorderDrawableLollipop();
   }

   GradientDrawable newGradientDrawableForShape() {
      return new FloatingActionButtonLollipop.AlwaysStatefulGradientDrawable();
   }

   void getPadding(Rect rect) {
      if (this.mShadowViewDelegate.isCompatPaddingEnabled()) {
         float radius = this.mShadowViewDelegate.getRadius();
         float maxShadowSize = this.getElevation() + this.mPressedTranslationZ;
         int hPadding = (int)Math.ceil((double)ShadowDrawableWrapper.calculateHorizontalPadding(maxShadowSize, radius, false));
         int vPadding = (int)Math.ceil((double)ShadowDrawableWrapper.calculateVerticalPadding(maxShadowSize, radius, false));
         rect.set(hPadding, vPadding, hPadding, vPadding);
      } else {
         rect.set(0, 0, 0, 0);
      }

   }

   static class AlwaysStatefulGradientDrawable extends GradientDrawable {
      public boolean isStateful() {
         return true;
      }
   }
}
