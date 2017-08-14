package com.google.android.gms.internal;

import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;

@zzzn
public final class zznk extends zzni {
   private final OnCustomRenderedAdLoadedListener zzBh;

   public zznk(OnCustomRenderedAdLoadedListener var1) {
      this.zzBh = var1;
   }

   public final void zza(zzne var1) {
      this.zzBh.onCustomRenderedAdLoaded(new zznd(var1));
   }
}
