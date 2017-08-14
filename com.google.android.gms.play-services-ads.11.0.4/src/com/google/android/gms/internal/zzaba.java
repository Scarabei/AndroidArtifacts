package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.js.zzy;

final class zzaba implements Runnable {
   // $FF: synthetic field
   private zzafg zzsW;
   // $FF: synthetic field
   private zzaaz zzUc;

   zzaba(zzaaz var1, zzafg var2) {
      this.zzUc = var1;
      this.zzsW = var2;
      super();
   }

   public final void run() {
      zzaaz.zza(this.zzUc).zza(this.zzsW);
      if (zzaaz.zzb(this.zzUc) != null) {
         zzaaz.zzb(this.zzUc).release();
         zzaaz.zza((zzaaz)this.zzUc, (zzy)null);
      }

   }
}
