package com.google.android.gms.maps.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public class zzbx {
   private static Context zzbmZ;
   private static zze zzbna;

   public static zze zzbh(Context var0) throws GooglePlayServicesNotAvailableException {
      com.google.android.gms.common.internal.zzbo.zzu(var0);
      if (zzbna != null) {
         return zzbna;
      } else {
         int var3;
         switch(var3 = GooglePlayServicesUtil.isGooglePlayServicesAvailable(var0)) {
         case 0:
            Log.i(zzbx.class.getSimpleName(), "Making Creator dynamically");
            IBinder var4;
            IInterface var5;
            zzbna = (zze)((var4 = (IBinder)zza(zzbi(var0).getClassLoader(), "com.google.android.gms.maps.internal.CreatorImpl")) == null ? null : ((var5 = var4.queryLocalInterface("com.google.android.gms.maps.internal.ICreator")) instanceof zze ? (zze)var5 : new zzf(var4)));

            try {
               zzbna.zzi(com.google.android.gms.dynamic.zzn.zzw(zzbi(var0).getResources()), GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE);
            } catch (RemoteException var6) {
               throw new RuntimeRemoteException(var6);
            }

            return zzbna;
         default:
            throw new GooglePlayServicesNotAvailableException(var3);
         }
      }
   }

   private static Context zzbi(Context var0) {
      return zzbmZ != null ? zzbmZ : (zzbmZ = GooglePlayServicesUtil.getRemoteContext(var0));
   }

   private static Object zza(ClassLoader var0, String var1) {
      try {
         return zzd(((ClassLoader)com.google.android.gms.common.internal.zzbo.zzu(var0)).loadClass(var1));
      } catch (ClassNotFoundException var2) {
         IllegalStateException var10000 = new IllegalStateException;
         String var10003 = String.valueOf(var1);
         String var10002;
         if (var10003.length() != 0) {
            var10002 = "Unable to find dynamic class ".concat(var10003);
         } else {
            String var10004 = new String;
            var10002 = var10004;
            var10004.<init>("Unable to find dynamic class ");
         }

         var10000.<init>(var10002);
         throw var10000;
      }
   }

   private static Object zzd(Class var0) {
      IllegalStateException var10000;
      String var10002;
      String var10003;
      String var10004;
      try {
         return var0.newInstance();
      } catch (InstantiationException var1) {
         var10000 = new IllegalStateException;
         var10003 = String.valueOf(var0.getName());
         if (var10003.length() != 0) {
            var10002 = "Unable to instantiate the dynamic class ".concat(var10003);
         } else {
            var10004 = new String;
            var10002 = var10004;
            var10004.<init>("Unable to instantiate the dynamic class ");
         }

         var10000.<init>(var10002);
         throw var10000;
      } catch (IllegalAccessException var2) {
         var10000 = new IllegalStateException;
         var10003 = String.valueOf(var0.getName());
         if (var10003.length() != 0) {
            var10002 = "Unable to call the default constructor of ".concat(var10003);
         } else {
            var10004 = new String;
            var10002 = var10004;
            var10004.<init>("Unable to call the default constructor of ");
         }

         var10000.<init>(var10002);
         throw var10000;
      }
   }
}
