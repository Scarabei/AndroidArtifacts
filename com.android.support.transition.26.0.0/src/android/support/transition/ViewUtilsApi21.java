package android.support.transition;

import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiresApi(21)
class ViewUtilsApi21 extends ViewUtilsApi19 {
   private static final String TAG = "ViewUtilsApi21";
   private static Method sTransformMatrixToGlobalMethod;
   private static boolean sTransformMatrixToGlobalMethodFetched;
   private static Method sTransformMatrixToLocalMethod;
   private static boolean sTransformMatrixToLocalMethodFetched;
   private static Method sSetAnimationMatrixMethod;
   private static boolean sSetAnimationMatrixMethodFetched;

   public void transformMatrixToGlobal(@NonNull View view, @NonNull Matrix matrix) {
      this.fetchTransformMatrixToGlobalMethod();
      if (sTransformMatrixToGlobalMethod != null) {
         try {
            sTransformMatrixToGlobalMethod.invoke(view, matrix);
         } catch (IllegalAccessException var4) {
            ;
         } catch (InvocationTargetException var5) {
            throw new RuntimeException(var5.getCause());
         }
      }

   }

   public void transformMatrixToLocal(@NonNull View view, @NonNull Matrix matrix) {
      this.fetchTransformMatrixToLocalMethod();
      if (sTransformMatrixToLocalMethod != null) {
         try {
            sTransformMatrixToLocalMethod.invoke(view, matrix);
         } catch (IllegalAccessException var4) {
            ;
         } catch (InvocationTargetException var5) {
            throw new RuntimeException(var5.getCause());
         }
      }

   }

   public void setAnimationMatrix(@NonNull View view, Matrix matrix) {
      this.fetchSetAnimationMatrix();
      if (sSetAnimationMatrixMethod != null) {
         try {
            sSetAnimationMatrixMethod.invoke(view, matrix);
         } catch (InvocationTargetException var4) {
            ;
         } catch (IllegalAccessException var5) {
            throw new RuntimeException(var5.getCause());
         }
      }

   }

   private void fetchTransformMatrixToGlobalMethod() {
      if (!sTransformMatrixToGlobalMethodFetched) {
         try {
            sTransformMatrixToGlobalMethod = View.class.getDeclaredMethod("transformMatrixToGlobal", Matrix.class);
            sTransformMatrixToGlobalMethod.setAccessible(true);
         } catch (NoSuchMethodException var2) {
            Log.i("ViewUtilsApi21", "Failed to retrieve transformMatrixToGlobal method", var2);
         }

         sTransformMatrixToGlobalMethodFetched = true;
      }

   }

   private void fetchTransformMatrixToLocalMethod() {
      if (!sTransformMatrixToLocalMethodFetched) {
         try {
            sTransformMatrixToLocalMethod = View.class.getDeclaredMethod("transformMatrixToLocal", Matrix.class);
            sTransformMatrixToLocalMethod.setAccessible(true);
         } catch (NoSuchMethodException var2) {
            Log.i("ViewUtilsApi21", "Failed to retrieve transformMatrixToLocal method", var2);
         }

         sTransformMatrixToLocalMethodFetched = true;
      }

   }

   private void fetchSetAnimationMatrix() {
      if (!sSetAnimationMatrixMethodFetched) {
         try {
            sSetAnimationMatrixMethod = View.class.getDeclaredMethod("setAnimationMatrix", Matrix.class);
            sSetAnimationMatrixMethod.setAccessible(true);
         } catch (NoSuchMethodException var2) {
            Log.i("ViewUtilsApi21", "Failed to retrieve setAnimationMatrix method", var2);
         }

         sSetAnimationMatrixMethodFetched = true;
      }

   }
}
