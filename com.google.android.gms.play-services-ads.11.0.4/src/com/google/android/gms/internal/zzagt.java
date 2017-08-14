package com.google.android.gms.internal;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@zzzn
public final class zzagt {
   private static final ThreadPoolExecutor zzZk;
   private static final ThreadPoolExecutor zzZl;

   public static zzajm zza(Runnable var0) {
      return zza(0, var0);
   }

   public static zzajm zza(int var0, Runnable var1) {
      return var0 == 1 ? zza(zzZl, new zzagu(var1)) : zza(zzZk, new zzagv(var1));
   }

   public static zzajm zza(ExecutorService var0, Callable var1) {
      zzajg var2 = new zzajg();

      try {
         Future var3 = var0.submit(new zzagw(var2, var1));
         var2.zzd(new zzagx(var2, var3));
      } catch (RejectedExecutionException var4) {
         zzafr.zzc("Thread execution is rejected.", var4);
         var2.cancel(true);
      }

      return var2;
   }

   public static zzajm zza(Callable var0) {
      return zza(zzZk, var0);
   }

   private static ThreadFactory zzaH(String var0) {
      return new zzagy(var0);
   }

   static {
      zzZk = new ThreadPoolExecutor(20, 20, 1L, TimeUnit.MINUTES, new LinkedBlockingQueue(), zzaH("Default"));
      zzZl = new ThreadPoolExecutor(5, 5, 1L, TimeUnit.MINUTES, new LinkedBlockingQueue(), zzaH("Loader"));
      zzZk.allowCoreThreadTimeOut(true);
      zzZl.allowCoreThreadTimeOut(true);
   }
}
