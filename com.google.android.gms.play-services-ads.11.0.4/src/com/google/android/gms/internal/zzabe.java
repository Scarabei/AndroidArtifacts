package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.js.zzy;

final class zzabe implements Runnable {
   // $FF: synthetic field
   private zzaaz zzUc;

   zzabe(zzaaz var1) {
      this.zzUc = var1;
      super();
   }

   public final void run() {
      if (zzaaz.zzb(this.zzUc) != null) {
         zzaaz.zzb(this.zzUc).release();
         zzaaz.zza((zzaaz)this.zzUc, (zzy)null);
      }

   }
}
