package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.zzir;

final class zzaj implements Runnable {
   // $FF: synthetic field
   private zzir zztY;
   // $FF: synthetic field
   private zzai zztZ;

   zzaj(zzai var1, zzir var2) {
      this.zztZ = var1;
      this.zztY = var2;
      super();
   }

   public final void run() {
      synchronized(zzai.zza(this.zztZ)) {
         if (zzai.zzb(this.zztZ)) {
            zzai.zza(this.zztZ, this.zztY);
         } else {
            zzai.zzb(this.zztZ, this.zztY);
         }

      }
   }
}
