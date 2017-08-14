package com.google.android.gms.internal;

import android.util.Base64;

public final class zzbt {
   public static String zza(byte[] var0, boolean var1) {
      int var2 = var1 ? 11 : 2;
      return Base64.encodeToString(var0, var2);
   }

   public static byte[] zza(String var0, boolean var1) throws IllegalArgumentException {
      int var2 = var1 ? 11 : 2;
      byte[] var3;
      if ((var3 = Base64.decode(var0, var2)).length == 0 && var0.length() > 0) {
         IllegalArgumentException var10000 = new IllegalArgumentException;
         String var10003 = String.valueOf(var0);
         String var10002;
         if (var10003.length() != 0) {
            var10002 = "Unable to decode ".concat(var10003);
         } else {
            String var10004 = new String;
            var10002 = var10004;
            var10004.<init>("Unable to decode ");
         }

         var10000.<init>(var10002);
         throw var10000;
      } else {
         return var3;
      }
   }
}
