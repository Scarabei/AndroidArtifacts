package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.internal.zzz;

public final class zzbfw extends zzz {
   public zzbfw(Context var1, Looper var2, zzq var3, GoogleApiClient.ConnectionCallbacks var4, GoogleApiClient.OnConnectionFailedListener var5) {
      super(var1, var2, 39, var3, var4, var5);
   }

   public final String zzdb() {
      return "com.google.android.gms.common.service.START";
   }

   protected final String zzdc() {
      return "com.google.android.gms.common.internal.service.ICommonService";
   }

   // $FF: synthetic method
   protected final IInterface zzd(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (IInterface)((var3 = var1.queryLocalInterface("com.google.android.gms.common.internal.service.ICommonService")) instanceof zzbfz ? (zzbfz)var3 : new zzbga(var1));
      }
   }
}
