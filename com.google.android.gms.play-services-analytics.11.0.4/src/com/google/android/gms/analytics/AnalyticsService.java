package com.google.android.gms.analytics;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.internal.zzaok;
import com.google.android.gms.internal.zzaon;

public final class AnalyticsService extends Service implements zzaon {
   private zzaok zzadm;

   private final zzaok zzjk() {
      if (this.zzadm == null) {
         this.zzadm = new zzaok(this);
      }

      return this.zzadm;
   }

   @RequiresPermission(
      allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"}
   )
   public final void onCreate() {
      super.onCreate();
      this.zzjk().onCreate();
   }

   @RequiresPermission(
      allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"}
   )
   public final void onDestroy() {
      this.zzjk().onDestroy();
      super.onDestroy();
   }

   @RequiresPermission(
      allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"}
   )
   public final int onStartCommand(Intent var1, int var2, int var3) {
      return this.zzjk().onStartCommand(var1, var2, var3);
   }

   public final IBinder onBind(Intent var1) {
      this.zzjk();
      return null;
   }

   public final boolean callServiceStopSelfResult(int var1) {
      return this.stopSelfResult(var1);
   }

   public final Context getContext() {
      return this;
   }
}
