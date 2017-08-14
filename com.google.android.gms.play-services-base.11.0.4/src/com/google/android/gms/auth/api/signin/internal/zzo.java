package com.google.android.gms.auth.api.signin.internal;

public final class zzo {
   private static int zzams = 31;
   private int zzamt = 1;

   public final zzo zzo(Object var1) {
      this.zzamt = zzams * this.zzamt + (var1 == null ? 0 : var1.hashCode());
      return this;
   }

   public final zzo zzP(boolean var1) {
      this.zzamt = zzams * this.zzamt + (var1 ? 1 : 0);
      return this;
   }

   public final int zzmJ() {
      return this.zzamt;
   }
}
