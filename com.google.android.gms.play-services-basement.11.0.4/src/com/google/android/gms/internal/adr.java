package com.google.android.gms.internal;

import java.util.Arrays;

final class adr {
   final int tag;
   final byte[] zzbws;

   adr(int var1, byte[] var2) {
      this.tag = var1;
      this.zzbws = var2;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof adr)) {
         return false;
      } else {
         adr var2 = (adr)var1;
         return this.tag == var2.tag && Arrays.equals(this.zzbws, var2.zzbws);
      }
   }

   public final int hashCode() {
      int var1;
      return (var1 = 527 + this.tag) * 31 + Arrays.hashCode(this.zzbws);
   }
}
