package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.internal.zzz;

public final class zzash extends zzz {
   public zzash(Context var1, Looper var2, zzq var3, ConnectionCallbacks var4, OnConnectionFailedListener var5) {
      super(var1, var2, 126, var3, var4, var5);
   }

   protected final String zzdb() {
      return "com.google.android.gms.auth.api.phone.service.SmsRetrieverApiService.START";
   }

   protected final String zzdc() {
      return "com.google.android.gms.auth.api.phone.internal.ISmsRetrieverApiService";
   }

   // $FF: synthetic method
   protected final IInterface zzd(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (IInterface)((var3 = var1.queryLocalInterface("com.google.android.gms.auth.api.phone.internal.ISmsRetrieverApiService")) instanceof zzasd ? (zzasd)var3 : new zzase(var1));
      }
   }
}
