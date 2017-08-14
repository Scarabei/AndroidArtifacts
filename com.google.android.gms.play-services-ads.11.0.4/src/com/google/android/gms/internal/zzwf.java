package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.overlay.zzw;

final class zzwf implements zzw {
   // $FF: synthetic field
   private zzwd zzNr;

   zzwf(zzwd var1) {
      this.zzNr = var1;
      super();
   }

   public final void zzaA() {
      zzajc.zzaC("AdMobCustomTabsAdapter overlay is closed.");
      zzwd.zza(this.zzNr).onAdClosed(this.zzNr);

      try {
         zzwd.zzc(this.zzNr).zzc(zzwd.zzb(this.zzNr));
      } catch (Exception var2) {
         zzajc.zzb("Exception while unbinding from CustomTabsService.", var2);
      }
   }

   public final void onPause() {
      zzajc.zzaC("AdMobCustomTabsAdapter overlay is paused.");
   }

   public final void onResume() {
      zzajc.zzaC("AdMobCustomTabsAdapter overlay is resumed.");
   }

   public final void zzaB() {
      zzajc.zzaC("Opening AdMobCustomTabsAdapter overlay.");
      zzwd.zza(this.zzNr).onAdOpened(this.zzNr);
   }
}
