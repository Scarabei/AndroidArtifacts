package com.google.android.gms.flags.impl;

import android.content.SharedPreferences;
import android.util.Log;
import com.google.android.gms.internal.zzcag;

public final class zzb extends zza {
   public static Boolean zza(SharedPreferences var0, String var1, Boolean var2) {
      try {
         return (Boolean)zzcag.zzb(new zzc(var0, var1, var2));
      } catch (Exception var4) {
         String var10002 = String.valueOf(var4.getMessage());
         String var10001;
         if (var10002.length() != 0) {
            var10001 = "Flag value not available, returning default: ".concat(var10002);
         } else {
            String var10003 = new String;
            var10001 = var10003;
            var10003.<init>("Flag value not available, returning default: ");
         }

         Log.w("FlagDataUtils", var10001);
         return var2;
      }
   }
}
