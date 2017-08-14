package com.google.android.gms.common.util;

import java.util.ArrayList;
import java.util.Arrays;

public final class zzb {
   public static Integer[] zza(int[] var0) {
      if (var0 == null) {
         return null;
      } else {
         int var1;
         Integer[] var2 = new Integer[var1 = var0.length];

         for(int var3 = 0; var3 < var1; ++var3) {
            var2[var3] = var0[var3];
         }

         return var2;
      }
   }

   public static void zza(StringBuilder var0, Object[] var1) {
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         if (var3 != 0) {
            var0.append(",");
         }

         var0.append(var1[var3].toString());
      }

   }

   public static void zza(StringBuilder var0, long[] var1) {
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         if (var3 != 0) {
            var0.append(",");
         }

         var0.append(Long.toString(var1[var3]));
      }

   }

   public static void zza(StringBuilder var0, float[] var1) {
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         if (var3 != 0) {
            var0.append(",");
         }

         var0.append(Float.toString(var1[var3]));
      }

   }

   public static void zza(StringBuilder var0, double[] var1) {
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         if (var3 != 0) {
            var0.append(",");
         }

         var0.append(Double.toString(var1[var3]));
      }

   }

   public static void zza(StringBuilder var0, boolean[] var1) {
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         if (var3 != 0) {
            var0.append(",");
         }

         var0.append(Boolean.toString(var1[var3]));
      }

   }

   public static void zza(StringBuilder var0, String[] var1) {
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         if (var3 != 0) {
            var0.append(",");
         }

         var0.append("\"").append(var1[var3]).append("\"");
      }

   }

   public static byte[] zza(byte[]... var0) {
      if (var0.length == 0) {
         return new byte[0];
      } else {
         int var1 = 0;

         for(int var2 = 0; var2 < var0.length; ++var2) {
            var1 += var0[var2].length;
         }

         byte[] var6 = Arrays.copyOf(var0[0], var1);
         int var3 = var0[0].length;

         for(int var4 = 1; var4 < var0.length; ++var4) {
            byte[] var5;
            System.arraycopy(var5 = var0[var4], 0, var6, var3, var5.length);
            var3 += var5.length;
         }

         return var6;
      }
   }

   public static ArrayList zza(Object[] var0) {
      int var1 = var0.length;
      ArrayList var2 = new ArrayList(var1);

      for(int var3 = 0; var3 < var1; ++var3) {
         var2.add(var0[var3]);
      }

      return var2;
   }
}
