package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.zzyh;

final class zzbh implements Runnable {
   // $FF: synthetic field
   private zzbb zzuQ;

   zzbh(zzbb var1) {
      this.zzuQ = var1;
      super();
   }

   public final void run() {
      synchronized(zzbb.zza(this.zzuQ)) {
         zzyh var2;
         if ((var2 = this.zzuQ.zzbi()) != null) {
            var2.zzfd();
         }

      }
   }
}
