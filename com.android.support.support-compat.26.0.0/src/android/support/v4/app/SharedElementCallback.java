package android.support.v4.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import java.util.List;
import java.util.Map;

public abstract class SharedElementCallback {
   private Matrix mTempMatrix;
   private static int MAX_IMAGE_SIZE = 1048576;
   private static final String BUNDLE_SNAPSHOT_BITMAP = "sharedElement:snapshot:bitmap";
   private static final String BUNDLE_SNAPSHOT_IMAGE_SCALETYPE = "sharedElement:snapshot:imageScaleType";
   private static final String BUNDLE_SNAPSHOT_IMAGE_MATRIX = "sharedElement:snapshot:imageMatrix";

   public void onSharedElementStart(List sharedElementNames, List sharedElements, List sharedElementSnapshots) {
   }

   public void onSharedElementEnd(List sharedElementNames, List sharedElements, List sharedElementSnapshots) {
   }

   public void onRejectSharedElements(List rejectedSharedElements) {
   }

   public void onMapSharedElements(List names, Map sharedElements) {
   }

   public Parcelable onCaptureSharedElementSnapshot(View sharedElement, Matrix viewToGlobalMatrix, RectF screenBounds) {
      if (sharedElement instanceof ImageView) {
         ImageView imageView = (ImageView)sharedElement;
         Drawable d = imageView.getDrawable();
         Drawable bg = imageView.getBackground();
         if (d != null && bg == null) {
            Bitmap bitmap = createDrawableBitmap(d);
            if (bitmap != null) {
               Bundle bundle = new Bundle();
               bundle.putParcelable("sharedElement:snapshot:bitmap", bitmap);
               bundle.putString("sharedElement:snapshot:imageScaleType", imageView.getScaleType().toString());
               if (imageView.getScaleType() == ScaleType.MATRIX) {
                  Matrix matrix = imageView.getImageMatrix();
                  float[] values = new float[9];
                  matrix.getValues(values);
                  bundle.putFloatArray("sharedElement:snapshot:imageMatrix", values);
               }

               return bundle;
            }
         }
      }

      int bitmapWidth = Math.round(screenBounds.width());
      int bitmapHeight = Math.round(screenBounds.height());
      Bitmap bitmap = null;
      if (bitmapWidth > 0 && bitmapHeight > 0) {
         float scale = Math.min(1.0F, (float)MAX_IMAGE_SIZE / (float)(bitmapWidth * bitmapHeight));
         bitmapWidth = (int)((float)bitmapWidth * scale);
         bitmapHeight = (int)((float)bitmapHeight * scale);
         if (this.mTempMatrix == null) {
            this.mTempMatrix = new Matrix();
         }

         this.mTempMatrix.set(viewToGlobalMatrix);
         this.mTempMatrix.postTranslate(-screenBounds.left, -screenBounds.top);
         this.mTempMatrix.postScale(scale, scale);
         bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Config.ARGB_8888);
         Canvas canvas = new Canvas(bitmap);
         canvas.concat(this.mTempMatrix);
         sharedElement.draw(canvas);
      }

      return bitmap;
   }

   private static Bitmap createDrawableBitmap(Drawable drawable) {
      int width = drawable.getIntrinsicWidth();
      int height = drawable.getIntrinsicHeight();
      if (width > 0 && height > 0) {
         float scale = Math.min(1.0F, (float)MAX_IMAGE_SIZE / (float)(width * height));
         if (drawable instanceof BitmapDrawable && scale == 1.0F) {
            return ((BitmapDrawable)drawable).getBitmap();
         } else {
            int bitmapWidth = (int)((float)width * scale);
            int bitmapHeight = (int)((float)height * scale);
            Bitmap bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            Rect existingBounds = drawable.getBounds();
            int left = existingBounds.left;
            int top = existingBounds.top;
            int right = existingBounds.right;
            int bottom = existingBounds.bottom;
            drawable.setBounds(0, 0, bitmapWidth, bitmapHeight);
            drawable.draw(canvas);
            drawable.setBounds(left, top, right, bottom);
            return bitmap;
         }
      } else {
         return null;
      }
   }

   public View onCreateSnapshotView(Context context, Parcelable snapshot) {
      ImageView view = null;
      if (snapshot instanceof Bundle) {
         Bundle bundle = (Bundle)snapshot;
         Bitmap bitmap = (Bitmap)bundle.getParcelable("sharedElement:snapshot:bitmap");
         if (bitmap == null) {
            return null;
         }

         ImageView imageView = new ImageView(context);
         view = imageView;
         imageView.setImageBitmap(bitmap);
         imageView.setScaleType(ScaleType.valueOf(bundle.getString("sharedElement:snapshot:imageScaleType")));
         if (imageView.getScaleType() == ScaleType.MATRIX) {
            float[] values = bundle.getFloatArray("sharedElement:snapshot:imageMatrix");
            Matrix matrix = new Matrix();
            matrix.setValues(values);
            imageView.setImageMatrix(matrix);
         }
      } else if (snapshot instanceof Bitmap) {
         Bitmap bitmap = (Bitmap)snapshot;
         view = new ImageView(context);
         view.setImageBitmap(bitmap);
      }

      return view;
   }

   public void onSharedElementsArrived(List sharedElementNames, List sharedElements, SharedElementCallback.OnSharedElementsReadyListener listener) {
      listener.onSharedElementsReady();
   }

   public interface OnSharedElementsReadyListener {
      void onSharedElementsReady();
   }
}
