package com.google.android.gms.internal;

import java.lang.Thread.UncaughtExceptionHandler;

final class zzzj implements UncaughtExceptionHandler {
   // $FF: synthetic field
   private UncaughtExceptionHandler zzSj;
   // $FF: synthetic field
   private zzzi zzSk;

   zzzj(zzzi var1, UncaughtExceptionHandler var2) {
      this.zzSk = var1;
      this.zzSj = var2;
      super();
   }

   public final void uncaughtException(Thread var1, Throwable var2) {
      try {
         this.zzSk.zza(var1, var2);
         return;
      } catch (Throwable var6) {
         zzafr.e("AdMob exception reporter failed reporting the exception.");
      } finally {
         if (this.zzSj != null) {
            this.zzSj.uncaughtException(var1, var2);
         }

      }

   }
}
