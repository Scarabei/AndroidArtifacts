package com.google.android.gms.security;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.zze;
import com.google.android.gms.common.zzo;
import com.google.android.gms.common.internal.zzbo;
import java.lang.reflect.Method;

public class ProviderInstaller {
   private static final zze zzbCG = zze.zzoW();
   private static final Object zzuF = new Object();
   private static Method zzbCH = null;
   public static final String PROVIDER_NAME = "GmsCore_OpenSSL";

   public static void installIfNeeded(Context var0) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
      zzbo.zzb(var0, "Context must not be null");
      zze.zzas(var0);
      Context var1;
      if ((var1 = zzo.getRemoteContext(var0)) == null) {
         Log.e("ProviderInstaller", "Failed to get remote context");
         throw new GooglePlayServicesNotAvailableException(8);
      } else {
         Object var2 = zzuF;
         synchronized(zzuF) {
            try {
               if (zzbCH == null) {
                  Class var5 = var1.getClassLoader().loadClass("com.google.android.gms.common.security.ProviderInstallerImpl");
                  Class[] var6 = new Class[]{Context.class};
                  zzbCH = var5.getMethod("insertProvider", var6);
               }

               zzbCH.invoke((Object)null, var1);
            } catch (Exception var7) {
               String var10002 = String.valueOf(var7.getMessage());
               String var10001;
               if (var10002.length() != 0) {
                  var10001 = "Failed to install provider: ".concat(var10002);
               } else {
                  String var10003 = new String;
                  var10001 = var10003;
                  var10003.<init>("Failed to install provider: ");
               }

               Log.e("ProviderInstaller", var10001);
               throw new GooglePlayServicesNotAvailableException(8);
            }

         }
      }
   }

   public static void installIfNeededAsync(Context var0, ProviderInstaller.ProviderInstallListener var1) {
      zzbo.zzb(var0, "Context must not be null");
      zzbo.zzb(var1, "Listener must not be null");
      zzbo.zzcz("Must be called on the UI thread");
      (new zza(var0, var1)).execute(new Void[0]);
   }

   // $FF: synthetic method
   static zze zzAo() {
      return zzbCG;
   }

   public interface ProviderInstallListener {
      void onProviderInstalled();

      void onProviderInstallFailed(int var1, Intent var2);
   }
}
