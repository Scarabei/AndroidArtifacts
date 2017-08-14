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

public final class zzbvk extends zzbun {
   private static zzf zzajR = new zzf();
   public static final Api API;

   public zzbvk(Context var1, Looper var2, zzq var3, ConnectionCallbacks var4, OnConnectionFailedListener var5) {
      super(var1, var2, 55, var4, var5, var3);
   }

   public final String zzdb() {
      return "com.google.android.gms.fitness.SensorsApi";
   }

   public final String zzdc() {
      return "com.google.android.gms.fitness.internal.IGoogleFitSensorsApi";
   }

   // $FF: synthetic method
   public final IInterface zzd(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (IInterface)((var3 = var1.queryLocalInterface("com.google.android.gms.fitness.internal.IGoogleFitSensorsApi")) instanceof zzbwt ? (zzbwt)var3 : new zzbwu(var1));
      }
   }

   static {
      API = new Api("Fitness.SENSORS_API", new zzbvm(), zzajR);
   }
}
