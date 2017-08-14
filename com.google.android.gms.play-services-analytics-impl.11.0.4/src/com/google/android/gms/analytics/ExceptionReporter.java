package com.google.android.gms.analytics;

import android.content.Context;
import com.google.android.gms.internal.zzaob;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;

public class ExceptionReporter implements UncaughtExceptionHandler {
   private final UncaughtExceptionHandler zzady;
   private final Tracker zzadz;
   private final Context mContext;
   private ExceptionParser zzadA;
   private GoogleAnalytics zzadB;

   public ExceptionReporter(Tracker var1, UncaughtExceptionHandler var2, Context var3) {
      if (var1 == null) {
         throw new NullPointerException("tracker cannot be null");
      } else if (var3 == null) {
         throw new NullPointerException("context cannot be null");
      } else {
         this.zzady = var2;
         this.zzadz = var1;
         this.zzadA = new StandardExceptionParser(var3, new ArrayList());
         this.mContext = var3.getApplicationContext();
         String var10001 = String.valueOf(var2 == null ? "null" : var2.getClass().getName());
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "ExceptionReporter created, original handler is ".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("ExceptionReporter created, original handler is ");
         }

         zzaob.v(var10000);
      }
   }

   public ExceptionParser getExceptionParser() {
      return this.zzadA;
   }

   public void setExceptionParser(ExceptionParser var1) {
      this.zzadA = var1;
   }

   public void uncaughtException(Thread var1, Throwable var2) {
      String var3 = "UncaughtException";
      if (this.zzadA != null) {
         String var4 = var1 != null ? var1.getName() : null;
         var3 = this.zzadA.getDescription(var4, var2);
      }

      String var10001 = String.valueOf(var3);
      String var10000;
      if (var10001.length() != 0) {
         var10000 = "Reporting uncaught exception: ".concat(var10001);
      } else {
         String var10002 = new String;
         var10000 = var10002;
         var10002.<init>("Reporting uncaught exception: ");
      }

      zzaob.v(var10000);
      this.zzadz.send((new HitBuilders.ExceptionBuilder()).setDescription(var3).setFatal(true).build());
      if (this.zzadB == null) {
         this.zzadB = GoogleAnalytics.getInstance(this.mContext);
      }

      GoogleAnalytics var7 = this.zzadB;
      this.zzadB.dispatchLocalHits();
      var7.zzji().zzkv().zzkm();
      if (this.zzady != null) {
         zzaob.v("Passing exception to the original handler");
         this.zzady.uncaughtException(var1, var2);
      }

   }

   final UncaughtExceptionHandler zzjn() {
      return this.zzady;
   }
}
