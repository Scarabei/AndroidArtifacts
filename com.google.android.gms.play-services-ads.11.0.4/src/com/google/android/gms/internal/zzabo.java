package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.js.zzai;

final class zzabo implements zzajs {
   // $FF: synthetic field
   private zzmz zzUD;
   // $FF: synthetic field
   private zzabn zzUE;

   zzabo(zzabn var1, zzmz var2) {
      this.zzUE = var1;
      this.zzUD = var2;
      super();
   }

   // $FF: synthetic method
   public final void zzc(Object var1) {
      zzai var3 = (zzai)var1;
      zzabo var2 = this;
      this.zzUE.zztd.zza(this.zzUD, "jsf");
      this.zzUE.zztd.zzdT();
      var3.zza("/invalidRequest", this.zzUE.zzUA.zzUO);
      var3.zza("/loadAdURL", this.zzUE.zzUA.zzUP);
      var3.zza("/loadAd", this.zzUE.zzUA.zzUQ);

      try {
         var3.zzi("AFMA_getAd", var2.zzUE.zzUC);
      } catch (Exception var5) {
         zzafr.zzb("Error requesting an ad url", var5);
      }
   }
}
