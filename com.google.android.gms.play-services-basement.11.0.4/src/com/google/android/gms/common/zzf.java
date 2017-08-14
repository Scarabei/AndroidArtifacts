package com.google.android.gms.common;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.zzay;
import com.google.android.gms.common.internal.zzaz;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.dynamite.DynamiteModule;

final class zzf {
   private static zzay zzaAd;
   private static final Object zzaAe = new Object();
   private static Context zzaAf;

   static synchronized void zzav(Context var0) {
      if (zzaAf == null) {
         if (var0 != null) {
            zzaAf = var0.getApplicationContext();
            return;
         }
      } else {
         Log.w("GoogleCertificates", "GoogleCertificates has been initialized already");
      }

   }

   private static boolean zzoX() {
      if (zzaAd != null) {
         return true;
      } else {
         zzbo.zzu(zzaAf);
         Object var0 = zzaAe;
         synchronized(zzaAe) {
            if (zzaAd == null) {
               try {
                  zzaAd = zzaz.zzJ(DynamiteModule.zza(zzaAf, DynamiteModule.zzaSP, "com.google.android.gms.googlecertificates").zzcV("com.google.android.gms.common.GoogleCertificatesImpl"));
               } catch (DynamiteModule.zzc var3) {
                  Log.e("GoogleCertificates", "Failed to load com.google.android.gms.googlecertificates", var3);
                  return false;
               }
            }

            return true;
         }
      }
   }

   static boolean zza(String var0, zzg var1) {
      return zza(var0, var1, false);
   }

   static boolean zzb(String var0, zzg var1) {
      return zza(var0, var1, true);
   }

   private static boolean zza(String var0, zzg var1, boolean var2) {
      if (!zzoX()) {
         return false;
      } else {
         zzbo.zzu(zzaAf);

         try {
            zzm var3 = new zzm(var0, var1, var2);
            return zzaAd.zza(var3, com.google.android.gms.dynamic.zzn.zzw(zzaAf.getPackageManager()));
         } catch (RemoteException var4) {
            Log.e("GoogleCertificates", "Failed to get Google certificates from remote", var4);
            return false;
         }
      }
   }
}
