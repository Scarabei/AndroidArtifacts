package com.google.android.gms.nearby.messages.internal;

import java.util.Arrays;

public class zzc {
   private static final char[] zzbyU = "0123456789abcdef".toCharArray();
   private final byte[] zzbyV;

   protected zzc(byte[] var1) {
      this.zzbyV = var1;
   }

   public static String zzo(byte[] var0) {
      StringBuilder var1 = new StringBuilder(2 * var0.length);
      byte[] var2 = var0;
      int var3 = var0.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         byte var5 = var2[var4];
         var1.append(zzbyU[var5 >> 4 & 15]).append(zzbyU[var5 & 15]);
      }

      return var1.toString();
   }

   public static byte[] zzeE(String var0) {
      int var1;
      byte[] var2 = new byte[var1 = var0.length() / 2];

      for(int var3 = 0; var3 < var1; ++var3) {
         var2[var3] = (byte)((Character.digit(var0.charAt(2 * var3), 16) << 4) + Character.digit(var0.charAt(2 * var3 + 1), 16));
      }

      return var2;
   }

   public final byte[] getBytes() {
      return this.zzbyV;
   }

   public final String getHex() {
      return zzo(this.zzbyV);
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!var1.getClass().isAssignableFrom(this.getClass())) {
         return false;
      } else {
         zzc var2 = (zzc)var1;
         return Arrays.equals(this.zzbyV, var2.zzbyV);
      }
   }

   public int hashCode() {
      return Arrays.hashCode(this.zzbyV);
   }

   public String toString() {
      return zzo(this.zzbyV);
   }
}
