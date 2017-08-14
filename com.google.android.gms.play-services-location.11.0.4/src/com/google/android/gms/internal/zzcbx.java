package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.internal.zzz;

public class zzcbx extends zzz {
   private final String zzbiA;
   protected final zzcdt zzbiB = new zzcby(this);

   public zzcbx(Context var1, Looper var2, ConnectionCallbacks var3, OnConnectionFailedListener var4, String var5, zzq var6) {
      super(var1, var2, 23, var6, var3, var4);
      this.zzbiA = var5;
   }

   protected final String zzdb() {
      return "com.google.android.location.internal.GoogleLocationManagerService.START";
   }

   protected final String zzdc() {
      return "com.google.android.gms.location.internal.IGoogleLocationManagerService";
   }

   protected final Bundle zzmo() {
      Bundle var1;
      (var1 = new Bundle()).putString("client_name", this.zzbiA);
      return var1;
   }

   // $FF: synthetic method
   protected final IInterface zzd(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (IInterface)((var3 = var1.queryLocalInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService")) instanceof zzccz ? (zzccz)var3 : new zzcda(var1));
      }
   }

   // $FF: synthetic method
   static void zza(zzcbx var0) {
      var0.zzre();
   }
}
