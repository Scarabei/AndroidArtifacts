package com.google.android.gms.flags.impl;

import android.content.SharedPreferences;
import android.util.Log;
import com.google.android.gms.internal.zzcag;

public final class zzh extends zza {
   public static String zza(SharedPreferences var0, String var1, String var2) {
      try {
         return (String)zzcag.zzb(new zzi(var0, var1, var2));
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
