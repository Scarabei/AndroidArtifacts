package android.support.design.internal;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.transition.Transition;
import android.support.transition.TransitionValues;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.Map;

@RestrictTo({Scope.LIBRARY_GROUP})
@RequiresApi(14)
public class TextScale extends Transition {
   private static final String PROPNAME_SCALE = "android:textscale:scale";

   public void captureStartValues(TransitionValues transitionValues) {
      this.captureValues(transitionValues);
   }

   public void captureEndValues(TransitionValues transitionValues) {
      this.captureValues(transitionValues);
   }

   private void captureValues(TransitionValues transitionValues) {
      if (transitionValues.view instanceof TextView) {
         TextView textview = (TextView)transitionValues.view;
         transitionValues.values.put("android:textscale:scale", textview.getScaleX());
      }

   }

   public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
      if (startValues != null && endValues != null && startValues.view instanceof TextView && endValues.view instanceof TextView) {
         final TextView view = (TextView)endValues.view;
         Map startVals = startValues.values;
         Map endVals = endValues.values;
         float startSize = startVals.get("android:textscale:scale") != null ? ((Float)startVals.get("android:textscale:scale")).floatValue() : 1.0F;
         float endSize = endVals.get("android:textscale:scale") != null ? ((Float)endVals.get("android:textscale:scale")).floatValue() : 1.0F;
         if (startSize == endSize) {
            return null;
         } else {
            ValueAnimator animator = ValueAnimator.ofFloat(new float[]{startSize, endSize});
            animator.addUpdateListener(new AnimatorUpdateListener() {
               public void onAnimationUpdate(ValueAnimator valueAnimator) {
                  float animatedValue = ((Float)valueAnimator.getAnimatedValue()).floatValue();
                  view.setScaleX(animatedValue);
                  view.setScaleY(animatedValue);
               }
            });
            return animator;
         }
      } else {
         return null;
      }
   }
}
