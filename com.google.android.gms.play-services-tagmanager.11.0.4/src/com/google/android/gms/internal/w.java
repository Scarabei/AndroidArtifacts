package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public final class w extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      String var3 = zzcxp.zzd((dp)(var2.length > 0 ? (dp)zzbo.zzu(var2[0]) : dv.zzbLu));

      try {
         return new eb(encode(var3, "#;/?:@&=+$,abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_.!~*'()0123456789"));
      } catch (UnsupportedEncodingException var4) {
         return dv.zzbLu;
      }
   }

   static String encode(String var0, String var1) throws UnsupportedEncodingException {
      StringBuilder var2 = new StringBuilder();
      Charset var3 = Charset.forName("UTF-8");
      int var4 = 0;

      while(true) {
         while(var4 < var0.length()) {
            char var5 = var0.charAt(var4);
            if (var1.indexOf(var5) != -1) {
               var2.append((char)var5);
               ++var4;
            } else {
               byte var6 = 1;
               if (Character.isHighSurrogate((char)var5)) {
                  if (var4 + 1 >= var0.length()) {
                     throw new UnsupportedEncodingException();
                  }

                  if (!Character.isLowSurrogate(var0.charAt(var4 + 1))) {
                     throw new UnsupportedEncodingException();
                  }

                  var6 = 2;
               }

               byte[] var7 = var0.substring(var4, var4 + var6).getBytes(var3);

               for(int var8 = 0; var8 < var7.length; ++var8) {
                  var2.append("%");
                  var2.append(Character.toUpperCase(Character.forDigit(var7[var8] >> 4 & 15, 16)));
                  var2.append(Character.toUpperCase(Character.forDigit(var7[var8] & 15, 16)));
               }

               var4 += var6;
            }
         }

         return var2.toString().replaceAll(" ", "%20");
      }
   }
}
