package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeContentAd;

@zzzn
public final class zzqe extends zzpr {
   private final NativeContentAd.OnContentAdLoadedListener zzIM;

   public zzqe(NativeContentAd.OnContentAdLoadedListener var1) {
      this.zzIM = var1;
   }

   public final void zza(zzpf var1) {
      this.zzIM.onContentAdLoaded(new zzpi(var1));
   }
}
