package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd;

@zzzn
public final class zzqg extends zzpx {
   private final NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener zzIO;

   public zzqg(NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener var1) {
      this.zzIO = var1;
   }

   public final void zza(zzpj var1) {
      this.zzIO.onCustomTemplateAdLoaded(new zzpm(var1));
   }
}
