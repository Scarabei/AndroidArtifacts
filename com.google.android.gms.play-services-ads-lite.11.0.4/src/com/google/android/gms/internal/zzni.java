package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzni extends zzee implements zznh {
   public zzni() {
      this.attachInterface(this, "com.google.android.gms.ads.internal.customrenderedad.client.IOnCustomRenderedAdLoadedListener");
   }

   public static zznh zzh(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zznh)((var1 = var0.queryLocalInterface("com.google.android.gms.ads.internal.customrenderedad.client.IOnCustomRenderedAdLoadedListener")) instanceof zznh ? (zznh)var1 : new zznj(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         IBinder var6;
         IInterface var7;
         Object var5 = (var6 = var2.readStrongBinder()) == null ? null : ((var7 = var6.queryLocalInterface("com.google.android.gms.ads.internal.customrenderedad.client.ICustomRenderedAd")) instanceof zzne ? (zzne)var7 : new zzng(var6));
         this.zza((zzne)var5);
         var3.writeNoException();
         return true;
      } else {
         return false;
      }
   }
}
