package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzpr extends zzee implements zzpq {
   public zzpr() {
      this.attachInterface(this, "com.google.android.gms.ads.internal.formats.client.IOnContentAdLoadedListener");
   }

   public static zzpq zzm(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzpq)((var1 = var0.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IOnContentAdLoadedListener")) instanceof zzpq ? (zzpq)var1 : new zzps(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         IBinder var6;
         IInterface var7;
         Object var5 = (var6 = var2.readStrongBinder()) == null ? null : ((var7 = var6.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd")) instanceof zzpf ? (zzpf)var7 : new zzph(var6));
         this.zza((zzpf)var5);
         var3.writeNoException();
         return true;
      } else {
         return false;
      }
   }
}
