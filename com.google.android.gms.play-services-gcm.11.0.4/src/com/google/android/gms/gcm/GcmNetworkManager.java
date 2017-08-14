package com.google.android.gms.gcm;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.zzbo;
import java.util.Iterator;
import java.util.List;

public class GcmNetworkManager {
   public static final int RESULT_SUCCESS = 0;
   public static final int RESULT_RESCHEDULE = 1;
   public static final int RESULT_FAILURE = 2;
   private static GcmNetworkManager zzbfv;
   private Context mContext;
   private final PendingIntent mPendingIntent;

   public static GcmNetworkManager getInstance(Context var0) {
      Class var1 = GcmNetworkManager.class;
      synchronized(GcmNetworkManager.class) {
         if (zzbfv == null) {
            zzbfv = new GcmNetworkManager(var0.getApplicationContext());
         }

         return zzbfv;
      }
   }

   private GcmNetworkManager(Context var1) {
      this.mContext = var1;
      this.mPendingIntent = PendingIntent.getBroadcast(this.mContext, 0, (new Intent()).setPackage("com.google.example.invalidpackage"), 0);
   }

   public void schedule(Task var1) {
      this.zzdo(var1.getServiceName());
      Intent var2;
      if ((var2 = this.zzvB()) != null) {
         Bundle var3;
         (var3 = var2.getExtras()).putString("scheduler_action", "SCHEDULE_TASK");
         var1.toBundle(var3);
         var2.putExtras(var3);
         this.mContext.sendBroadcast(var2);
      }
   }

   public void cancelTask(String var1, Class var2) {
      ComponentName var8 = new ComponentName(this.mContext, var2);
      zzdn(var1);
      this.zzdo(var8.getClassName());
      Intent var9;
      if ((var9 = this.zzvB()) != null) {
         var9.putExtra("scheduler_action", "CANCEL_TASK");
         var9.putExtra("tag", var1);
         var9.putExtra("component", var8);
         this.mContext.sendBroadcast(var9);
      }

   }

   public void cancelAllTasks(Class var1) {
      ComponentName var5 = new ComponentName(this.mContext, var1);
      this.zzdo(var5.getClassName());
      Intent var6;
      if ((var6 = this.zzvB()) != null) {
         var6.putExtra("scheduler_action", "CANCEL_ALL");
         var6.putExtra("component", var5);
         this.mContext.sendBroadcast(var6);
      }

   }

   private final Intent zzvB() {
      String var1 = com.google.android.gms.iid.zze.zzbd(this.mContext);
      int var2 = -1;
      if (var1 != null) {
         var2 = GoogleCloudMessaging.zzaZ(this.mContext);
      }

      if (var1 != null && var2 >= GoogleCloudMessaging.zzbfL) {
         Intent var3;
         (var3 = new Intent("com.google.android.gms.gcm.ACTION_SCHEDULE")).setPackage(var1);
         var3.putExtra("app", this.mPendingIntent);
         var3.putExtra("source", 4);
         var3.putExtra("source_version", 11020000);
         return var3;
      } else {
         Log.e("GcmNetworkManager", (new StringBuilder(91)).append("Google Play Services is not available, dropping GcmNetworkManager request. code=").append(var2).toString());
         return null;
      }
   }

   static void zzdn(String var0) {
      if (TextUtils.isEmpty(var0)) {
         throw new IllegalArgumentException("Must provide a valid tag.");
      } else if (100 < var0.length()) {
         throw new IllegalArgumentException("Tag is larger than max permissible tag length (100)");
      }
   }

   private final void zzdo(String var1) {
      zzbo.zzb(var1, "GcmTaskService must not be null.");
      Intent var2;
      (var2 = new Intent("com.google.android.gms.gcm.ACTION_TASK_READY")).setPackage(this.mContext.getPackageName());
      List var3;
      zzbo.zzb((var3 = this.mContext.getPackageManager().queryIntentServices(var2, 0)) != null && var3.size() != 0, "There is no GcmTaskService component registered within this package. Have you extended GcmTaskService correctly?");
      boolean var4 = false;
      Iterator var5 = var3.iterator();

      while(var5.hasNext()) {
         if (((ResolveInfo)var5.next()).serviceInfo.name.equals(var1)) {
            var4 = true;
            break;
         }
      }

      zzbo.zzb(var4, (new StringBuilder(119 + String.valueOf(var1).length())).append("The GcmTaskService class you provided ").append(var1).append(" does not seem to support receiving com.google.android.gms.gcm.ACTION_TASK_READY.").toString());
   }
}
