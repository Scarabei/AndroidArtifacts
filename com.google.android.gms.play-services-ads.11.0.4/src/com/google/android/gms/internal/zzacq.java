package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbe;

@zzzn
public final class zzacq extends zzacw {
   private final String zzVB;
   private final int zzWp;

   public zzacq(String var1, int var2) {
      this.zzVB = var1;
      this.zzWp = var2;
   }

   public final String getType() {
      return this.zzVB;
   }

   public final int getAmount() {
      return this.zzWp;
   }

   public final boolean equals(Object var1) {
      if (var1 != null && var1 instanceof zzacq) {
         zzacq var2 = (zzacq)var1;
         return zzbe.equal(this.zzVB, var2.zzVB) && zzbe.equal(this.zzWp, var2.zzWp);
      } else {
         return false;
      }
   }
}
