package com.google.android.gms.ads.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;

final class zzao implements Runnable {
   // $FF: synthetic field
   private AdOverlayInfoParcel zzup;
   // $FF: synthetic field
   private zzan zzuq;

   zzao(zzan var1, AdOverlayInfoParcel var2) {
      this.zzuq = var1;
      this.zzup = var2;
      super();
   }

   public final void run() {
      zzbs.zzbx();
      AdOverlayInfoParcel var2 = this.zzup;
      Context var10001 = this.zzuq.zzun.zzsP.zzqD;
      com.google.android.gms.ads.internal.overlay.zzu.zza(this.zzuq.zzun.zzsP.zzqD, var2, true);
   }
}
