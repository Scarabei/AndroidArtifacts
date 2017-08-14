package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;

public class zzcsr {
   private static final String TAG = zzcsr.class.getSimpleName();
   private int[] zzbCi;
   private int zzbBK;

   public zzcsr(DataHolder var1, int var2) {
      if (var1 != null) {
         if (var1.getStatusCode() == 0) {
            int[] var3;
            if ((var3 = zzs(var1.zzg("", var2, var1.zzat(var2)))) != null) {
               if (var3.length >= 3) {
                  if (var3[0] == 1) {
                     if (var3[1] == 1936614772) {
                        this.zzbCi = new int[var3.length];
                        System.arraycopy(var3, 0, this.zzbCi, 0, var3.length);
                        this.zzbBK = this.zzbCi[2];
                     }
                  }
               }
            }
         }
      }
   }

   public final int getThreatType() {
      return this.zzbBK;
   }

   public final boolean zzr(byte[] var1) {
      if (this.zzbCi == null) {
         return false;
      } else {
         long var2 = ((long)var1[3] & 255L | ((long)var1[2] & 255L) << 8 | ((long)var1[1] & 255L) << 16 | ((long)var1[0] & 255L) << 24) & 4294967295L;
         int[] var4 = this.zzbCi;
         int var5 = 3;
         int var6 = var4.length - 1;

         int var10000;
         while(true) {
            if (var5 > var6) {
               var10000 = -1;
               break;
            }

            int var7 = var5 + (var6 - var5) / 2;
            long var8;
            if ((var8 = (long)var4[var7] & 4294967295L) == var2) {
               var10000 = var7;
               break;
            }

            if (var8 < var2) {
               var5 = var7 + 1;
            } else {
               var6 = var7 - 1;
            }
         }

         return var10000 != -1;
      }
   }

   private static int[] zzs(byte[] var0) {
      if (var0 != null && var0.length % 4 == 0) {
         int[] var1 = new int[var0.length / 4];

         for(int var2 = 0; var2 < var0.length; var2 += 4) {
            long var3 = (long)var0[var2 + 3] & 255L | ((long)var0[var2 + 2] & 255L) << 8 | ((long)var0[var2 + 1] & 255L) << 16 | ((long)var0[var2] & 255L) << 24;
            var1[var2 / 4] = (int)var3;
         }

         return var1;
      } else {
         return null;
      }
   }
}
