package android.support.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.Map;

public class ChangeImageTransform extends Transition {
   private static final String PROPNAME_MATRIX = "android:changeImageTransform:matrix";
   private static final String PROPNAME_BOUNDS = "android:changeImageTransform:bounds";
   private static final String[] sTransitionProperties = new String[]{"android:changeImageTransform:matrix", "android:changeImageTransform:bounds"};
   private static final TypeEvaluator NULL_MATRIX_EVALUATOR = new TypeEvaluator() {
      public Matrix evaluate(float fraction, Matrix startValue, Matrix endValue) {
         return null;
      }
   };
   private static final Property ANIMATED_TRANSFORM_PROPERTY = new Property(Matrix.class, "animatedTransform") {
      public void set(ImageView view, Matrix matrix) {
         ImageViewUtils.animateTransform(view, matrix);
      }

      public Matrix get(ImageView object) {
         return null;
      }
   };

   public ChangeImageTransform() {
   }

   public ChangeImageTransform(Context context, AttributeSet attrs) {
      super(context, attrs);
   }

   private void captureValues(TransitionValues transitionValues) {
      View view = transitionValues.view;
      if (view instanceof ImageView && view.getVisibility() == 0) {
         ImageView imageView = (ImageView)view;
         Drawable drawable = imageView.getDrawable();
         if (drawable != null) {
            Map values = transitionValues.values;
            int left = view.getLeft();
            int top = view.getTop();
            int right = view.getRight();
            int bottom = view.getBottom();
            Rect bounds = new Rect(left, top, right, bottom);
            values.put("android:changeImageTransform:bounds", bounds);
            values.put("android:changeImageTransform:matrix", copyImageMatrix(imageView));
         }
      }
   }

   public void captureStartValues(@NonNull TransitionValues transitionValues) {
      this.captureValues(transitionValues);
   }

   public void captureEndValues(@NonNull TransitionValues transitionValues) {
      this.captureValues(transitionValues);
   }

   public String[] getTransitionProperties() {
      return sTransitionProperties;
   }

   public Animator createAnimator(@NonNull ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
      if (startValues != null && endValues != null) {
         Rect startBounds = (Rect)startValues.values.get("android:changeImageTransform:bounds");
         Rect endBounds = (Rect)endValues.values.get("android:changeImageTransform:bounds");
         if (startBounds != null && endBounds != null) {
            Matrix startMatrix = (Matrix)startValues.values.get("android:changeImageTransform:matrix");
            Matrix endMatrix = (Matrix)endValues.values.get("android:changeImageTransform:matrix");
            boolean matricesEqual = startMatrix == null && endMatrix == null || startMatrix != null && startMatrix.equals(endMatrix);
            if (startBounds.equals(endBounds) && matricesEqual) {
               return null;
            } else {
               ImageView imageView = (ImageView)endValues.view;
               Drawable drawable = imageView.getDrawable();
               int drawableWidth = drawable.getIntrinsicWidth();
               int drawableHeight = drawable.getIntrinsicHeight();
               ImageViewUtils.startAnimateTransform(imageView);
               ObjectAnimator animator;
               if (drawableWidth != 0 && drawableHeight != 0) {
                  if (startMatrix == null) {
                     startMatrix = MatrixUtils.IDENTITY_MATRIX;
                  }

                  if (endMatrix == null) {
                     endMatrix = MatrixUtils.IDENTITY_MATRIX;
                  }

                  ANIMATED_TRANSFORM_PROPERTY.set(imageView, startMatrix);
                  animator = this.createMatrixAnimator(imageView, startMatrix, endMatrix);
               } else {
                  animator = this.createNullAnimator(imageView);
               }

               ImageViewUtils.reserveEndAnimateTransform(imageView, animator);
               return animator;
            }
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   private ObjectAnimator createNullAnimator(ImageView imageView) {
      return ObjectAnimator.ofObject(imageView, ANIMATED_TRANSFORM_PROPERTY, NULL_MATRIX_EVALUATOR, new Matrix[]{null, null});
   }

   private ObjectAnimator createMatrixAnimator(ImageView imageView, Matrix startMatrix, Matrix endMatrix) {
      return ObjectAnimator.ofObject(imageView, ANIMATED_TRANSFORM_PROPERTY, new TransitionUtils.MatrixEvaluator(), new Matrix[]{startMatrix, endMatrix});
   }

   private static Matrix copyImageMatrix(ImageView view) {
      switch(view.getScaleType()) {
      case FIT_XY:
         return fitXYMatrix(view);
      case CENTER_CROP:
         return centerCropMatrix(view);
      default:
         return new Matrix(view.getImageMatrix());
      }
   }

   private static Matrix fitXYMatrix(ImageView view) {
      Drawable image = view.getDrawable();
      Matrix matrix = new Matrix();
      matrix.postScale((float)view.getWidth() / (float)image.getIntrinsicWidth(), (float)view.getHeight() / (float)image.getIntrinsicHeight());
      return matrix;
   }

   private static Matrix centerCropMatrix(ImageView view) {
      Drawable image = view.getDrawable();
      int imageWidth = image.getIntrinsicWidth();
      int imageViewWidth = view.getWidth();
      float scaleX = (float)imageViewWidth / (float)imageWidth;
      int imageHeight = image.getIntrinsicHeight();
      int imageViewHeight = view.getHeight();
      float scaleY = (float)imageViewHeight / (float)imageHeight;
      float maxScale = Math.max(scaleX, scaleY);
      float width = (float)imageWidth * maxScale;
      float height = (float)imageHeight * maxScale;
      int tx = Math.round(((float)imageViewWidth - width) / 2.0F);
      int ty = Math.round(((float)imageViewHeight - height) / 2.0F);
      Matrix matrix = new Matrix();
      matrix.postScale(maxScale, maxScale);
      matrix.postTranslate((float)tx, (float)ty);
      return matrix;
   }
}
