package com.google.android.gms.internal;

import android.view.ViewGroup;

final class zzauu implements Runnable {
   // $FF: synthetic field
   private zzaut zzasG;

   zzauu(zzaut var1) {
      this.zzasG = var1;
      super();
   }

   public final void run() {
      if (zzaus.zza(this.zzasG.zzasF)) {
         ((ViewGroup)zzaus.zzb(this.zzasG.zzasF).getWindow().getDecorView()).removeView(this.zzasG.zzasF);
         if (zzaus.zzc(this.zzasG.zzasF) != null) {
            zzaus.zzc(this.zzasG.zzasF).onOverlayDismissed();
         }

         zzaus.zzd(this.zzasG.zzasF);
      }

   }
}
