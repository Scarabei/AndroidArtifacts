package com.google.android.gms.internal;

import android.app.Activity;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzu;

final class zzwg implements Runnable {
   // $FF: synthetic field
   private AdOverlayInfoParcel zzup;
   // $FF: synthetic field
   private zzwd zzNr;

   zzwg(zzwd var1, AdOverlayInfoParcel var2) {
      this.zzNr = var1;
      this.zzup = var2;
      super();
   }

   public final void run() {
      com.google.android.gms.ads.internal.zzbs.zzbx();
      Activity var10000 = zzwd.zzb(this.zzNr);
      AdOverlayInfoParcel var2 = this.zzup;
      zzu.zza(var10000, var2, true);
   }
}
