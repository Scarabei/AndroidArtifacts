package com.google.android.gms.iid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.google.firebase.iid.zzb;

public class InstanceIDListenerService extends zzb {
   public void handleIntent(Intent var1) {
      String var2 = var1.getAction();
      if ("com.google.android.gms.iid.InstanceID".equals(var2)) {
         Bundle var3 = null;
         String var4;
         if ((var4 = var1.getStringExtra("subtype")) != null) {
            (var3 = new Bundle()).putString("subtype", var4);
         }

         InstanceID var5 = InstanceID.zza(this, var3);
         String var6 = var1.getStringExtra("CMD");
         if (Log.isLoggable("InstanceID", 3)) {
            Log.d("InstanceID", (new StringBuilder(34 + String.valueOf(var4).length() + String.valueOf(var6).length())).append("Service command. subtype:").append(var4).append(" command:").append(var6).toString());
         }

         if ("gcm.googleapis.com/refresh".equals(var1.getStringExtra("from"))) {
            InstanceID.zzvM().zzdr(var4);
            this.onTokenRefresh();
         } else if ("RST".equals(var6)) {
            var5.zzvL();
            this.onTokenRefresh();
         } else {
            if ("RST_FULL".equals(var6)) {
               if (!InstanceID.zzvM().isEmpty()) {
                  InstanceID.zzvM().zzvP();
                  this.onTokenRefresh();
                  return;
               }
            } else {
               if ("SYNC".equals(var6)) {
                  InstanceID.zzvM().zzdr(var4);
                  this.onTokenRefresh();
                  return;
               }

               "PING".equals(var6);
            }

         }
      }
   }

   static void zza(Context var0, zzh var1) {
      var1.zzvP();
      Intent var2;
      (var2 = new Intent("com.google.android.gms.iid.InstanceID")).putExtra("CMD", "RST");
      var2.setClassName(var0, "com.google.android.gms.gcm.GcmReceiver");
      var0.sendBroadcast(var2);
   }

   public void onTokenRefresh() {
   }
}
