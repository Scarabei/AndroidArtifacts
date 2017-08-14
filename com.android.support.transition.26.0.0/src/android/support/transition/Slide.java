package android.support.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.xmlpull.v1.XmlPullParser;

public class Slide extends Visibility {
   private static final TimeInterpolator sDecelerate = new DecelerateInterpolator();
   private static final TimeInterpolator sAccelerate = new AccelerateInterpolator();
   private static final String PROPNAME_SCREEN_POSITION = "android:slide:screenPosition";
   private Slide.CalculateSlide mSlideCalculator;
   private int mSlideEdge;
   private static final Slide.CalculateSlide sCalculateLeft = new Slide.CalculateSlideHorizontal() {
      public float getGoneX(ViewGroup sceneRoot, View view) {
         return view.getTranslationX() - (float)sceneRoot.getWidth();
      }
   };
   private static final Slide.CalculateSlide sCalculateStart = new Slide.CalculateSlideHorizontal() {
      public float getGoneX(ViewGroup sceneRoot, View view) {
         boolean isRtl = ViewCompat.getLayoutDirection(sceneRoot) == 1;
         float x;
         if (isRtl) {
            x = view.getTranslationX() + (float)sceneRoot.getWidth();
         } else {
            x = view.getTranslationX() - (float)sceneRoot.getWidth();
         }

         return x;
      }
   };
   private static final Slide.CalculateSlide sCalculateTop = new Slide.CalculateSlideVertical() {
      public float getGoneY(ViewGroup sceneRoot, View view) {
         return view.getTranslationY() - (float)sceneRoot.getHeight();
      }
   };
   private static final Slide.CalculateSlide sCalculateRight = new Slide.CalculateSlideHorizontal() {
      public float getGoneX(ViewGroup sceneRoot, View view) {
         return view.getTranslationX() + (float)sceneRoot.getWidth();
      }
   };
   private static final Slide.CalculateSlide sCalculateEnd = new Slide.CalculateSlideHorizontal() {
      public float getGoneX(ViewGroup sceneRoot, View view) {
         boolean isRtl = ViewCompat.getLayoutDirection(sceneRoot) == 1;
         float x;
         if (isRtl) {
            x = view.getTranslationX() - (float)sceneRoot.getWidth();
         } else {
            x = view.getTranslationX() + (float)sceneRoot.getWidth();
         }

         return x;
      }
   };
   private static final Slide.CalculateSlide sCalculateBottom = new Slide.CalculateSlideVertical() {
      public float getGoneY(ViewGroup sceneRoot, View view) {
         return view.getTranslationY() + (float)sceneRoot.getHeight();
      }
   };

   public Slide() {
      this.mSlideCalculator = sCalculateBottom;
      this.mSlideEdge = 80;
      this.setSlideEdge(80);
   }

   public Slide(int slideEdge) {
      this.mSlideCalculator = sCalculateBottom;
      this.mSlideEdge = 80;
      this.setSlideEdge(slideEdge);
   }

   public Slide(Context context, AttributeSet attrs) {
      super(context, attrs);
      this.mSlideCalculator = sCalculateBottom;
      this.mSlideEdge = 80;
      TypedArray a = context.obtainStyledAttributes(attrs, Styleable.SLIDE);
      int edge = TypedArrayUtils.getNamedInt(a, (XmlPullParser)attrs, "slideEdge", 0, 80);
      a.recycle();
      this.setSlideEdge(edge);
   }

   private void captureValues(TransitionValues transitionValues) {
      View view = transitionValues.view;
      int[] position = new int[2];
      view.getLocationOnScreen(position);
      transitionValues.values.put("android:slide:screenPosition", position);
   }

   public void captureStartValues(@NonNull TransitionValues transitionValues) {
      super.captureStartValues(transitionValues);
      this.captureValues(transitionValues);
   }

   public void captureEndValues(@NonNull TransitionValues transitionValues) {
      super.captureEndValues(transitionValues);
      this.captureValues(transitionValues);
   }

   public void setSlideEdge(int slideEdge) {
      switch(slideEdge) {
      case 3:
         this.mSlideCalculator = sCalculateLeft;
         break;
      case 5:
         this.mSlideCalculator = sCalculateRight;
         break;
      case 48:
         this.mSlideCalculator = sCalculateTop;
         break;
      case 80:
         this.mSlideCalculator = sCalculateBottom;
         break;
      case 8388611:
         this.mSlideCalculator = sCalculateStart;
         break;
      case 8388613:
         this.mSlideCalculator = sCalculateEnd;
         break;
      default:
         throw new IllegalArgumentException("Invalid slide direction");
      }

      this.mSlideEdge = slideEdge;
      SidePropagation propagation = new SidePropagation();
      propagation.setSide(slideEdge);
      this.setPropagation(propagation);
   }

   public int getSlideEdge() {
      return this.mSlideEdge;
   }

   public Animator onAppear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
      if (endValues == null) {
         return null;
      } else {
         int[] position = (int[])((int[])endValues.values.get("android:slide:screenPosition"));
         float endX = view.getTranslationX();
         float endY = view.getTranslationY();
         float startX = this.mSlideCalculator.getGoneX(sceneRoot, view);
         float startY = this.mSlideCalculator.getGoneY(sceneRoot, view);
         return TranslationAnimationCreator.createAnimation(view, endValues, position[0], position[1], startX, startY, endX, endY, sDecelerate);
      }
   }

   public Animator onDisappear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
      if (startValues == null) {
         return null;
      } else {
         int[] position = (int[])((int[])startValues.values.get("android:slide:screenPosition"));
         float startX = view.getTranslationX();
         float startY = view.getTranslationY();
         float endX = this.mSlideCalculator.getGoneX(sceneRoot, view);
         float endY = this.mSlideCalculator.getGoneY(sceneRoot, view);
         return TranslationAnimationCreator.createAnimation(view, startValues, position[0], position[1], startX, startY, endX, endY, sAccelerate);
      }
   }

   private abstract static class CalculateSlideVertical implements Slide.CalculateSlide {
      private CalculateSlideVertical() {
      }

      public float getGoneX(ViewGroup sceneRoot, View view) {
         return view.getTranslationX();
      }

      // $FF: synthetic method
      CalculateSlideVertical(Object x0) {
         this();
      }
   }

   private abstract static class CalculateSlideHorizontal implements Slide.CalculateSlide {
      private CalculateSlideHorizontal() {
      }

      public float getGoneY(ViewGroup sceneRoot, View view) {
         return view.getTranslationY();
      }

      // $FF: synthetic method
      CalculateSlideHorizontal(Object x0) {
         this();
      }
   }

   private interface CalculateSlide {
      float getGoneX(ViewGroup var1, View var2);

      float getGoneY(ViewGroup var1, View var2);
   }

   @Retention(RetentionPolicy.SOURCE)
   @RestrictTo({Scope.LIBRARY_GROUP})
   public @interface GravityFlag {
   }
}
