package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.internal.zzz;

public final class zzaqh extends zzz {
   private final String zzake;

   public zzaqh(Context var1, Looper var2, ConnectionCallbacks var3, OnConnectionFailedListener var4, zzq var5) {
      super(var1, var2, 77, var5, var3, var4);
      this.zzake = var5.zzrq();
   }

   protected final String zzdb() {
      return "com.google.android.gms.appinvite.service.START";
   }

   protected final String zzdc() {
      return "com.google.android.gms.appinvite.internal.IAppInviteService";
   }

   protected final Bundle zzmo() {
      Bundle var2;
      (var2 = new Bundle()).putString("authPackage", this.zzake);
      return var2;
   }

   public final void zza(zzaqj var1) {
      try {
         ((zzaql)this.zzrf()).zza(var1);
      } catch (RemoteException var2) {
         ;
      }
   }

   // $FF: synthetic method
   protected final IInterface zzd(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (IInterface)((var3 = var1.queryLocalInterface("com.google.android.gms.appinvite.internal.IAppInviteService")) instanceof zzaql ? (zzaql)var3 : new zzaqm(var1));
      }
   }
}
