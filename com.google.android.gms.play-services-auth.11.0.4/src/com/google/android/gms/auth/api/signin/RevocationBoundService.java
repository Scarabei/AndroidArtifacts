package com.google.android.gms.auth.api.signin;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.google.android.gms.auth.api.signin.internal.zzv;

public final class RevocationBoundService extends Service {
   public final IBinder onBind(Intent var1) {
      if ("com.google.android.gms.auth.api.signin.RevocationBoundService.disconnect".equals(var1.getAction())) {
         if (Log.isLoggable("RevocationService", 2)) {
            Log.v("RevocationService", "RevocationBoundService handling disconnect.");
         }

         return new zzv(this);
      } else {
         String var10002 = String.valueOf(var1.getAction());
         String var10001;
         if (var10002.length() != 0) {
            var10001 = "Unknown action sent to RevocationBoundService: ".concat(var10002);
         } else {
            String var10003 = new String;
            var10001 = var10003;
            var10003.<init>("Unknown action sent to RevocationBoundService: ");
         }

         Log.w("RevocationService", var10001);
         return null;
      }
   }
}
