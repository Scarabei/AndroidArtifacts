package com.google.android.gms.internal;

import java.nio.charset.Charset;
import java.security.MessageDigest;

@zzzn
public final class zzho extends zzhf {
   private MessageDigest zzzd;
   private final int zzzg;
   private final int zzzh;

   public zzho(int var1) {
      int var2 = var1 / 8;
      if (var1 % 8 > 0) {
         ++var2;
      }

      this.zzzg = var2;
      this.zzzh = var1;
   }

   public final byte[] zzy(String var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzzd = this.zzcW();
         if (this.zzzd == null) {
            return new byte[0];
         } else {
            this.zzzd.reset();
            this.zzzd.update(var1.getBytes(Charset.forName("UTF-8")));
            byte[] var3;
            byte[] var4 = new byte[(var3 = this.zzzd.digest()).length > this.zzzg ? this.zzzg : var3.length];
            System.arraycopy(var3, 0, var4, 0, var4.length);
            if (this.zzzh % 8 > 0) {
               long var5 = 0L;

               int var7;
               for(var7 = 0; var7 < var4.length; ++var7) {
                  if (var7 > 0) {
                     var5 <<= 8;
                  }

                  var5 += (long)(var4[var7] & 255);
               }

               var7 = 8 - this.zzzh % 8;
               var5 >>>= var7;

               for(int var8 = this.zzzg - 1; var8 >= 0; --var8) {
                  var4[var8] = (byte)((int)(var5 & 255L));
                  var5 >>>= 8;
               }
            }

            return var4;
         }
      }
   }
}
