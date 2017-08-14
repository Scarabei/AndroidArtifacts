package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public final class u extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      String var3 = zzcxp.zzd((dp)(var2.length > 0 ? (dp)zzbo.zzu(var2[0]) : dv.zzbLu));

      try {
         return new eb(decode(var3, "#;/?:@&=+$,"));
      } catch (UnsupportedEncodingException var4) {
         return dv.zzbLu;
      }
   }

   static String decode(String var0, String var1) throws UnsupportedEncodingException {
      Charset var2 = Charset.forName("UTF-8");
      StringBuilder var3 = new StringBuilder();
      int var4 = 0;

      while(true) {
         while(true) {
            while(var4 < var0.length()) {
               int var5 = var4;
               char var6;
               if ((var6 = var0.charAt(var4)) == '%') {
                  byte var7 = zzv(var0, var4);
                  var4 += 3;
                  if ((var7 & 128) != 0) {
                     int var8;
                     for(var8 = 0; (var7 << var8 & 128) != 0; ++var8) {
                        ;
                     }

                     if (var8 < 2 || var8 > 4) {
                        throw new UnsupportedEncodingException();
                     }

                     byte[] var9;
                     (var9 = new byte[var8])[0] = var7;

                     for(int var10 = 1; var10 < var8; ++var10) {
                        byte var11 = zzv(var0, var4);
                        var4 += 3;
                        if ((var11 & 192) != 128) {
                           throw new UnsupportedEncodingException();
                        }

                        var9[var10] = var11;
                     }

                     CharBuffer var12;
                     if ((var12 = var2.decode(ByteBuffer.wrap(var9))).length() == 1 && var1.indexOf(var12.charAt(0)) != -1) {
                        var3.append(var0.substring(var5, var4));
                     } else {
                        var3.append(var12);
                     }
                  } else if (var1.indexOf(var7) == -1) {
                     var3.append((char)var7);
                  } else {
                     var3.append(var0.substring(var4 - 3, var4));
                  }
               } else {
                  var3.append((char)var6);
                  ++var4;
               }
            }

            return var3.toString();
         }
      }
   }

   private static byte zzv(String var0, int var1) throws UnsupportedEncodingException {
      if (var1 + 3 <= var0.length() && var0.charAt(var1) == '%') {
         String var2;
         if ((var2 = var0.substring(var1 + 1, var1 + 3)).charAt(0) != '+' && var2.charAt(0) != '-') {
            try {
               return (byte)Integer.parseInt(var2, 16);
            } catch (NumberFormatException var3) {
               throw new UnsupportedEncodingException();
            }
         } else {
            throw new UnsupportedEncodingException();
         }
      } else {
         throw new UnsupportedEncodingException();
      }
   }
}
