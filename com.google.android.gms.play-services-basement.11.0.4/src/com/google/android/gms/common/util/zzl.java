package com.google.android.gms.common.util;

public final class zzl {
   public static String zza(byte[] var0, int var1, int var2, boolean var3) {
      if (var0 != null && var0.length != 0 && var2 > 0 && var2 <= var0.length) {
         int var4 = 57 * ((var2 + 16 - 1) / 16);
         StringBuilder var5 = new StringBuilder(var4);
         int var6 = 0;
         int var7 = var2;

         for(int var8 = 0; var7 > 0; ++var8) {
            if (var6 == 0) {
               if (var2 < 65536) {
                  var5.append(String.format("%04X:", var8));
               } else {
                  var5.append(String.format("%08X:", var8));
               }
            } else if (var6 == 8) {
               var5.append(" -");
            }

            var5.append(String.format(" %02X", var0[var8] & 255));
            --var7;
            ++var6;
            if (var6 == 16 || var7 == 0) {
               var5.append('\n');
               var6 = 0;
            }
         }

         return var5.toString();
      } else {
         return null;
      }
   }
}
