package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TypeEvaluator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

class TransitionUtils {
   private static final int MAX_IMAGE_SIZE = 1048576;

   static View copyViewImage(ViewGroup sceneRoot, View view, View parent) {
      Matrix matrix = new Matrix();
      matrix.setTranslate((float)(-parent.getScrollX()), (float)(-parent.getScrollY()));
      ViewUtils.transformMatrixToGlobal(view, matrix);
      ViewUtils.transformMatrixToLocal(sceneRoot, matrix);
      RectF bounds = new RectF(0.0F, 0.0F, (float)view.getWidth(), (float)view.getHeight());
      matrix.mapRect(bounds);
      int left = Math.round(bounds.left);
      int top = Math.round(bounds.top);
      int right = Math.round(bounds.right);
      int bottom = Math.round(bounds.bottom);
      ImageView copy = new ImageView(view.getContext());
      copy.setScaleType(ScaleType.CENTER_CROP);
      Bitmap bitmap = createViewBitmap(view, matrix, bounds);
      if (bitmap != null) {
         copy.setImageBitmap(bitmap);
      }

      int widthSpec = MeasureSpec.makeMeasureSpec(right - left, 1073741824);
      int heightSpec = MeasureSpec.makeMeasureSpec(bottom - top, 1073741824);
      copy.measure(widthSpec, heightSpec);
      copy.layout(left, top, right, bottom);
      return copy;
   }

   private static Bitmap createViewBitmap(View view, Matrix matrix, RectF bounds) {
      Bitmap bitmap = null;
      int bitmapWidth = Math.round(bounds.width());
      int bitmapHeight = Math.round(bounds.height());
      if (bitmapWidth > 0 && bitmapHeight > 0) {
         float scale = Math.min(1.0F, 1048576.0F / (float)(bitmapWidth * bitmapHeight));
         bitmapWidth = (int)((float)bitmapWidth * scale);
         bitmapHeight = (int)((float)bitmapHeight * scale);
         matrix.postTranslate(-bounds.left, -bounds.top);
         matrix.postScale(scale, scale);
         bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Config.ARGB_8888);
         Canvas canvas = new Canvas(bitmap);
         canvas.concat(matrix);
         view.draw(canvas);
      }

      return bitmap;
   }

   static Animator mergeAnimators(Animator animator1, Animator animator2) {
      if (animator1 == null) {
         return animator2;
      } else if (animator2 == null) {
         return animator1;
      } else {
         AnimatorSet animatorSet = new AnimatorSet();
         animatorSet.playTogether(new Animator[]{animator1, animator2});
         return animatorSet;
      }
   }

   static class MatrixEvaluator implements TypeEvaluator {
      final float[] mTempStartValues = new float[9];
      final float[] mTempEndValues = new float[9];
      final Matrix mTempMatrix = new Matrix();

      public Matrix evaluate(float fraction, Matrix startValue, Matrix endValue) {
         startValue.getValues(this.mTempStartValues);
         endValue.getValues(this.mTempEndValues);

         for(int i = 0; i < 9; ++i) {
            float diff = this.mTempEndValues[i] - this.mTempStartValues[i];
            this.mTempEndValues[i] = this.mTempStartValues[i] + fraction * diff;
         }

         this.mTempMatrix.setValues(this.mTempEndValues);
         return this.mTempMatrix;
      }
   }
}
