package android.support.transition;

import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiresApi(19)
class ViewUtilsApi19 extends ViewUtilsApi18 {
   private static final String TAG = "ViewUtilsApi19";
   private static Method sSetTransitionAlphaMethod;
   private static boolean sSetTransitionAlphaMethodFetched;
   private static Method sGetTransitionAlphaMethod;
   private static boolean sGetTransitionAlphaMethodFetched;

   public void setTransitionAlpha(@NonNull View view, float alpha) {
      this.fetchSetTransitionAlphaMethod();
      if (sSetTransitionAlphaMethod != null) {
         try {
            sSetTransitionAlphaMethod.invoke(view, alpha);
         } catch (IllegalAccessException var4) {
            ;
         } catch (InvocationTargetException var5) {
            throw new RuntimeException(var5.getCause());
         }
      } else {
         view.setAlpha(alpha);
      }

   }

   public float getTransitionAlpha(@NonNull View view) {
      this.fetchGetTransitionAlphaMethod();
      if (sGetTransitionAlphaMethod != null) {
         try {
            return ((Float)sGetTransitionAlphaMethod.invoke(view)).floatValue();
         } catch (IllegalAccessException var3) {
            ;
         } catch (InvocationTargetException var4) {
            throw new RuntimeException(var4.getCause());
         }
      }

      return super.getTransitionAlpha(view);
   }

   public void saveNonTransitionAlpha(@NonNull View view) {
   }

   public void clearNonTransitionAlpha(@NonNull View view) {
   }

   private void fetchSetTransitionAlphaMethod() {
      if (!sSetTransitionAlphaMethodFetched) {
         try {
            sSetTransitionAlphaMethod = View.class.getDeclaredMethod("setTransitionAlpha", Float.TYPE);
            sSetTransitionAlphaMethod.setAccessible(true);
         } catch (NoSuchMethodException var2) {
            Log.i("ViewUtilsApi19", "Failed to retrieve setTransitionAlpha method", var2);
         }

         sSetTransitionAlphaMethodFetched = true;
      }

   }

   private void fetchGetTransitionAlphaMethod() {
      if (!sGetTransitionAlphaMethodFetched) {
         try {
            sGetTransitionAlphaMethod = View.class.getDeclaredMethod("getTransitionAlpha");
            sGetTransitionAlphaMethod.setAccessible(true);
         } catch (NoSuchMethodException var2) {
            Log.i("ViewUtilsApi19", "Failed to retrieve getTransitionAlpha method", var2);
         }

         sGetTransitionAlphaMethodFetched = true;
      }

   }
}
