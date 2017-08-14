package com.google.android.gms.internal;

import android.os.Process;
import java.util.concurrent.Callable;

final class zzagw implements Runnable {
   // $FF: synthetic field
   private zzajg zzZn;
   // $FF: synthetic field
   private Callable zzZo;

   zzagw(zzajg var1, Callable var2) {
      this.zzZn = var1;
      this.zzZo = var2;
      super();
   }

   public final void run() {
      try {
         Process.setThreadPriority(10);
         this.zzZn.zzg(this.zzZo.call());
      } catch (Exception var2) {
         com.google.android.gms.ads.internal.zzbs.zzbD().zza((Throwable)var2, (String)"AdThreadPool.submit");
         this.zzZn.zzb(var2);
      }
   }
}
