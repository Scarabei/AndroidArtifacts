package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.internal.zzbo;

public final class zzaoj {
   static Object zzuF = new Object();
   static zzctz zzads;
   private static Boolean zzadt;

   public static boolean zzac(Context var0) {
      zzbo.zzu(var0);
      if (zzadt != null) {
         return zzadt.booleanValue();
      } else {
         boolean var1;
         zzadt = var1 = zzaos.zza(var0, "com.google.android.gms.analytics.AnalyticsReceiver", false);
         return var1;
      }
   }

   @RequiresPermission(
      allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"}
   )
   public static void onReceive(Context var0, Intent var1) {
      zzaoc var2 = zzamj.zzaf(var0).zzkr();
      if (var1 == null) {
         var2.zzbr("AnalyticsReceiver called with null intent");
      } else {
         String var3 = var1.getAction();
         var2.zza("Local AnalyticsReceiver got", var3);
         if ("com.google.android.gms.analytics.ANALYTICS_DISPATCH".equals(var3)) {
            boolean var4 = zzaok.zzad(var0);
            Intent var5;
            (var5 = new Intent("com.google.android.gms.analytics.ANALYTICS_DISPATCH")).setComponent(new ComponentName(var0, "com.google.android.gms.analytics.AnalyticsService"));
            var5.setAction("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
            Object var6 = zzuF;
            synchronized(zzuF) {
               var0.startService(var5);
               if (var4) {
                  try {
                     if (zzads == null) {
                        (zzads = new zzctz(var0, 1, "Analytics WakeLock")).setReferenceCounted(false);
                     }

                     zzads.acquire(1000L);
                  } catch (SecurityException var8) {
                     var2.zzbr("Analytics service at risk of not starting. For more reliable analytics, add the WAKE_LOCK permission to your manifest. See http://goo.gl/8Rd3yj for instructions.");
                  }

               }
            }
         }
      }
   }
}
