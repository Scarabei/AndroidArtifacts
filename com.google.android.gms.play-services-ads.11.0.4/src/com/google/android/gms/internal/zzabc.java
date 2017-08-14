package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.js.zzai;

final class zzabc implements zzajs {
   // $FF: synthetic field
   private zzabb zzUf;

   zzabc(zzabb var1) {
      this.zzUf = var1;
      super();
   }

   // $FF: synthetic method
   public final void zzc(Object var1) {
      zzai var3 = (zzai)var1;
      zzabc var2 = this;

      try {
         var3.zza("AFMA_getAdapterLessMediationAd", var2.zzUf.zzUd);
      } catch (Exception var5) {
         zzafr.zzb("Error requesting an ad url", var5);
         zzaaz.zzgD().zzT(this.zzUf.zzUe);
      }
   }
}
