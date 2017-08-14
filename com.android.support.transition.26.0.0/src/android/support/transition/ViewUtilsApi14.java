package android.support.transition;

import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.transition.R.id;
import android.view.View;
import android.view.ViewParent;

@RequiresApi(14)
class ViewUtilsApi14 implements ViewUtilsImpl {
   private float[] mMatrixValues;

   public ViewOverlayImpl getOverlay(@NonNull View view) {
      return ViewOverlayApi14.createFrom(view);
   }

   public WindowIdImpl getWindowId(@NonNull View view) {
      return new WindowIdApi14(view.getWindowToken());
   }

   public void setTransitionAlpha(@NonNull View view, float alpha) {
      Float savedAlpha = (Float)view.getTag(id.save_non_transition_alpha);
      if (savedAlpha != null) {
         view.setAlpha(savedAlpha.floatValue() * alpha);
      } else {
         view.setAlpha(alpha);
      }

   }

   public float getTransitionAlpha(@NonNull View view) {
      Float savedAlpha = (Float)view.getTag(id.save_non_transition_alpha);
      return savedAlpha != null ? view.getAlpha() / savedAlpha.floatValue() : view.getAlpha();
   }

   public void saveNonTransitionAlpha(@NonNull View view) {
      if (view.getTag(id.save_non_transition_alpha) == null) {
         view.setTag(id.save_non_transition_alpha, view.getAlpha());
      }

   }

   public void clearNonTransitionAlpha(@NonNull View view) {
      if (view.getVisibility() == 0) {
         view.setTag(id.save_non_transition_alpha, (Object)null);
      }

   }

   public void transformMatrixToGlobal(@NonNull View view, @NonNull Matrix matrix) {
      ViewParent parent = view.getParent();
      if (parent instanceof View) {
         View vp = (View)parent;
         this.transformMatrixToGlobal(vp, matrix);
         matrix.preTranslate((float)(-vp.getScrollX()), (float)(-vp.getScrollY()));
      }

      matrix.preTranslate((float)view.getLeft(), (float)view.getTop());
      Matrix vm = view.getMatrix();
      if (!vm.isIdentity()) {
         matrix.preConcat(vm);
      }

   }

   public void transformMatrixToLocal(@NonNull View view, @NonNull Matrix matrix) {
      ViewParent parent = view.getParent();
      if (parent instanceof View) {
         View vp = (View)parent;
         this.transformMatrixToLocal(vp, matrix);
         matrix.postTranslate((float)vp.getScrollX(), (float)vp.getScrollY());
      }

      matrix.postTranslate((float)view.getLeft(), (float)view.getTop());
      Matrix vm = view.getMatrix();
      if (!vm.isIdentity()) {
         Matrix inverted = new Matrix();
         if (vm.invert(inverted)) {
            matrix.postConcat(inverted);
         }
      }

   }

   public void setAnimationMatrix(@NonNull View view, Matrix matrix) {
      if (matrix != null && !matrix.isIdentity()) {
         float[] values = this.mMatrixValues;
         if (values == null) {
            this.mMatrixValues = values = new float[9];
         }

         matrix.getValues(values);
         float sin = values[3];
         float cos = (float)Math.sqrt((double)(1.0F - sin * sin)) * (float)(values[0] < 0.0F ? -1 : 1);
         float rotation = (float)Math.toDegrees(Math.atan2((double)sin, (double)cos));
         float scaleX = values[0] / cos;
         float scaleY = values[4] / cos;
         float dx = values[2];
         float dy = values[5];
         view.setPivotX(0.0F);
         view.setPivotY(0.0F);
         view.setTranslationX(dx);
         view.setTranslationY(dy);
         view.setRotation(rotation);
         view.setScaleX(scaleX);
         view.setScaleY(scaleY);
      } else {
         view.setPivotX((float)(view.getWidth() / 2));
         view.setPivotY((float)(view.getHeight() / 2));
         view.setTranslationX(0.0F);
         view.setTranslationY(0.0F);
         view.setScaleX(1.0F);
         view.setScaleY(1.0F);
         view.setRotation(0.0F);
      }

   }

   public void setLeftTopRightBottom(View v, int left, int top, int right, int bottom) {
      v.setLeft(left);
      v.setTop(top);
      v.setRight(right);
      v.setBottom(bottom);
   }
}
