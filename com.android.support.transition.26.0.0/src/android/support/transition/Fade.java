package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class Fade extends Visibility {
   private static final String PROPNAME_TRANSITION_ALPHA = "android:fade:transitionAlpha";
   private static final String LOG_TAG = "Fade";
   public static final int IN = 1;
   public static final int OUT = 2;

   public Fade(int fadingMode) {
      this.setMode(fadingMode);
   }

   public Fade() {
   }

   public Fade(Context context, AttributeSet attrs) {
      super(context, attrs);
      TypedArray a = context.obtainStyledAttributes(attrs, Styleable.FADE);
      int fadingMode = TypedArrayUtils.getNamedInt(a, (XmlResourceParser)attrs, "fadingMode", 0, this.getMode());
      this.setMode(fadingMode);
      a.recycle();
   }

   public void captureStartValues(@NonNull TransitionValues transitionValues) {
      super.captureStartValues(transitionValues);
      transitionValues.values.put("android:fade:transitionAlpha", ViewUtils.getTransitionAlpha(transitionValues.view));
   }

   private Animator createAnimation(final View view, float startAlpha, float endAlpha) {
      if (startAlpha == endAlpha) {
         return null;
      } else {
         ViewUtils.setTransitionAlpha(view, startAlpha);
         ObjectAnimator anim = ObjectAnimator.ofFloat(view, ViewUtils.TRANSITION_ALPHA, new float[]{endAlpha});
         Fade.FadeAnimatorListener listener = new Fade.FadeAnimatorListener(view);
         anim.addListener(listener);
         this.addListener(new TransitionListenerAdapter() {
            public void onTransitionEnd(@NonNull Transition transition) {
               ViewUtils.setTransitionAlpha(view, 1.0F);
               ViewUtils.clearNonTransitionAlpha(view);
               transition.removeListener(this);
            }
         });
         return anim;
      }
   }

   public Animator onAppear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
      float startAlpha = getStartAlpha(startValues, 0.0F);
      if (startAlpha == 1.0F) {
         startAlpha = 0.0F;
      }

      return this.createAnimation(view, startAlpha, 1.0F);
   }

   public Animator onDisappear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
      ViewUtils.saveNonTransitionAlpha(view);
      float startAlpha = getStartAlpha(startValues, 1.0F);
      return this.createAnimation(view, startAlpha, 0.0F);
   }

   private static float getStartAlpha(TransitionValues startValues, float fallbackValue) {
      float startAlpha = fallbackValue;
      if (startValues != null) {
         Float startAlphaFloat = (Float)startValues.values.get("android:fade:transitionAlpha");
         if (startAlphaFloat != null) {
            startAlpha = startAlphaFloat.floatValue();
         }
      }

      return startAlpha;
   }

   private static class FadeAnimatorListener extends AnimatorListenerAdapter {
      private final View mView;
      private boolean mLayerTypeChanged = false;

      FadeAnimatorListener(View view) {
         this.mView = view;
      }

      public void onAnimationStart(Animator animation) {
         if (ViewCompat.hasOverlappingRendering(this.mView) && this.mView.getLayerType() == 0) {
            this.mLayerTypeChanged = true;
            this.mView.setLayerType(2, (Paint)null);
         }

      }

      public void onAnimationEnd(Animator animation) {
         ViewUtils.setTransitionAlpha(this.mView, 1.0F);
         if (this.mLayerTypeChanged) {
            this.mView.setLayerType(0, (Paint)null);
         }

      }
   }
}
