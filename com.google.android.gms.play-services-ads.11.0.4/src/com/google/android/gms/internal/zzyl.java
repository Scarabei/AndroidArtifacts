package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.js.zza;

final class zzyl implements Runnable {
   // $FF: synthetic field
   private zza zzRu;

   zzyl(zzyh var1, zza var2) {
      this.zzRu = var2;
      super();
   }

   public final void run() {
      this.zzRu.destroy();
   }
}
