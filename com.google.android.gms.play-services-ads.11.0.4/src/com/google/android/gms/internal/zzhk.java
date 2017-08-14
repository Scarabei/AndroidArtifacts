package com.google.android.gms.internal;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;

@zzzn
public final class zzhk extends zzhf {
   private MessageDigest zzzd;

   public final byte[] zzy(String var1) {
      String[] var8;
      int var12;
      byte[] var10000;
      if ((var8 = var1.split(" ")).length == 1) {
         var12 = zzhj.zzA(var8[0]);
         ByteBuffer var13;
         (var13 = ByteBuffer.allocate(4)).order(ByteOrder.LITTLE_ENDIAN);
         var13.putInt(var12);
         var10000 = var13.array();
      } else {
         byte[] var9;
         int var10;
         if (var8.length < 5) {
            var9 = new byte[var8.length << 1];

            for(var10 = 0; var10 < var8.length; ++var10) {
               int var14 = (var12 = zzhj.zzA(var8[var10])) & '\uffff' ^ var12 >> 16;
               byte[] var11 = new byte[]{(byte)var14, (byte)(var14 >> 8)};
               var9[var10 << 1] = var11[0];
               var9[(var10 << 1) + 1] = var11[1];
            }

            var10000 = var9;
         } else {
            var9 = new byte[var8.length];

            for(var10 = 0; var10 < var8.length; ++var10) {
               var9[var10] = (byte)((var12 = zzhj.zzA(var8[var10])) & 255 ^ var12 >> 8 & 255 ^ var12 >> 16 & 255 ^ var12 >> 24);
            }

            var10000 = var9;
         }
      }

      byte[] var3 = var10000;
      this.zzzd = this.zzcW();
      Object var4 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzzd == null) {
            return new byte[0];
         } else {
            this.zzzd.reset();
            this.zzzd.update(var3);
            byte[] var5;
            byte[] var6 = new byte[(var5 = this.zzzd.digest()).length > 4 ? 4 : var5.length];
            System.arraycopy(var5, 0, var6, 0, var6.length);
            return var6;
         }
      }
   }
}
