package com.google.android.gms.internal;

import android.support.annotation.WorkerThread;

final class zzcug implements Runnable {
   // $FF: synthetic field
   private zzcuf zzbHS;

   zzcug(zzcuf var1) {
      this.zzbHS = var1;
      super();
   }

   @WorkerThread
   public final void run() {
      if (zzcuf.zza(this.zzbHS) == 2) {
         zzcuf.zzb(this.zzbHS).dispatch();
      }

   }
}
