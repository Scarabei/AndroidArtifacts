package android.support.v4.text;

import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import java.util.Locale;

public final class ICUCompat {
   private static final ICUCompat.ICUCompatBaseImpl IMPL;

   @Nullable
   public static String maximizeAndGetScript(Locale locale) {
      return IMPL.maximizeAndGetScript(locale);
   }

   static {
      if (VERSION.SDK_INT >= 21) {
         IMPL = new ICUCompat.ICUCompatApi21Impl();
      } else {
         IMPL = new ICUCompat.ICUCompatBaseImpl();
      }

   }

   @RequiresApi(21)
   static class ICUCompatApi21Impl extends ICUCompat.ICUCompatBaseImpl {
      public String maximizeAndGetScript(Locale locale) {
         return ICUCompatApi21.maximizeAndGetScript(locale);
      }
   }

   static class ICUCompatBaseImpl {
      public String maximizeAndGetScript(Locale locale) {
         return ICUCompatIcs.maximizeAndGetScript(locale);
      }
   }
}
