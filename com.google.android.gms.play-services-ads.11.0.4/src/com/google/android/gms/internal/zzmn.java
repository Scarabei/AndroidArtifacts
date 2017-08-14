package com.google.android.gms.internal;

import java.util.concurrent.Callable;

final class zzmn implements Callable {
   // $FF: synthetic field
   private zzme zzBU;
   // $FF: synthetic field
   private zzmm zzBV;

   zzmn(zzmm var1, zzme var2) {
      this.zzBV = var1;
      this.zzBU = var2;
      super();
   }

   public final Object call() {
      return this.zzBU.zza(zzmm.zza(this.zzBV));
   }
}
