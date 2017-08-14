package android.support.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.transition.R.id;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

public class Explode extends Visibility {
   private static final TimeInterpolator sDecelerate = new DecelerateInterpolator();
   private static final TimeInterpolator sAccelerate = new AccelerateInterpolator();
   private static final String PROPNAME_SCREEN_BOUNDS = "android:explode:screenBounds";
   private int[] mTempLoc = new int[2];

   public Explode() {
      this.setPropagation(new CircularPropagation());
   }

   public Explode(Context context, AttributeSet attrs) {
      super(context, attrs);
      this.setPropagation(new CircularPropagation());
   }

   private void captureValues(TransitionValues transitionValues) {
      View view = transitionValues.view;
      view.getLocationOnScreen(this.mTempLoc);
      int left = this.mTempLoc[0];
      int top = this.mTempLoc[1];
      int right = left + view.getWidth();
      int bottom = top + view.getHeight();
      transitionValues.values.put("android:explode:screenBounds", new Rect(left, top, right, bottom));
   }

   public void captureStartValues(@NonNull TransitionValues transitionValues) {
      super.captureStartValues(transitionValues);
      this.captureValues(transitionValues);
   }

   public void captureEndValues(@NonNull TransitionValues transitionValues) {
      super.captureEndValues(transitionValues);
      this.captureValues(transitionValues);
   }

   public Animator onAppear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
      if (endValues == null) {
         return null;
      } else {
         Rect bounds = (Rect)endValues.values.get("android:explode:screenBounds");
         float endX = view.getTranslationX();
         float endY = view.getTranslationY();
         this.calculateOut(sceneRoot, bounds, this.mTempLoc);
         float startX = endX + (float)this.mTempLoc[0];
         float startY = endY + (float)this.mTempLoc[1];
         return TranslationAnimationCreator.createAnimation(view, endValues, bounds.left, bounds.top, startX, startY, endX, endY, sDecelerate);
      }
   }

   public Animator onDisappear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
      if (startValues == null) {
         return null;
      } else {
         Rect bounds = (Rect)startValues.values.get("android:explode:screenBounds");
         int viewPosX = bounds.left;
         int viewPosY = bounds.top;
         float startX = view.getTranslationX();
         float startY = view.getTranslationY();
         float endX = startX;
         float endY = startY;
         int[] interruptedPosition = (int[])((int[])startValues.view.getTag(id.transition_position));
         if (interruptedPosition != null) {
            endX = startX + (float)(interruptedPosition[0] - bounds.left);
            endY = startY + (float)(interruptedPosition[1] - bounds.top);
            bounds.offsetTo(interruptedPosition[0], interruptedPosition[1]);
         }

         this.calculateOut(sceneRoot, bounds, this.mTempLoc);
         endX += (float)this.mTempLoc[0];
         endY += (float)this.mTempLoc[1];
         return TranslationAnimationCreator.createAnimation(view, startValues, viewPosX, viewPosY, startX, startY, endX, endY, sAccelerate);
      }
   }

   private void calculateOut(View sceneRoot, Rect bounds, int[] outVector) {
      sceneRoot.getLocationOnScreen(this.mTempLoc);
      int sceneRootX = this.mTempLoc[0];
      int sceneRootY = this.mTempLoc[1];
      Rect epicenter = this.getEpicenter();
      int focalX;
      int focalY;
      if (epicenter == null) {
         focalX = sceneRootX + sceneRoot.getWidth() / 2 + Math.round(sceneRoot.getTranslationX());
         focalY = sceneRootY + sceneRoot.getHeight() / 2 + Math.round(sceneRoot.getTranslationY());
      } else {
         focalX = epicenter.centerX();
         focalY = epicenter.centerY();
      }

      int centerX = bounds.centerX();
      int centerY = bounds.centerY();
      float xVector = (float)(centerX - focalX);
      float yVector = (float)(centerY - focalY);
      if (xVector == 0.0F && yVector == 0.0F) {
         xVector = (float)(Math.random() * 2.0D) - 1.0F;
         yVector = (float)(Math.random() * 2.0D) - 1.0F;
      }

      float vectorSize = calculateDistance(xVector, yVector);
      xVector /= vectorSize;
      yVector /= vectorSize;
      float maxDistance = calculateMaxDistance(sceneRoot, focalX - sceneRootX, focalY - sceneRootY);
      outVector[0] = Math.round(maxDistance * xVector);
      outVector[1] = Math.round(maxDistance * yVector);
   }

   private static float calculateMaxDistance(View sceneRoot, int focalX, int focalY) {
      int maxX = Math.max(focalX, sceneRoot.getWidth() - focalX);
      int maxY = Math.max(focalY, sceneRoot.getHeight() - focalY);
      return calculateDistance((float)maxX, (float)maxY);
   }

   private static float calculateDistance(float x, float y) {
      return (float)Math.sqrt((double)(x * x + y * y));
   }
}
