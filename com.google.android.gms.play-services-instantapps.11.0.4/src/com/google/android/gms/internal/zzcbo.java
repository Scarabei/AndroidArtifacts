package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.internal.zzz;

public final class zzcbo extends zzz {
   public zzcbo(Context var1, Looper var2, ConnectionCallbacks var3, OnConnectionFailedListener var4) {
      super(var1, var2, 121, zzq.zzaA(var1), var3, var4);
   }

   @NonNull
   protected final String zzdb() {
      return "com.google.android.gms.instantapps.START";
   }

   @NonNull
   protected final String zzdc() {
      return "com.google.android.gms.instantapps.internal.IInstantAppsService";
   }

   // $FF: synthetic method
   protected final IInterface zzd(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (IInterface)((var3 = var1.queryLocalInterface("com.google.android.gms.instantapps.internal.IInstantAppsService")) instanceof zzcbl ? (zzcbl)var3 : new zzcbm(var1));
      }
   }
}
