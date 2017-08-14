package android.support.v7.widget;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RestrictTo({Scope.LIBRARY_GROUP})
public class ViewUtils {
   private static final String TAG = "ViewUtils";
   private static Method sComputeFitSystemWindowsMethod;

   public static boolean isLayoutRtl(View view) {
      return ViewCompat.getLayoutDirection(view) == 1;
   }

   public static void computeFitSystemWindows(View view, Rect inoutInsets, Rect outLocalInsets) {
      if (sComputeFitSystemWindowsMethod != null) {
         try {
            sComputeFitSystemWindowsMethod.invoke(view, inoutInsets, outLocalInsets);
         } catch (Exception var4) {
            Log.d("ViewUtils", "Could not invoke computeFitSystemWindows", var4);
         }
      }

   }

   public static void makeOptionalFitsSystemWindows(View view) {
      if (VERSION.SDK_INT >= 16) {
         try {
            Method method = view.getClass().getMethod("makeOptionalFitsSystemWindows");
            if (!method.isAccessible()) {
               method.setAccessible(true);
            }

            method.invoke(view);
         } catch (NoSuchMethodException var2) {
            Log.d("ViewUtils", "Could not find method makeOptionalFitsSystemWindows. Oh well...");
         } catch (InvocationTargetException var3) {
            Log.d("ViewUtils", "Could not invoke makeOptionalFitsSystemWindows", var3);
         } catch (IllegalAccessException var4) {
            Log.d("ViewUtils", "Could not invoke makeOptionalFitsSystemWindows", var4);
         }
      }

   }

   static {
      if (VERSION.SDK_INT >= 18) {
         try {
            sComputeFitSystemWindowsMethod = View.class.getDeclaredMethod("computeFitSystemWindows", Rect.class, Rect.class);
            if (!sComputeFitSystemWindowsMethod.isAccessible()) {
               sComputeFitSystemWindowsMethod.setAccessible(true);
            }
         } catch (NoSuchMethodException var1) {
            Log.d("ViewUtils", "Could not find method computeFitSystemWindows. Oh well.");
         }
      }

   }
}
