package com.google.android.gms.internal;

import java.util.PriorityQueue;

@zzzn
public final class zzhm {
   public static void zza(String[] var0, int var1, int var2, PriorityQueue var3) {
      long var4;
      if (var0.length < var2) {
         var4 = zzb(var0, 0, var0.length);
         zza(var1, var4, zza(var0, 0, var0.length), var0.length, var3);
      } else {
         var4 = zzb(var0, 0, var2);
         zza(var1, var4, zza(var0, 0, var2), var2, var3);
         long var6 = zza(16785407L, var2 - 1);

         for(int var8 = 1; var8 < var0.length - var2 + 1; ++var8) {
            int var10000 = zzhj.zzA(var0[var8 - 1]);
            int var10 = zzhj.zzA(var0[var8 + var2 - 1]);
            int var9 = var10000;
            long var15 = var6 * (((long)var9 + 2147483647L) % 1073807359L) % 1073807359L;
            long var11 = (var4 + 1073807359L - var15) % 1073807359L * 16785407L % 1073807359L;
            long var17 = ((long)var10 + 2147483647L) % 1073807359L;
            var4 = (var11 + var17) % 1073807359L;
            zza(var1, var4, zza(var0, var8, var2), var0.length, var3);
         }

      }
   }

   private static void zza(int var0, long var1, String var3, int var4, PriorityQueue var5) {
      zzhn var6 = new zzhn(var1, var3, var4);
      if (var5.size() != var0 || ((zzhn)var5.peek()).zzzf <= var6.zzzf && ((zzhn)var5.peek()).value <= var6.value) {
         if (!var5.contains(var6)) {
            var5.add(var6);
            if (var5.size() > var0) {
               var5.poll();
            }

         }
      }
   }

   private static String zza(String[] var0, int var1, int var2) {
      if (var0.length < var1 + var2) {
         zzafr.e("Unable to construct shingle");
         return "";
      } else {
         StringBuffer var3 = new StringBuffer();

         for(int var4 = var1; var4 < var1 + var2 - 1; ++var4) {
            var3.append(var0[var4]);
            var3.append(' ');
         }

         var3.append(var0[var1 + var2 - 1]);
         return var3.toString();
      }
   }

   private static long zzb(String[] var0, int var1, int var2) {
      long var3 = ((long)zzhj.zzA(var0[0]) + 2147483647L) % 1073807359L;

      for(int var5 = 1; var5 < var2; ++var5) {
         var3 = (var3 * 16785407L % 1073807359L + ((long)zzhj.zzA(var0[var5]) + 2147483647L) % 1073807359L) % 1073807359L;
      }

      return var3;
   }

   private static long zza(long var0, int var2) {
      if (var2 == 0) {
         return 1L;
      } else if (var2 == 1) {
         return var0;
      } else {
         return var2 % 2 == 0 ? zza(var0 * var0 % 1073807359L, var2 / 2) % 1073807359L : var0 * (zza(var0 * var0 % 1073807359L, var2 / 2) % 1073807359L) % 1073807359L;
      }
   }
}
