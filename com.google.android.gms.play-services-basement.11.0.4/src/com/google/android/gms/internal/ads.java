package com.google.android.gms.internal;

import java.io.IOException;

public final class ads {
   private static int zzcsy = 11;
   private static int zzcsz = 12;
   private static int zzcsA = 16;
   private static int zzcsB = 26;
   public static final int[] zzcsC = new int[0];
   public static final long[] zzcsD = new long[0];
   public static final float[] zzcsE = new float[0];
   private static double[] zzcsF = new double[0];
   public static final boolean[] zzcsG = new boolean[0];
   public static final String[] EMPTY_STRING_ARRAY = new String[0];
   public static final byte[][] zzcsH = new byte[0][];
   public static final byte[] zzcsI = new byte[0];

   public static final int zzb(adg var0, int var1) throws IOException {
      int var2 = 1;
      int var3 = var0.getPosition();
      var0.zzcm(var1);

      while(var0.zzLA() == var1) {
         var0.zzcm(var1);
         ++var2;
      }

      var0.zzq(var3, var1);
      return var2;
   }
}
