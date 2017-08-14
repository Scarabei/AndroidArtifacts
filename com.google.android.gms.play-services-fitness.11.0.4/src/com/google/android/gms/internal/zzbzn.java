package com.google.android.gms.internal;

public final class zzbzn {
   private static final ThreadLocal zzaWi = new ThreadLocal();

   public static String zzdh(String var0) {
      String var1;
      if ((var1 = (String)zzaWi.get()) == null || var1.startsWith("com.google")) {
         return var0;
      } else {
         String var2 = (String)zzaWi.get();
         if (var0 != null && var2 != null) {
            byte[] var3 = new byte[var0.length() + var2.length()];
            System.arraycopy(var0.getBytes(), 0, var3, 0, var0.length());
            System.arraycopy(var2.getBytes(), 0, var3, var0.length(), var2.length());
            int var5 = var3.length;
            byte[] var4 = var3;
            int var6 = 0;
            int var7 = 0 + (var5 & -4);

            int var8;
            for(var8 = 0; var8 < var7; var8 += 4) {
               int var9 = ((var9 = (var4[var8] & 255 | (var4[var8 + 1] & 255) << 8 | (var4[var8 + 2] & 255) << 16 | var4[var8 + 3] << 24) * -862048943) << 15 | var9 >>> 17) * 461845907;
               var6 = ((var6 ^= var9) << 13 | var6 >>> 19) * 5 + -430675100;
            }

            var8 = 0;
            switch(var5 & 3) {
            case 3:
               var8 = (var4[var7 + 2] & 255) << 16;
            case 2:
               var8 |= (var4[var7 + 1] & 255) << 8;
            case 1:
               var8 = ((var8 = (var8 | var4[var7] & 255) * -862048943) << 15 | var8 >>> 17) * 461845907;
               var6 ^= var8;
            default:
               return Integer.toHexString(((var6 ^ var5 ^ (var6 ^ var5) >>> 16) * -2048144789 ^ (var6 ^ var5 ^ (var6 ^ var5) >>> 16) * -2048144789 >>> 13) * -1028477387 ^ ((var6 ^ var5 ^ (var6 ^ var5) >>> 16) * -2048144789 ^ (var6 ^ var5 ^ (var6 ^ var5) >>> 16) * -2048144789 >>> 13) * -1028477387 >>> 16);
            }
         } else {
            return var0;
         }
      }
   }
}
