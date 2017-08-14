package android.support.v4.text;

import android.support.annotation.RequiresApi;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

@RequiresApi(21)
class ICUCompatApi21 {
   private static final String TAG = "ICUCompatApi21";
   private static Method sAddLikelySubtagsMethod;

   public static String maximizeAndGetScript(Locale locale) {
      try {
         Object[] args = new Object[]{locale};
         return ((Locale)sAddLikelySubtagsMethod.invoke((Object)null, args)).getScript();
      } catch (InvocationTargetException var2) {
         Log.w("ICUCompatApi21", var2);
      } catch (IllegalAccessException var3) {
         Log.w("ICUCompatApi21", var3);
      }

      return locale.getScript();
   }

   static {
      try {
         Class clazz = Class.forName("libcore.icu.ICU");
         sAddLikelySubtagsMethod = clazz.getMethod("addLikelySubtags", Locale.class);
      } catch (Exception var1) {
         throw new IllegalStateException(var1);
      }
   }
}
