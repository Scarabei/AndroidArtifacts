package com.google.android.gms.internal;

import java.util.Locale;

public final class zzcue {
   public static String encode(byte[] var0) {
      StringBuilder var1 = new StringBuilder();
      byte[] var2 = var0;
      int var3 = var0.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         byte var5;
         if (((var5 = var2[var4]) & 240) == 0) {
            var1.append("0");
         }

         var1.append(Integer.toHexString(var5 & 255));
      }

      return var1.toString().toUpperCase(Locale.ENGLISH);
   }

   public static byte[] decode(String var0) {
      int var1;
      if ((var1 = var0.length()) % 2 != 0) {
         throw new IllegalArgumentException("purported base16 string has odd number of characters");
      } else {
         byte[] var2 = new byte[var1 / 2];

         for(int var3 = 0; var3 < var1; var3 += 2) {
            int var4 = Character.digit(var0.charAt(var3), 16);
            int var5 = Character.digit(var0.charAt(var3 + 1), 16);
            if (var4 == -1 || var5 == -1) {
               throw new IllegalArgumentException("purported base16 string has illegal char");
            }

            var2[var3 / 2] = (byte)((var4 << 4) + var5);
         }

         return var2;
      }
   }
}
