package com.google.android.gms.gcm;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

final class zzc extends Handler {
   // $FF: synthetic field
   private GoogleCloudMessaging zzbfU;

   zzc(GoogleCloudMessaging var1, Looper var2) {
      this.zzbfU = var1;
      super(var2);
   }

   public final void handleMessage(Message var1) {
      if (var1 == null || !(var1.obj instanceof Intent)) {
         Log.w("GCM", "Dropping invalid message");
      }

      Intent var2 = (Intent)var1.obj;
      if ("com.google.android.c2dm.intent.REGISTRATION".equals(var2.getAction())) {
         GoogleCloudMessaging.zza(this.zzbfU).add(var2);
      } else {
         if (!GoogleCloudMessaging.zza(this.zzbfU, var2)) {
            var2.setPackage(GoogleCloudMessaging.zzb(this.zzbfU).getPackageName());
            GoogleCloudMessaging.zzb(this.zzbfU).sendBroadcast(var2);
         }

      }
   }
}
