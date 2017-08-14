package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import android.text.TextUtils;

final class zzmy extends zzmv {
   public final String zzg(@Nullable String var1, String var2) {
      var1 = zzN(var1);
      var2 = zzN(var2);
      if (TextUtils.isEmpty(var1)) {
         return var2;
      } else {
         return TextUtils.isEmpty(var2) ? var1 : (new StringBuilder(1 + String.valueOf(var1).length() + String.valueOf(var2).length())).append(var1).append(",").append(var2).toString();
      }
   }

   @Nullable
   private static String zzN(@Nullable String var0) {
      if (TextUtils.isEmpty(var0)) {
         return var0;
      } else {
         int var1 = 0;

         int var2;
         for(var2 = var0.length(); var1 < var0.length() && var0.charAt(var1) == ','; ++var1) {
            ;
         }

         while(var2 > 0 && var0.charAt(var2 - 1) == ',') {
            --var2;
         }

         return var1 == 0 && var2 == var0.length() ? var0 : var0.substring(var1, var2);
      }
   }
}
