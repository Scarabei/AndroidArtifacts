package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.js.zzai;
import java.util.Map;

final class zzok implements zzrd {
   // $FF: synthetic field
   private zzai zzIj;
   // $FF: synthetic field
   private zzog zzIk;

   zzok(zzog var1, zzai var2) {
      this.zzIk = var1;
      this.zzIj = var2;
      super();
   }

   public final void zza(zzaka var1, Map var2) {
      zzaka var3;
      if ((var3 = (zzaka)zzog.zza(this.zzIk).get()) == null) {
         this.zzIj.zzb("/hideOverlay", (zzrd)this);
      } else {
         var3.getView().setVisibility(8);
      }
   }
}
