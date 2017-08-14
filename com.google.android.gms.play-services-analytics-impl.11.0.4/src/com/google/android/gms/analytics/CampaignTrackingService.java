package com.google.android.gms.analytics;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.RequiresPermission;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzamj;
import com.google.android.gms.internal.zzank;
import com.google.android.gms.internal.zzaoc;
import com.google.android.gms.internal.zzaos;
import com.google.android.gms.internal.zzctz;

public class CampaignTrackingService extends Service {
   private Handler mHandler;
   private static Boolean zzadu;

   public static boolean zzad(Context var0) {
      zzbo.zzu(var0);
      if (zzadu != null) {
         return zzadu.booleanValue();
      } else {
         boolean var1;
         zzadu = var1 = zzaos.zzw(var0, "com.google.android.gms.analytics.CampaignTrackingService");
         return var1;
      }
   }

   @RequiresPermission(
      allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"}
   )
   public void onCreate() {
      super.onCreate();
      zzamj.zzaf(this).zzkr().zzbo("CampaignTrackingService is starting up");
   }

   @RequiresPermission(
      allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"}
   )
   public void onDestroy() {
      zzamj.zzaf(this).zzkr().zzbo("CampaignTrackingService is shutting down");
      super.onDestroy();
   }

   @RequiresPermission(
      allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"}
   )
   public int onStartCommand(Intent var1, int var2, int var3) {
      try {
         Object var11 = CampaignTrackingReceiver.zzuF;
         synchronized(CampaignTrackingReceiver.zzuF) {
            zzctz var12 = CampaignTrackingReceiver.zzads;
            if (CampaignTrackingReceiver.zzads != null && var12.isHeld()) {
               var12.release();
            }
         }
      } catch (SecurityException var15) {
         ;
      }

      zzamj var4;
      zzaoc var5 = (var4 = zzamj.zzaf(this)).zzkr();
      String var6 = null;
      if (var1 != null) {
         var6 = var1.getStringExtra("referrer");
      }

      Handler var16 = this.mHandler;
      if (this.mHandler == null) {
         var16 = new Handler(this.getMainLooper());
         this.mHandler = var16;
      }

      if (TextUtils.isEmpty(var6)) {
         if (var1 == null) {
            var5.zzbr("CampaignTrackingService received null intent");
         } else {
            var5.zzbr("No campaign found on com.android.vending.INSTALL_REFERRER \"referrer\" extra");
         }

         var4.zzkt().zzf(new zzc(this, var5, var16, var3));
         return 2;
      } else {
         int var9 = zzank.zzlp();
         String var8;
         if (var6.length() <= var9) {
            var8 = var6;
         } else {
            var5.zzc("Campaign data exceed the maximum supported size and will be clipped. size, limit", var6.length(), var9);
            var8 = var6.substring(0, var9);
         }

         var5.zza("CampaignTrackingService called. startId, campaign", var3, var8);
         var4.zzkv().zza(var8, new zzd(this, var5, var16, var3));
         return 2;
      }
   }

   public IBinder onBind(Intent var1) {
      return null;
   }

   protected final void zza(zzaoc var1, Handler var2, int var3) {
      var2.post(new zze(this, var3, var1));
   }
}
