package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.internal.zzz;

public final class zzbab extends zzz {
   public zzbab(Context var1, Looper var2, zzq var3, ConnectionCallbacks var4, OnConnectionFailedListener var5) {
      super(var1, var2, 40, var3, var4, var5);
   }

   protected final String zzdb() {
      return "com.google.android.gms.clearcut.service.START";
   }

   protected final String zzdc() {
      return "com.google.android.gms.clearcut.internal.IClearcutLoggerService";
   }

   // $FF: synthetic method
   protected final IInterface zzd(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (IInterface)((var3 = var1.queryLocalInterface("com.google.android.gms.clearcut.internal.IClearcutLoggerService")) instanceof zzbaf ? (zzbaf)var3 : new zzbag(var1));
      }
   }
}
