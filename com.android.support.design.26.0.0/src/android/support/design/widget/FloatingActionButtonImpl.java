package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.R.color;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.animation.Interpolator;

@RequiresApi(14)
class FloatingActionButtonImpl {
   static final Interpolator ANIM_INTERPOLATOR;
   static final long PRESSED_ANIM_DURATION = 100L;
   static final long PRESSED_ANIM_DELAY = 100L;
   static final int ANIM_STATE_NONE = 0;
   static final int ANIM_STATE_HIDING = 1;
   static final int ANIM_STATE_SHOWING = 2;
   int mAnimState = 0;
   private final StateListAnimator mStateListAnimator;
   ShadowDrawableWrapper mShadowDrawable;
   private float mRotation;
   Drawable mShapeDrawable;
   Drawable mRippleDrawable;
   CircularBorderDrawable mBorderDrawable;
   Drawable mContentBackground;
   float mElevation;
   float mPressedTranslationZ;
   static final int SHOW_HIDE_ANIM_DURATION = 200;
   static final int[] PRESSED_ENABLED_STATE_SET;
   static final int[] FOCUSED_ENABLED_STATE_SET;
   static final int[] ENABLED_STATE_SET;
   static final int[] EMPTY_STATE_SET;
   final VisibilityAwareImageButton mView;
   final ShadowViewDelegate mShadowViewDelegate;
   private final Rect mTmpRect = new Rect();
   private OnPreDrawListener mPreDrawListener;

   FloatingActionButtonImpl(VisibilityAwareImageButton view, ShadowViewDelegate shadowViewDelegate) {
      this.mView = view;
      this.mShadowViewDelegate = shadowViewDelegate;
      this.mStateListAnimator = new StateListAnimator();
      this.mStateListAnimator.addState(PRESSED_ENABLED_STATE_SET, this.createAnimator(new FloatingActionButtonImpl.ElevateToTranslationZAnimation()));
      this.mStateListAnimator.addState(FOCUSED_ENABLED_STATE_SET, this.createAnimator(new FloatingActionButtonImpl.ElevateToTranslationZAnimation()));
      this.mStateListAnimator.addState(ENABLED_STATE_SET, this.createAnimator(new FloatingActionButtonImpl.ResetElevationAnimation()));
      this.mStateListAnimator.addState(EMPTY_STATE_SET, this.createAnimator(new FloatingActionButtonImpl.DisabledElevationAnimation()));
      this.mRotation = this.mView.getRotation();
   }

   void setBackgroundDrawable(ColorStateList backgroundTint, Mode backgroundTintMode, int rippleColor, int borderWidth) {
      this.mShapeDrawable = DrawableCompat.wrap(this.createShapeDrawable());
      DrawableCompat.setTintList(this.mShapeDrawable, backgroundTint);
      if (backgroundTintMode != null) {
         DrawableCompat.setTintMode(this.mShapeDrawable, backgroundTintMode);
      }

      GradientDrawable touchFeedbackShape = this.createShapeDrawable();
      this.mRippleDrawable = DrawableCompat.wrap(touchFeedbackShape);
      DrawableCompat.setTintList(this.mRippleDrawable, createColorStateList(rippleColor));
      Drawable[] layers;
      if (borderWidth > 0) {
         this.mBorderDrawable = this.createBorderDrawable(borderWidth, backgroundTint);
         layers = new Drawable[]{this.mBorderDrawable, this.mShapeDrawable, this.mRippleDrawable};
      } else {
         this.mBorderDrawable = null;
         layers = new Drawable[]{this.mShapeDrawable, this.mRippleDrawable};
      }

      this.mContentBackground = new LayerDrawable(layers);
      this.mShadowDrawable = new ShadowDrawableWrapper(this.mView.getContext(), this.mContentBackground, this.mShadowViewDelegate.getRadius(), this.mElevation, this.mElevation + this.mPressedTranslationZ);
      this.mShadowDrawable.setAddPaddingForCorners(false);
      this.mShadowViewDelegate.setBackgroundDrawable(this.mShadowDrawable);
   }

   void setBackgroundTintList(ColorStateList tint) {
      if (this.mShapeDrawable != null) {
         DrawableCompat.setTintList(this.mShapeDrawable, tint);
      }

      if (this.mBorderDrawable != null) {
         this.mBorderDrawable.setBorderTint(tint);
      }

   }

   void setBackgroundTintMode(Mode tintMode) {
      if (this.mShapeDrawable != null) {
         DrawableCompat.setTintMode(this.mShapeDrawable, tintMode);
      }

   }

   void setRippleColor(int rippleColor) {
      if (this.mRippleDrawable != null) {
         DrawableCompat.setTintList(this.mRippleDrawable, createColorStateList(rippleColor));
      }

   }

   final void setElevation(float elevation) {
      if (this.mElevation != elevation) {
         this.mElevation = elevation;
         this.onElevationsChanged(elevation, this.mPressedTranslationZ);
      }

   }

   float getElevation() {
      return this.mElevation;
   }

   final void setPressedTranslationZ(float translationZ) {
      if (this.mPressedTranslationZ != translationZ) {
         this.mPressedTranslationZ = translationZ;
         this.onElevationsChanged(this.mElevation, translationZ);
      }

   }

   void onElevationsChanged(float elevation, float pressedTranslationZ) {
      if (this.mShadowDrawable != null) {
         this.mShadowDrawable.setShadowSize(elevation, elevation + this.mPressedTranslationZ);
         this.updatePadding();
      }

   }

   void onDrawableStateChanged(int[] state) {
      this.mStateListAnimator.setState(state);
   }

   void jumpDrawableToCurrentState() {
      this.mStateListAnimator.jumpToCurrentState();
   }

   void hide(@Nullable final FloatingActionButtonImpl.InternalVisibilityChangedListener listener, final boolean fromUser) {
      if (!this.isOrWillBeHidden()) {
         this.mView.animate().cancel();
         if (this.shouldAnimateVisibilityChange()) {
            this.mAnimState = 1;
            this.mView.animate().scaleX(0.0F).scaleY(0.0F).alpha(0.0F).setDuration(200L).setInterpolator(AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR).setListener(new AnimatorListenerAdapter() {
               private boolean mCancelled;

               public void onAnimationStart(Animator animation) {
                  FloatingActionButtonImpl.this.mView.internalSetVisibility(0, fromUser);
                  this.mCancelled = false;
               }

               public void onAnimationCancel(Animator animation) {
                  this.mCancelled = true;
               }

               public void onAnimationEnd(Animator animation) {
                  FloatingActionButtonImpl.this.mAnimState = 0;
                  if (!this.mCancelled) {
                     FloatingActionButtonImpl.this.mView.internalSetVisibility(fromUser ? 8 : 4, fromUser);
                     if (listener != null) {
                        listener.onHidden();
                     }
                  }

               }
            });
         } else {
            this.mView.internalSetVisibility(fromUser ? 8 : 4, fromUser);
            if (listener != null) {
               listener.onHidden();
            }
         }

      }
   }

   void show(@Nullable final FloatingActionButtonImpl.InternalVisibilityChangedListener listener, final boolean fromUser) {
      if (!this.isOrWillBeShown()) {
         this.mView.animate().cancel();
         if (this.shouldAnimateVisibilityChange()) {
            this.mAnimState = 2;
            if (this.mView.getVisibility() != 0) {
               this.mView.setAlpha(0.0F);
               this.mView.setScaleY(0.0F);
               this.mView.setScaleX(0.0F);
            }

            this.mView.animate().scaleX(1.0F).scaleY(1.0F).alpha(1.0F).setDuration(200L).setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR).setListener(new AnimatorListenerAdapter() {
               public void onAnimationStart(Animator animation) {
                  FloatingActionButtonImpl.this.mView.internalSetVisibility(0, fromUser);
               }

               public void onAnimationEnd(Animator animation) {
                  FloatingActionButtonImpl.this.mAnimState = 0;
                  if (listener != null) {
                     listener.onShown();
                  }

               }
            });
         } else {
            this.mView.internalSetVisibility(0, fromUser);
            this.mView.setAlpha(1.0F);
            this.mView.setScaleY(1.0F);
            this.mView.setScaleX(1.0F);
            if (listener != null) {
               listener.onShown();
            }
         }

      }
   }

   final Drawable getContentBackground() {
      return this.mContentBackground;
   }

   void onCompatShadowChanged() {
   }

   final void updatePadding() {
      Rect rect = this.mTmpRect;
      this.getPadding(rect);
      this.onPaddingUpdated(rect);
      this.mShadowViewDelegate.setShadowPadding(rect.left, rect.top, rect.right, rect.bottom);
   }

   void getPadding(Rect rect) {
      this.mShadowDrawable.getPadding(rect);
   }

   void onPaddingUpdated(Rect padding) {
   }

   void onAttachedToWindow() {
      if (this.requirePreDrawListener()) {
         this.ensurePreDrawListener();
         this.mView.getViewTreeObserver().addOnPreDrawListener(this.mPreDrawListener);
      }

   }

   void onDetachedFromWindow() {
      if (this.mPreDrawListener != null) {
         this.mView.getViewTreeObserver().removeOnPreDrawListener(this.mPreDrawListener);
         this.mPreDrawListener = null;
      }

   }

   boolean requirePreDrawListener() {
      return true;
   }

   CircularBorderDrawable createBorderDrawable(int borderWidth, ColorStateList backgroundTint) {
      Context context = this.mView.getContext();
      CircularBorderDrawable borderDrawable = this.newCircularDrawable();
      borderDrawable.setGradientColors(ContextCompat.getColor(context, color.design_fab_stroke_top_outer_color), ContextCompat.getColor(context, color.design_fab_stroke_top_inner_color), ContextCompat.getColor(context, color.design_fab_stroke_end_inner_color), ContextCompat.getColor(context, color.design_fab_stroke_end_outer_color));
      borderDrawable.setBorderWidth((float)borderWidth);
      borderDrawable.setBorderTint(backgroundTint);
      return borderDrawable;
   }

   CircularBorderDrawable newCircularDrawable() {
      return new CircularBorderDrawable();
   }

   void onPreDraw() {
      float rotation = this.mView.getRotation();
      if (this.mRotation != rotation) {
         this.mRotation = rotation;
         this.updateFromViewRotation();
      }

   }

   private void ensurePreDrawListener() {
      if (this.mPreDrawListener == null) {
         this.mPreDrawListener = new OnPreDrawListener() {
            public boolean onPreDraw() {
               FloatingActionButtonImpl.this.onPreDraw();
               return true;
            }
         };
      }

   }

   GradientDrawable createShapeDrawable() {
      GradientDrawable d = this.newGradientDrawableForShape();
      d.setShape(1);
      d.setColor(-1);
      return d;
   }

   GradientDrawable newGradientDrawableForShape() {
      return new GradientDrawable();
   }

   boolean isOrWillBeShown() {
      if (this.mView.getVisibility() != 0) {
         return this.mAnimState == 2;
      } else {
         return this.mAnimState != 1;
      }
   }

   boolean isOrWillBeHidden() {
      if (this.mView.getVisibility() == 0) {
         return this.mAnimState == 1;
      } else {
         return this.mAnimState != 2;
      }
   }

   private ValueAnimator createAnimator(@NonNull FloatingActionButtonImpl.ShadowAnimatorImpl impl) {
      ValueAnimator animator = new ValueAnimator();
      animator.setInterpolator(ANIM_INTERPOLATOR);
      animator.setDuration(100L);
      animator.addListener(impl);
      animator.addUpdateListener(impl);
      animator.setFloatValues(new float[]{0.0F, 1.0F});
      return animator;
   }

   private static ColorStateList createColorStateList(int selectedColor) {
      int[][] states = new int[3][];
      int[] colors = new int[3];
      int i = 0;
      states[i] = FOCUSED_ENABLED_STATE_SET;
      colors[i] = selectedColor;
      int i = i + 1;
      states[i] = PRESSED_ENABLED_STATE_SET;
      colors[i] = selectedColor;
      ++i;
      states[i] = new int[0];
      colors[i] = 0;
      ++i;
      return new ColorStateList(states, colors);
   }

   private boolean shouldAnimateVisibilityChange() {
      return ViewCompat.isLaidOut(this.mView) && !this.mView.isInEditMode();
   }

   private void updateFromViewRotation() {
      if (VERSION.SDK_INT == 19) {
         if (this.mRotation % 90.0F != 0.0F) {
            if (this.mView.getLayerType() != 1) {
               this.mView.setLayerType(1, (Paint)null);
            }
         } else if (this.mView.getLayerType() != 0) {
            this.mView.setLayerType(0, (Paint)null);
         }
      }

      if (this.mShadowDrawable != null) {
         this.mShadowDrawable.setRotation(-this.mRotation);
      }

      if (this.mBorderDrawable != null) {
         this.mBorderDrawable.setRotation(-this.mRotation);
      }

   }

   static {
      ANIM_INTERPOLATOR = AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR;
      PRESSED_ENABLED_STATE_SET = new int[]{16842919, 16842910};
      FOCUSED_ENABLED_STATE_SET = new int[]{16842908, 16842910};
      ENABLED_STATE_SET = new int[]{16842910};
      EMPTY_STATE_SET = new int[0];
   }

   private class DisabledElevationAnimation extends FloatingActionButtonImpl.ShadowAnimatorImpl {
      DisabledElevationAnimation() {
         super(null);
      }

      protected float getTargetShadowSize() {
         return 0.0F;
      }
   }

   private class ElevateToTranslationZAnimation extends FloatingActionButtonImpl.ShadowAnimatorImpl {
      ElevateToTranslationZAnimation() {
         super(null);
      }

      protected float getTargetShadowSize() {
         return FloatingActionButtonImpl.this.mElevation + FloatingActionButtonImpl.this.mPressedTranslationZ;
      }
   }

   private class ResetElevationAnimation extends FloatingActionButtonImpl.ShadowAnimatorImpl {
      ResetElevationAnimation() {
         super(null);
      }

      protected float getTargetShadowSize() {
         return FloatingActionButtonImpl.this.mElevation;
      }
   }

   private abstract class ShadowAnimatorImpl extends AnimatorListenerAdapter implements AnimatorUpdateListener {
      private boolean mValidValues;
      private float mShadowSizeStart;
      private float mShadowSizeEnd;

      private ShadowAnimatorImpl() {
      }

      public void onAnimationUpdate(ValueAnimator animator) {
         if (!this.mValidValues) {
            this.mShadowSizeStart = FloatingActionButtonImpl.this.mShadowDrawable.getShadowSize();
            this.mShadowSizeEnd = this.getTargetShadowSize();
            this.mValidValues = true;
         }

         FloatingActionButtonImpl.this.mShadowDrawable.setShadowSize(this.mShadowSizeStart + (this.mShadowSizeEnd - this.mShadowSizeStart) * animator.getAnimatedFraction());
      }

      public void onAnimationEnd(Animator animator) {
         FloatingActionButtonImpl.this.mShadowDrawable.setShadowSize(this.mShadowSizeEnd);
         this.mValidValues = false;
      }

      protected abstract float getTargetShadowSize();

      // $FF: synthetic method
      ShadowAnimatorImpl(Object x1) {
         this();
      }
   }

   interface InternalVisibilityChangedListener {
      void onShown();

      void onHidden();
   }
}
