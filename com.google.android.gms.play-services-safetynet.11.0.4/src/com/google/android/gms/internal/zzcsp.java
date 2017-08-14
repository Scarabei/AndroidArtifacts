package com.google.android.gms.internal;

import java.util.Arrays;

public class zzcsp {
   private static final String TAG = zzcsp.class.getSimpleName();
   private static final char[] zzbyU = "0123456789abcdef".toCharArray();
   private final byte[] zzbyV;

   public zzcsp(byte[] var1) {
      this.zzbyV = var1;
   }

   public final byte[] getBytes() {
      return this.zzbyV;
   }

   public final zzcsp zzbu(int var1) {
      return new zzcsp(Arrays.copyOfRange(this.zzbyV, 0, 4));
   }

   public boolean equals(Object var1) {
      if (var1 == null) {
         return false;
      } else if (var1 == this) {
         return true;
      } else {
         try {
            zzcsp var2 = (zzcsp)var1;
            return Arrays.equals(this.zzbyV, var2.zzbyV);
         } catch (ClassCastException var3) {
            return false;
         }
      }
   }

   public int hashCode() {
      return 527 + Arrays.hashCode(this.zzbyV);
   }
}
