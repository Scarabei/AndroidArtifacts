package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzus extends zzed implements zzuq {
   zzus(IBinder var1) {
      super(var1, "com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
   }

   public final zzut zzah(String var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeString(var1);
      Parcel var3;
      IBinder var5;
      IInterface var6;
      Object var4 = (var5 = (var3 = this.zza(1, var2)).readStrongBinder()) == null ? null : ((var6 = var5.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter")) instanceof zzut ? (zzut)var6 : new zzuv(var5));
      var3.recycle();
      return (zzut)var4;
   }

   public final boolean zzai(String var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeString(var1);
      Parcel var3;
      boolean var4 = zzef.zza(var3 = this.zza(2, var2));
      var3.recycle();
      return var4;
   }
}
