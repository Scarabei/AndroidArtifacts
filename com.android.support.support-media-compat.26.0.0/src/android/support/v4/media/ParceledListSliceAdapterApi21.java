package android.support.v4.media;

import android.support.annotation.RequiresApi;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RequiresApi(21)
class ParceledListSliceAdapterApi21 {
   private static Constructor sConstructor;

   static Object newInstance(List itemList) {
      Object result = null;

      try {
         result = sConstructor.newInstance(itemList);
      } catch (IllegalAccessException | InvocationTargetException | InstantiationException var3) {
         var3.printStackTrace();
      }

      return result;
   }

   static {
      try {
         Class theClass = Class.forName("android.content.pm.ParceledListSlice");
         sConstructor = theClass.getConstructor(List.class);
      } catch (NoSuchMethodException | ClassNotFoundException var1) {
         var1.printStackTrace();
      }

   }
}
