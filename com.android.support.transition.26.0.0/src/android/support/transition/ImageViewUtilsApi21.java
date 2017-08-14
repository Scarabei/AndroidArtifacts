package android.support.transition;

import android.animation.Animator;
import android.graphics.Matrix;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.ImageView;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiresApi(21)
class ImageViewUtilsApi21 implements ImageViewUtilsImpl {
   private static final String TAG = "ImageViewUtilsApi21";
   private static Method sAnimateTransformMethod;
   private static boolean sAnimateTransformMethodFetched;

   public void startAnimateTransform(ImageView view) {
   }

   public void animateTransform(ImageView view, Matrix matrix) {
      this.fetchAnimateTransformMethod();
      if (sAnimateTransformMethod != null) {
         try {
            sAnimateTransformMethod.invoke(view, matrix);
         } catch (IllegalAccessException var4) {
            ;
         } catch (InvocationTargetException var5) {
            throw new RuntimeException(var5.getCause());
         }
      }

   }

   public void reserveEndAnimateTransform(ImageView view, Animator animator) {
   }

   private void fetchAnimateTransformMethod() {
      if (!sAnimateTransformMethodFetched) {
         try {
            sAnimateTransformMethod = ImageView.class.getDeclaredMethod("animateTransform", Matrix.class);
            sAnimateTransformMethod.setAccessible(true);
         } catch (NoSuchMethodException var2) {
            Log.i("ImageViewUtilsApi21", "Failed to retrieve animateTransform method", var2);
         }

         sAnimateTransformMethodFetched = true;
      }

   }
}
