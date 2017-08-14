package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeAppInstallAd;

@zzzn
public final class zzqd extends zzpo {
   private final NativeAppInstallAd.OnAppInstallAdLoadedListener zzIL;

   public zzqd(NativeAppInstallAd.OnAppInstallAdLoadedListener var1) {
      this.zzIL = var1;
   }

   public final void zza(zzpb var1) {
      this.zzIL.onAppInstallAdLoaded(new zzpe(var1));
   }
}
