package com.google.android.gms.internal;

import java.nio.charset.Charset;
import java.util.Arrays;

public final class adn {
   protected static final Charset UTF_8 = Charset.forName("UTF-8");
   private static Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
   public static final Object zzcsw = new Object();

   public static boolean equals(int[] var0, int[] var1) {
      if (var0 != null && var0.length != 0) {
         return Arrays.equals(var0, var1);
      } else {
         return var1 == null || var1.length == 0;
      }
   }

   public static boolean equals(long[] var0, long[] var1) {
      if (var0 != null && var0.length != 0) {
         return Arrays.equals(var0, var1);
      } else {
         return var1 == null || var1.length == 0;
      }
   }

   public static boolean equals(float[] var0, float[] var1) {
      if (var0 != null && var0.length != 0) {
         return Arrays.equals(var0, var1);
      } else {
         return var1 == null || var1.length == 0;
      }
   }

   public static boolean equals(boolean[] var0, boolean[] var1) {
      if (var0 != null && var0.length != 0) {
         return Arrays.equals(var0, var1);
      } else {
         return var1 == null || var1.length == 0;
      }
   }

   public static boolean zza(byte[][] var0, byte[][] var1) {
      int var2 = 0;
      int var3 = var0 == null ? 0 : var0.length;
      int var4 = 0;
      int var5 = var1 == null ? 0 : var1.length;

      while(true) {
         while(var2 >= var3 || var0[var2] != null) {
            while(var4 < var5 && var1[var4] == null) {
               ++var4;
            }

            boolean var6 = var2 >= var3;
            boolean var7 = var4 >= var5;
            if (var6 && var7) {
               return true;
            }

            if (var6 != var7) {
               return false;
            }

            if (!Arrays.equals(var0[var2], var1[var4])) {
               return false;
            }

            ++var2;
            ++var4;
         }

         ++var2;
      }
   }

   public static boolean equals(Object[] var0, Object[] var1) {
      int var2 = 0;
      int var3 = var0 == null ? 0 : var0.length;
      int var4 = 0;
      int var5 = var1 == null ? 0 : var1.length;

      while(true) {
         while(var2 >= var3 || var0[var2] != null) {
            while(var4 < var5 && var1[var4] == null) {
               ++var4;
            }

            boolean var6 = var2 >= var3;
            boolean var7 = var4 >= var5;
            if (var6 && var7) {
               return true;
            }

            if (var6 != var7) {
               return false;
            }

            if (!var0[var2].equals(var1[var4])) {
               return false;
            }

            ++var2;
            ++var4;
         }

         ++var2;
      }
   }

   public static int hashCode(int[] var0) {
      return var0 != null && var0.length != 0 ? Arrays.hashCode(var0) : 0;
   }

   public static int hashCode(long[] var0) {
      return var0 != null && var0.length != 0 ? Arrays.hashCode(var0) : 0;
   }

   public static int hashCode(float[] var0) {
      return var0 != null && var0.length != 0 ? Arrays.hashCode(var0) : 0;
   }

   public static int hashCode(boolean[] var0) {
      return var0 != null && var0.length != 0 ? Arrays.hashCode(var0) : 0;
   }

   public static int zzc(byte[][] var0) {
      int var1 = 0;
      int var2 = 0;

      for(int var3 = var0 == null ? 0 : var0.length; var2 < var3; ++var2) {
         byte[] var4;
         if ((var4 = var0[var2]) != null) {
            var1 = var1 * 31 + Arrays.hashCode(var4);
         }
      }

      return var1;
   }

   public static int hashCode(Object[] var0) {
      int var1 = 0;
      int var2 = 0;

      for(int var3 = var0 == null ? 0 : var0.length; var2 < var3; ++var2) {
         Object var4;
         if ((var4 = var0[var2]) != null) {
            var1 = var1 * 31 + var4.hashCode();
         }
      }

      return var1;
   }

   public static void zza(adj var0, adj var1) {
      if (var0.zzcso != null) {
         var1.zzcso = (adl)var0.zzcso.clone();
      }

   }
}
