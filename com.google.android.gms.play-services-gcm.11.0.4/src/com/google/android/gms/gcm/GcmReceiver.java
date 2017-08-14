package com.google.android.gms.gcm;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Build.VERSION;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.common.util.zzq;

public class GcmReceiver extends WakefulBroadcastReceiver {
   private static String zzbfA = "gcm.googleapis.com/refresh";
   private static boolean zzbfB = false;
   private com.google.firebase.iid.zzh zzbfC;
   private com.google.firebase.iid.zzh zzbfD;

   public void onReceive(Context var1, Intent var2) {
      if (Log.isLoggable("GcmReceiver", 3)) {
         Log.d("GcmReceiver", "received new intent");
      }

      var2.setComponent((ComponentName)null);
      var2.setPackage(var1.getPackageName());
      if (VERSION.SDK_INT <= 18) {
         var2.removeCategory(var1.getPackageName());
      }

      String var3 = var2.getStringExtra("from");
      if ("google.com/iid".equals(var3) || zzbfA.equals(var3)) {
         var2.setAction("com.google.android.gms.iid.InstanceID");
      }

      String var4;
      if ((var4 = var2.getStringExtra("gcm.rawData64")) != null) {
         var2.putExtra("rawData", Base64.decode(var4, 0));
         var2.removeExtra("gcm.rawData64");
      }

      if (zzq.isAtLeastO()) {
         if (this.isOrderedBroadcast()) {
            this.setResultCode(-1);
         }

         this.zzH(var1, var2.getAction()).zza(var2, this.goAsync());
      } else {
         if ("com.google.android.c2dm.intent.RECEIVE".equals(var2.getAction())) {
            this.doStartService(var1, var2);
         } else {
            this.doStartService(var1, var2);
         }

         if (this.isOrderedBroadcast() && this.getResultCode() == 0) {
            this.setResultCode(-1);
         }

      }
   }

   private final synchronized com.google.firebase.iid.zzh zzH(Context var1, String var2) {
      if ("com.google.android.c2dm.intent.RECEIVE".equals(var2)) {
         if (this.zzbfD == null) {
            this.zzbfD = new com.google.firebase.iid.zzh(var1, var2);
         }

         return this.zzbfD;
      } else {
         if (this.zzbfC == null) {
            this.zzbfC = new com.google.firebase.iid.zzh(var1, var2);
         }

         return this.zzbfC;
      }
   }

   private final void doStartService(Context var1, Intent var2) {
      if (this.isOrderedBroadcast()) {
         this.setResultCode(500);
      }

      ResolveInfo var6;
      if ((var6 = var1.getPackageManager().resolveService(var2, 0)) != null && var6.serviceInfo != null) {
         ServiceInfo var7 = var6.serviceInfo;
         String var8;
         if (var1.getPackageName().equals(var7.packageName) && var7.name != null) {
            var8 = var7.name;
            String var10000;
            String var10001;
            String var10002;
            if (var7.name.startsWith(".")) {
               var10000 = String.valueOf(var1.getPackageName());
               var10001 = String.valueOf(var8);
               if (var10001.length() != 0) {
                  var10000 = var10000.concat(var10001);
               } else {
                  var10002 = new String;
                  var10001 = var10000;
                  var10000 = var10002;
                  var10002.<init>(var10001);
               }
            } else {
               var10000 = var8;
            }

            var8 = var10000;
            if (Log.isLoggable("GcmReceiver", 3)) {
               var10002 = String.valueOf(var8);
               if (var10002.length() != 0) {
                  var10001 = "Restricting intent to a specific service: ".concat(var10002);
               } else {
                  String var10003 = new String;
                  var10001 = var10003;
                  var10003.<init>("Restricting intent to a specific service: ");
               }

               Log.d("GcmReceiver", var10001);
            }

            var2.setClassName(var1.getPackageName(), var8);
         } else {
            var8 = String.valueOf(var7.packageName);
            String var9 = String.valueOf(var7.name);
            Log.e("GcmReceiver", (new StringBuilder(94 + String.valueOf(var8).length() + String.valueOf(var9).length())).append("Error resolving target intent service, skipping classname enforcement. Resolved service was: ").append(var8).append("/").append(var9).toString());
         }
      } else {
         Log.e("GcmReceiver", "Failed to resolve target intent service, skipping classname enforcement");
      }

      try {
         ComponentName var3;
         if (var1.checkCallingOrSelfPermission("android.permission.WAKE_LOCK") == 0) {
            var3 = startWakefulService(var1, var2);
         } else {
            var3 = var1.startService(var2);
            Log.d("GcmReceiver", "Missing wake lock permission, service start may be delayed");
         }

         if (var3 == null) {
            Log.e("GcmReceiver", "Error while delivering the message: ServiceIntent not found.");
            if (this.isOrderedBroadcast()) {
               this.setResultCode(404);
               return;
            }
         } else if (this.isOrderedBroadcast()) {
            this.setResultCode(-1);
         }

      } catch (SecurityException var10) {
         Log.e("GcmReceiver", "Error while delivering the message to the serviceIntent", var10);
         if (this.isOrderedBroadcast()) {
            this.setResultCode(401);
         }

      }
   }
}
