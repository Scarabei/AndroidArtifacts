package com.google.android.gms.internal;

import java.lang.Thread.UncaughtExceptionHandler;

final class zzzk implements UncaughtExceptionHandler {
   // $FF: synthetic field
   private UncaughtExceptionHandler zzSl;
   // $FF: synthetic field
   private zzzi zzSk;

   zzzk(zzzi var1, UncaughtExceptionHandler var2) {
      this.zzSk = var1;
      this.zzSl = var2;
      super();
   }

   public final void uncaughtException(Thread var1, Throwable var2) {
      try {
         this.zzSk.zza(var1, var2);
         return;
      } catch (Throwable var6) {
         zzafr.e("AdMob exception reporter failed reporting the exception.");
      } finally {
         if (this.zzSl != null) {
            this.zzSl.uncaughtException(var1, var2);
         }

      }

   }
}
