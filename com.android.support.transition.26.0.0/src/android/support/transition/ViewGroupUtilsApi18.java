package android.support.transition;

import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.ViewGroup;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiresApi(18)
class ViewGroupUtilsApi18 extends ViewGroupUtilsApi14 {
   private static final String TAG = "ViewUtilsApi18";
   private static Method sSuppressLayoutMethod;
   private static boolean sSuppressLayoutMethodFetched;

   public ViewGroupOverlayImpl getOverlay(@NonNull ViewGroup group) {
      return new ViewGroupOverlayApi18(group);
   }

   public void suppressLayout(@NonNull ViewGroup group, boolean suppress) {
      this.fetchSuppressLayoutMethod();
      if (sSuppressLayoutMethod != null) {
         try {
            sSuppressLayoutMethod.invoke(group, suppress);
         } catch (IllegalAccessException var4) {
            Log.i("ViewUtilsApi18", "Failed to invoke suppressLayout method", var4);
         } catch (InvocationTargetException var5) {
            Log.i("ViewUtilsApi18", "Error invoking suppressLayout method", var5);
         }
      }

   }

   private void fetchSuppressLayoutMethod() {
      if (!sSuppressLayoutMethodFetched) {
         try {
            sSuppressLayoutMethod = ViewGroup.class.getDeclaredMethod("suppressLayout", Boolean.TYPE);
            sSuppressLayoutMethod.setAccessible(true);
         } catch (NoSuchMethodException var2) {
            Log.i("ViewUtilsApi18", "Failed to retrieve suppressLayout method", var2);
         }

         sSuppressLayoutMethodFetched = true;
      }

   }
}
