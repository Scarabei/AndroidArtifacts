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

public final class zzara extends zzz {
   public zzara(Context var1, Looper var2, zzq var3, ConnectionCallbacks var4, OnConnectionFailedListener var5) {
      super(var1, var2, 74, var3, var4, var5);
   }

   @NonNull
   protected final String zzdb() {
      return "com.google.android.gms.auth.api.accountactivationstate.START";
   }

   @NonNull
   protected final String zzdc() {
      return "com.google.android.gms.auth.api.accountactivationstate.internal.IAccountActivationStateService";
   }

   // $FF: synthetic method
   protected final IInterface zzd(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (IInterface)((var3 = var1.queryLocalInterface("com.google.android.gms.auth.api.accountactivationstate.internal.IAccountActivationStateService")) instanceof zzarb ? (zzarb)var3 : new zzarc(var1));
      }
   }
}
