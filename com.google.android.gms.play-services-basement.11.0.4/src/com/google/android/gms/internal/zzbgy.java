package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import java.lang.reflect.InvocationTargetException;

public final class zzbgy {
   private static Context zzaKh;
   private static Boolean zzaKi;

   public static synchronized boolean zzaN(Context var0) {
      Context var1 = var0.getApplicationContext();
      if (zzaKh != null && zzaKi != null && zzaKh == var1) {
         return zzaKi.booleanValue();
      } else {
         zzaKi = null;
         if (com.google.android.gms.common.util.zzq.isAtLeastO()) {
            try {
               zzaKi = (Boolean)PackageManager.class.getDeclaredMethod("isInstantApp").invoke(var1.getPackageManager());
            } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException var3) {
               zzaKi = false;
            }
         } else {
            try {
               var0.getClassLoader().loadClass("com.google.android.instantapps.supervisor.InstantAppsRuntime");
               zzaKi = true;
            } catch (ClassNotFoundException var2) {
               zzaKi = false;
            }
         }

         zzaKh = var1;
         return zzaKi.booleanValue();
      }
   }
}
