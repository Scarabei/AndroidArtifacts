package android.support.v4.text;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.util.Locale;

public final class TextUtilsCompat {
   /** @deprecated */
   @Deprecated
   public static final Locale ROOT = new Locale("", "");
   private static final String ARAB_SCRIPT_SUBTAG = "Arab";
   private static final String HEBR_SCRIPT_SUBTAG = "Hebr";

   @NonNull
   public static String htmlEncode(@NonNull String s) {
      if (VERSION.SDK_INT >= 17) {
         return TextUtils.htmlEncode(s);
      } else {
         StringBuilder sb = new StringBuilder();

         for(int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            switch(c) {
            case '"':
               sb.append("&quot;");
               break;
            case '&':
               sb.append("&amp;");
               break;
            case '\'':
               sb.append("&#39;");
               break;
            case '<':
               sb.append("&lt;");
               break;
            case '>':
               sb.append("&gt;");
               break;
            default:
               sb.append(c);
            }
         }

         return sb.toString();
      }
   }

   public static int getLayoutDirectionFromLocale(@Nullable Locale locale) {
      if (VERSION.SDK_INT >= 17) {
         return TextUtils.getLayoutDirectionFromLocale(locale);
      } else {
         if (locale != null && !locale.equals(ROOT)) {
            String scriptSubtag = ICUCompat.maximizeAndGetScript(locale);
            if (scriptSubtag == null) {
               return getLayoutDirectionFromFirstChar(locale);
            }

            if (scriptSubtag.equalsIgnoreCase("Arab") || scriptSubtag.equalsIgnoreCase("Hebr")) {
               return 1;
            }
         }

         return 0;
      }
   }

   private static int getLayoutDirectionFromFirstChar(@NonNull Locale locale) {
      switch(Character.getDirectionality(locale.getDisplayName(locale).charAt(0))) {
      case 0:
      default:
         return 0;
      case 1:
      case 2:
         return 1;
      }
   }
}
