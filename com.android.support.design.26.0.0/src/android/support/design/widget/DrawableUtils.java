package android.support.design.widget;

import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.DrawableContainer.DrawableContainerState;
import android.util.Log;
import java.lang.reflect.Method;

class DrawableUtils {
   private static final String LOG_TAG = "DrawableUtils";
   private static Method sSetConstantStateMethod;
   private static boolean sSetConstantStateMethodFetched;

   static boolean setContainerConstantState(DrawableContainer drawable, ConstantState constantState) {
      return setContainerConstantStateV9(drawable, constantState);
   }

   private static boolean setContainerConstantStateV9(DrawableContainer drawable, ConstantState constantState) {
      if (!sSetConstantStateMethodFetched) {
         try {
            sSetConstantStateMethod = DrawableContainer.class.getDeclaredMethod("setConstantState", DrawableContainerState.class);
            sSetConstantStateMethod.setAccessible(true);
         } catch (NoSuchMethodException var3) {
            Log.e("DrawableUtils", "Could not fetch setConstantState(). Oh well.");
         }

         sSetConstantStateMethodFetched = true;
      }

      if (sSetConstantStateMethod != null) {
         try {
            sSetConstantStateMethod.invoke(drawable, constantState);
            return true;
         } catch (Exception var4) {
            Log.e("DrawableUtils", "Could not invoke setConstantState(). Oh well.");
         }
      }

      return false;
   }
}
