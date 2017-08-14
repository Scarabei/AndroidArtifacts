package com.google.android.gms.internal;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.internal.zzbo;

public final class zzaok {
   private final Handler mHandler;
   private final zzaon zzaiD;
   private final Context mContext;
   private static Boolean zzadu;

   public zzaok(zzaon var1) {
      this.mContext = var1.getContext();
      zzbo.zzu(this.mContext);
      this.zzaiD = var1;
      this.mHandler = new Handler();
   }

   public static boolean zzad(Context var0) {
      zzbo.zzu(var0);
      if (zzadu != null) {
         return zzadu.booleanValue();
      } else {
         boolean var1;
         zzadu = var1 = zzaos.zzw(var0, "com.google.android.gms.analytics.AnalyticsService");
         return var1;
      }
   }

   @RequiresPermission(
      allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"}
   )
   public final void onCreate() {
      zzamj.zzaf(this.mContext).zzkr().zzbo("Local AnalyticsService is starting up");
   }

   @RequiresPermission(
      allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"}
   )
   public final void onDestroy() {
      zzamj.zzaf(this.mContext).zzkr().zzbo("Local AnalyticsService is shutting down");
   }

   @RequiresPermission(
      allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"}
   )
   public final int onStartCommand(Intent var1, int var2, int var3) {
      try {
         Object var7 = zzaoj.zzuF;
         synchronized(zzaoj.zzuF) {
            zzctz var8 = zzaoj.zzads;
            if (zzaoj.zzads != null && var8.isHeld()) {
               var8.release();
            }
         }
      } catch (SecurityException var11) {
         ;
      }

      zzamj var4;
      zzaoc var5 = (var4 = zzamj.zzaf(this.mContext)).zzkr();
      if (var1 == null) {
         var5.zzbr("AnalyticsService started with null intent");
         return 2;
      } else {
         String var6 = var1.getAction();
         var5.zza("Local AnalyticsService called. startId, action", var3, var6);
         if ("com.google.android.gms.analytics.ANALYTICS_DISPATCH".equals(var6)) {
            var4.zzkv().zza((zzanq)(new zzaol(this, var3, var4, var5)));
            return 2;
         } else {
            return 2;
         }
      }
   }

   // $FF: synthetic method
   static zzaon zza(zzaok var0) {
      return var0.zzaiD;
   }

   // $FF: synthetic method
   static Handler zzb(zzaok var0) {
      return var0.mHandler;
   }
}
