package com.google.android.gms.analytics;

import android.util.Log;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.FutureTask;

final class zzn extends FutureTask {
   // $FF: synthetic field
   private zzl.zza zzael;

   zzn(zzl.zza var1, Runnable var2, Object var3) {
      this.zzael = var1;
      super(var2, var3);
   }

   protected final void setException(Throwable var1) {
      UncaughtExceptionHandler var2;
      if ((var2 = zzl.zzb(this.zzael.zzaek)) != null) {
         var2.uncaughtException(Thread.currentThread(), var1);
      } else if (Log.isLoggable("GAv4", 6)) {
         String var3 = String.valueOf(var1);
         Log.e("GAv4", (new StringBuilder(37 + String.valueOf(var3).length())).append("MeasurementExecutor: job failed with ").append(var3).toString());
      }

      super.setException(var1);
   }
}
