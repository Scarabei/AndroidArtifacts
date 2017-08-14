package com.google.android.gms.maps;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.maps.internal.zzbx;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public final class MapsInitializer {
   private static boolean initialized = false;

   public static synchronized int initialize(Context var0) {
      zzbo.zzb(var0, "Context is null");
      if (initialized) {
         return 0;
      } else {
         com.google.android.gms.maps.internal.zze var1;
         try {
            var1 = zzbx.zzbh(var0);
         } catch (GooglePlayServicesNotAvailableException var5) {
            return var5.errorCode;
         }

         com.google.android.gms.maps.internal.zze var2 = var1;

         try {
            CameraUpdateFactory.zza(var2.zzwh());
            BitmapDescriptorFactory.zza(var2.zzwi());
         } catch (RemoteException var4) {
            throw new RuntimeRemoteException(var4);
         }

         initialized = true;
         return 0;
      }
   }
}
