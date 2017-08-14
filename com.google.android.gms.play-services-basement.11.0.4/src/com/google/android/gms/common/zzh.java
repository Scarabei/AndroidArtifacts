package com.google.android.gms.common;

import java.util.Arrays;

final class zzh extends zzg {
   private final byte[] zzaAh;

   zzh(byte[] var1) {
      super(Arrays.copyOfRange(var1, 0, 25));
      this.zzaAh = var1;
   }

   final byte[] getBytes() {
      return this.zzaAh;
   }
}
