package com.google.android.gms.internal;

import com.google.android.gms.ads.doubleclick.PublisherAdView;

final class zzqi implements Runnable {
   // $FF: synthetic field
   private PublisherAdView zzIQ;
   // $FF: synthetic field
   private zzjz zzIR;
   // $FF: synthetic field
   private zzqh zzIS;

   zzqi(zzqh var1, PublisherAdView var2, zzjz var3) {
      this.zzIS = var1;
      this.zzIQ = var2;
      this.zzIR = var3;
      super();
   }

   public final void run() {
      if (this.zzIQ.zza(this.zzIR)) {
         zzqh.zza(this.zzIS).onPublisherAdViewLoaded(this.zzIQ);
      } else {
         zzajc.zzaT("Could not bind ad manager");
      }
   }
}
