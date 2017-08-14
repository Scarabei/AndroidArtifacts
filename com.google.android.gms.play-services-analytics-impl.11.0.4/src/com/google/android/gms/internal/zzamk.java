package com.google.android.gms.internal;

import java.lang.Thread.UncaughtExceptionHandler;

final class zzamk implements UncaughtExceptionHandler {
   // $FF: synthetic field
   private zzamj zzaga;

   zzamk(zzamj var1) {
      this.zzaga = var1;
      super();
   }

   public final void uncaughtException(Thread var1, Throwable var2) {
      zzaoc var3;
      if ((var3 = this.zzaga.zzkF()) != null) {
         var3.zze("Job execution failed", var2);
      }

   }
}
