package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.js.zzai;

final class zzom extends zzym {
   // $FF: synthetic field
   private zzog zzIk;

   zzom(zzog var1) {
      this.zzIk = var1;
      super();
   }

   public final void zzd(zzai var1) {
      zzaka var2;
      if ((var2 = (zzaka)zzog.zza(this.zzIk).get()) != null) {
         var1.zza("/loadHtml", zzog.zza(this.zzIk, var1));
         var1.zza("/showOverlay", zzog.zzb(this.zzIk, var1));
         var1.zza("/hideOverlay", zzog.zzc(this.zzIk, var1));
         var2.zziw().zza("/sendMessageToSdk", zzog.zzd(this.zzIk, var1));
      }
   }
}
