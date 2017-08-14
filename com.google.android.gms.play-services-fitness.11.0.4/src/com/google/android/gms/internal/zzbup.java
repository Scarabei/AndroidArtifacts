package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzq;

public final class zzbup extends zzbun {
   private static zzf zzajR = new zzf();
   public static final Api API;

   public zzbup(Context var1, Looper var2, zzq var3, ConnectionCallbacks var4, OnConnectionFailedListener var5) {
      super(var1, var2, 59, var4, var5, var3);
   }

   public final String zzdb() {
      return "com.google.android.gms.fitness.BleApi";
   }

   public final String zzdc() {
      return "com.google.android.gms.fitness.internal.IGoogleFitBleApi";
   }

   // $FF: synthetic method
   public final IInterface zzd(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (IInterface)((var3 = var1.queryLocalInterface("com.google.android.gms.fitness.internal.IGoogleFitBleApi")) instanceof zzbwh ? (zzbwh)var3 : new zzbwi(var1));
      }
   }

   static {
      API = new Api("Fitness.BLE_API", new zzbur(), zzajR);
   }
}
