package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.os.SystemClock;

public final class zzk {
   private static IntentFilter zzaJO = new IntentFilter("android.intent.action.BATTERY_CHANGED");
   private static long zzaJP;
   private static float zzaJQ = Float.NaN;

   @TargetApi(20)
   public static int zzaK(Context var0) {
      if (var0 != null && var0.getApplicationContext() != null) {
         Intent var1;
         int var2 = (var1 = var0.getApplicationContext().registerReceiver((BroadcastReceiver)null, zzaJO)) == null ? 0 : var1.getIntExtra("plugged", 0);
         boolean var3 = (7 & var2) != 0;
         PowerManager var4;
         return (var4 = (PowerManager)var0.getSystemService("power")) == null ? -1 : ((zzq.zzsd() ? var4.isInteractive() : var4.isScreenOn()) ? 1 : 0) << 1 | (var3 ? 1 : 0);
      } else {
         return -1;
      }
   }

   public static synchronized float zzaL(Context var0) {
      if (SystemClock.elapsedRealtime() - zzaJP < 60000L && !Float.isNaN(zzaJQ)) {
         return zzaJQ;
      } else {
         Intent var1;
         if ((var1 = var0.getApplicationContext().registerReceiver((BroadcastReceiver)null, zzaJO)) != null) {
            int var2 = var1.getIntExtra("level", -1);
            int var3 = var1.getIntExtra("scale", -1);
            zzaJQ = (float)var2 / (float)var3;
         }

         zzaJP = SystemClock.elapsedRealtime();
         return zzaJQ;
      }
   }
}
