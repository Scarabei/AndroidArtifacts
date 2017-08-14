package com.google.android.gms.internal;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class zzbac {
   public static long zzf(byte[] var0) {
      int var2 = var0.length;
      if (var2 >= 0 && var2 <= var0.length) {
         long var5;
         long var7;
         long var9;
         long var11;
         long var13;
         if (var2 <= 32) {
            if (var2 <= 16) {
               if (var2 >= 8) {
                  var5 = -7286425919675154353L + (long)(var2 << 1);
                  var7 = zzc(var0, 0) + -7286425919675154353L;
                  var11 = Long.rotateRight(var9 = zzc(var0, var2 + 0 - 8), 37) * var5 + var7;
                  var13 = (Long.rotateRight(var7, 25) + var9) * var5;
                  return zza(var11, var13, var5);
               } else if (var2 >= 4) {
                  var5 = -7286425919675154353L + (long)(var2 << 1);
                  var7 = (long)zzb(var0, 0) & 4294967295L;
                  return zza((long)var2 + (var7 << 3), (long)zzb(var0, var2 + 0 - 4) & 4294967295L, var5);
               } else if (var2 > 0) {
                  byte var27 = var0[0];
                  byte var6 = var0[0 + (var2 >> 1)];
                  byte var28 = var0[0 + (var2 - 1)];
                  int var8 = (var27 & 255) + ((var6 & 255) << 8);
                  int var29 = var2 + ((var28 & 255) << 2);
                  return ((long)var8 * -7286425919675154353L ^ (long)var29 * -4348849565147123417L ^ ((long)var8 * -7286425919675154353L ^ (long)var29 * -4348849565147123417L) >>> 47) * -7286425919675154353L;
               } else {
                  return -7286425919675154353L;
               }
            } else {
               var5 = -7286425919675154353L + (long)(var2 << 1);
               var7 = zzc(var0, 0) * -5435081209227447693L;
               var9 = zzc(var0, 8);
               var11 = zzc(var0, var2 + 0 - 8) * var5;
               var13 = zzc(var0, var2 + 0 - 16) * -7286425919675154353L;
               return zza(Long.rotateRight(var7 + var9, 43) + Long.rotateRight(var11, 30) + var13, var7 + Long.rotateRight(var9 + -7286425919675154353L, 18) + var11, var5);
            }
         } else if (var2 <= 64) {
            var5 = -7286425919675154353L + (long)(var2 << 1);
            var7 = zzc(var0, 0) * -7286425919675154353L;
            var9 = zzc(var0, 8);
            var11 = zzc(var0, var2 + 0 - 8) * var5;
            var13 = zzc(var0, var2 + 0 - 16) * -7286425919675154353L;
            long var15;
            long var17 = zza(var15 = Long.rotateRight(var7 + var9, 43) + Long.rotateRight(var11, 30) + var13, var7 + Long.rotateRight(var9 + -7286425919675154353L, 18) + var11, var5);
            long var19 = zzc(var0, 16) * var5;
            long var21 = zzc(var0, 24);
            long var23 = (var15 + zzc(var0, var2 + 0 - 32)) * var5;
            long var25 = (var17 + zzc(var0, var2 + 0 - 24)) * var5;
            return zza(Long.rotateRight(var19 + var21, 43) + Long.rotateRight(var23, 30) + var25, var19 + Long.rotateRight(var21 + var7, 18) + var23, var5);
         } else {
            return zza(var0, 0, var2);
         }
      } else {
         throw new IndexOutOfBoundsException((new StringBuilder(67)).append("Out of bound index with offput: 0 and length: ").append(var2).toString());
      }
   }

   private static long zza(long var0, long var2, long var4) {
      long var6 = (var0 ^ var2) * var4 ^ (var0 ^ var2) * var4 >>> 47;
      return ((var2 ^ var6) * var4 ^ (var2 ^ var6) * var4 >>> 47) * var4;
   }

   private static void zza(byte[] var0, int var1, long var2, long var4, long[] var6) {
      long var7 = zzc(var0, var1);
      long var9 = zzc(var0, var1 + 8);
      long var11 = zzc(var0, var1 + 16);
      long var13 = zzc(var0, var1 + 24);
      var2 += var7;
      var4 = Long.rotateRight(var4 + var2 + var13, 21);
      long var15 = var2;
      var2 = var2 + var9 + var11;
      var4 += Long.rotateRight(var2, 44);
      var6[0] = var2 + var13;
      var6[1] = var4 + var15;
   }

   private static long zza(byte[] var0, int var1, int var2) {
      long var5 = 2480279821605975764L;
      long var7 = 1390051526045402406L;
      long[] var9 = new long[2];
      long[] var10 = new long[2];
      long var3 = 95310865018149119L + zzc(var0, 0);
      int var11;
      int var12 = (var11 = 0 + ((var2 - 1) / 64 << 6)) + (var2 - 1 & 63) - 63;

      long var13;
      do {
         var3 = Long.rotateRight(var3 + var5 + var9[0] + zzc(var0, var1 + 8), 37) * -5435081209227447693L;
         var5 = Long.rotateRight(var5 + var9[1] + zzc(var0, var1 + 48), 42) * -5435081209227447693L;
         var3 ^= var10[1];
         var5 += var9[0] + zzc(var0, var1 + 40);
         var7 = Long.rotateRight(var7 + var10[0], 33) * -5435081209227447693L;
         zza(var0, var1, var9[1] * -5435081209227447693L, var3 + var10[0], var9);
         zza(var0, var1 + 32, var7 + var10[1], var5 + zzc(var0, var1 + 16), var10);
         var13 = var3;
         var3 = var7;
         var7 = var13;
         var1 += 64;
      } while(var1 != var11);

      var13 = -5435081209227447693L + ((var13 & 255L) << 1);
      var10[0] += (long)(var2 - 1 & 63);
      var9[0] += var10[0];
      var10[0] += var9[0];
      var3 = Long.rotateRight(var3 + var5 + var9[0] + zzc(var0, var12 + 8), 37) * var13;
      var5 = Long.rotateRight(var5 + var9[1] + zzc(var0, var12 + 48), 42) * var13;
      var3 ^= var10[1] * 9L;
      var5 += var9[0] * 9L + zzc(var0, var12 + 40);
      var7 = Long.rotateRight(var7 + var10[0], 33) * var13;
      zza(var0, var12, var9[1] * var13, var3 + var10[0], var9);
      zza(var0, var12 + 32, var7 + var10[1], var5 + zzc(var0, var12 + 16), var10);
      return zza(zza(var9[0], var10[0], var13) + (var5 ^ var5 >>> 47) * -4348849565147123417L + var3, zza(var9[1], var10[1], var13) + var7, var13);
   }

   private static int zzb(byte[] var0, int var1) {
      return var0[var1] & 255 | (var0[var1 + 1] & 255) << 8 | (var0[var1 + 2] & 255) << 16 | (var0[var1 + 3] & 255) << 24;
   }

   private static long zzc(byte[] var0, int var1) {
      ByteBuffer var2;
      (var2 = ByteBuffer.wrap(var0, var1, 8)).order(ByteOrder.LITTLE_ENDIAN);
      return var2.getLong();
   }
}
