package android.support.v4.text;

import android.support.annotation.RequiresApi;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

@RequiresApi(14)
class ICUCompatIcs {
   private static final String TAG = "ICUCompatIcs";
   private static Method sGetScriptMethod;
   private static Method sAddLikelySubtagsMethod;

   public static String maximizeAndGetScript(Locale locale) {
      String localeWithSubtags = addLikelySubtags(locale);
      return localeWithSubtags != null ? getScript(localeWithSubtags) : null;
   }

   private static String getScript(String localeStr) {
      try {
         if (sGetScriptMethod != null) {
            Object[] args = new Object[]{localeStr};
            return (String)sGetScriptMethod.invoke((Object)null, args);
         }
      } catch (IllegalAccessException var2) {
         Log.w("ICUCompatIcs", var2);
      } catch (InvocationTargetException var3) {
         Log.w("ICUCompatIcs", var3);
      }

      return null;
   }

   private static String addLikelySubtags(Locale locale) {
      String localeStr = locale.toString();

      try {
         if (sAddLikelySubtagsMethod != null) {
            Object[] args = new Object[]{localeStr};
            return (String)sAddLikelySubtagsMethod.invoke((Object)null, args);
         }
      } catch (IllegalAccessException var3) {
         Log.w("ICUCompatIcs", var3);
      } catch (InvocationTargetException var4) {
         Log.w("ICUCompatIcs", var4);
      }

      return localeStr;
   }

   static {
      try {
         Class clazz = Class.forName("libcore.icu.ICU");
         if (clazz != null) {
            sGetScriptMethod = clazz.getMethod("getScript", String.class);
            sAddLikelySubtagsMethod = clazz.getMethod("addLikelySubtags", String.class);
         }
      } catch (Exception var1) {
         sGetScriptMethod = null;
         sAddLikelySubtagsMethod = null;
         Log.w("ICUCompatIcs", var1);
      }

   }
}
