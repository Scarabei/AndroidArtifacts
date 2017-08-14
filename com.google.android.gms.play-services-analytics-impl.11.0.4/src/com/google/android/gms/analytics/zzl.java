package com.google.android.gms.analytics;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Process;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzalk;
import com.google.android.gms.internal.zzalp;
import com.google.android.gms.internal.zzaos;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzl {
   private static volatile zzl zzaed;
   private final Context mContext;
   private final List zzaee;
   private final zzg zzaef;
   private final zzl.zza zzaeg;
   private volatile zzalk zzaeh;
   private UncaughtExceptionHandler zzaei;

   private zzl(Context var1) {
      Context var2;
      zzbo.zzu(var2 = var1.getApplicationContext());
      this.mContext = var2;
      this.zzaeg = new zzl.zza();
      this.zzaee = new CopyOnWriteArrayList();
      this.zzaef = new zzg();
   }

   public static zzl zzae(Context var0) {
      zzbo.zzu(var0);
      if (zzaed == null) {
         Class var1 = zzl.class;
         synchronized(zzl.class) {
            if (zzaed == null) {
               zzaed = new zzl(var0);
            }
         }
      }

      return zzaed;
   }

   public final zzalk zzjA() {
      if (this.zzaeh == null) {
         synchronized(this) {
            if (this.zzaeh == null) {
               zzalk var2 = new zzalk();
               PackageManager var3 = this.mContext.getPackageManager();
               String var4 = this.mContext.getPackageName();
               var2.setAppId(var4);
               var2.setAppInstallerId(var3.getInstallerPackageName(var4));
               String var5 = var4;
               String var6 = null;

               try {
                  PackageInfo var7;
                  if ((var7 = var3.getPackageInfo(this.mContext.getPackageName(), 0)) != null) {
                     CharSequence var8;
                     if (!TextUtils.isEmpty(var8 = var3.getApplicationLabel(var7.applicationInfo))) {
                        var5 = var8.toString();
                     }

                     var6 = var7.versionName;
                  }
               } catch (NameNotFoundException var10) {
                  String var10002 = String.valueOf(var4);
                  String var10001;
                  if (var10002.length() != 0) {
                     var10001 = "Error retrieving package info: appName set to ".concat(var10002);
                  } else {
                     String var10003 = new String;
                     var10001 = var10003;
                     var10003.<init>("Error retrieving package info: appName set to ");
                  }

                  Log.e("GAv4", var10001);
               }

               var2.setAppName(var5);
               var2.setAppVersion(var6);
               this.zzaeh = var2;
            }
         }
      }

      return this.zzaeh;
   }

   public final zzalp zzjB() {
      DisplayMetrics var1 = this.mContext.getResources().getDisplayMetrics();
      zzalp var2;
      (var2 = new zzalp()).setLanguage(zzaos.zza(Locale.getDefault()));
      int var3 = var1.widthPixels;
      var2.zzNY = var1.widthPixels;
      var3 = var1.heightPixels;
      var2.zzNZ = var1.heightPixels;
      return var2;
   }

   final void zze(zzi var1) {
      if (var1.zzjx()) {
         throw new IllegalStateException("Measurement prototype can't be submitted");
      } else if (var1.zzju()) {
         throw new IllegalStateException("Measurement can only be submitted once");
      } else {
         zzi var2;
         (var2 = var1.zzjp()).zzjv();
         this.zzaeg.execute(new zzm(this, var2));
      }
   }

   public final Context getContext() {
      return this.mContext;
   }

   public static void zzjC() {
      if (!(Thread.currentThread() instanceof zzl.zzc)) {
         throw new IllegalStateException("Call expected from worker thread");
      }
   }

   public final void zza(UncaughtExceptionHandler var1) {
      this.zzaei = var1;
   }

   public final Future zzd(Callable var1) {
      zzbo.zzu(var1);
      if (Thread.currentThread() instanceof zzl.zzc) {
         FutureTask var2;
         (var2 = new FutureTask(var1)).run();
         return var2;
      } else {
         return this.zzaeg.submit(var1);
      }
   }

   public final void zzf(Runnable var1) {
      zzbo.zzu(var1);
      this.zzaeg.submit(var1);
   }

   private static void zzb(zzi var0) {
      zzbo.zzcG("deliver should be called from worker thread");
      zzbo.zzb(var0.zzju(), "Measurement must be submitted");
      List var1;
      if (!(var1 = var0.zzjr()).isEmpty()) {
         HashSet var2 = new HashSet();
         Iterator var3 = var1.iterator();

         while(var3.hasNext()) {
            zzo var4;
            Uri var5 = (var4 = (zzo)var3.next()).zzjl();
            if (!var2.contains(var5)) {
               var2.add(var5);
               var4.zzb(var0);
            }
         }

      }
   }

   // $FF: synthetic method
   static List zza(zzl var0) {
      return var0.zzaee;
   }

   // $FF: synthetic method
   static void zza(zzl var0, zzi var1) {
      zzb(var1);
   }

   // $FF: synthetic method
   static UncaughtExceptionHandler zzb(zzl var0) {
      return var0.zzaei;
   }

   static class zzb implements ThreadFactory {
      private static final AtomicInteger zzaem = new AtomicInteger();

      private zzb() {
      }

      public final Thread newThread(Runnable var1) {
         int var3 = zzaem.incrementAndGet();
         String var2 = (new StringBuilder(23)).append("measurement-").append(var3).toString();
         return new zzl.zzc(var1, var2);
      }

      // $FF: synthetic method
      zzb(zzm var1) {
         this();
      }
   }

   static class zzc extends Thread {
      zzc(Runnable var1, String var2) {
         super(var1, var2);
      }

      public final void run() {
         Process.setThreadPriority(10);
         super.run();
      }
   }

   class zza extends ThreadPoolExecutor {
      public zza() {
         super(1, 1, 1L, TimeUnit.MINUTES, new LinkedBlockingQueue());
         this.setThreadFactory(new zzl.zzb((zzm)null));
         this.allowCoreThreadTimeOut(true);
      }

      protected final RunnableFuture newTaskFor(Runnable var1, Object var2) {
         return new zzn(this, var1, var2);
      }
   }
}
