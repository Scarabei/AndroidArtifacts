package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.internal.zzz;

public final class zzckd extends zzz {
   public zzckd(Context var1, Looper var2, ConnectionCallbacks var3, OnConnectionFailedListener var4, zzq var5) {
      super(var1, var2, 69, var5, var3, var4);
   }

   protected final String zzdb() {
      return "com.google.android.gms.nearby.bootstrap.service.NearbyBootstrapService.START";
   }

   protected final String zzdc() {
      return "com.google.android.gms.nearby.bootstrap.internal.INearbyBootstrapService";
   }

   // $FF: synthetic method
   protected final IInterface zzd(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (IInterface)((var3 = var1.queryLocalInterface("com.google.android.gms.nearby.bootstrap.internal.INearbyBootstrapService")) instanceof zzckg ? (zzckg)var3 : new zzckh(var1));
      }
   }
}
