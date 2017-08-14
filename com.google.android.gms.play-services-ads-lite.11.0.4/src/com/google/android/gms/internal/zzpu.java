package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzpu extends zzee implements zzpt {
   public zzpu() {
      this.attachInterface(this, "com.google.android.gms.ads.internal.formats.client.IOnCustomClickListener");
   }

   public static zzpt zzn(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzpt)((var1 = var0.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IOnCustomClickListener")) instanceof zzpt ? (zzpt)var1 : new zzpv(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         IBinder var7;
         IInterface var8;
         Object var5 = (var7 = var2.readStrongBinder()) == null ? null : ((var8 = var7.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd")) instanceof zzpj ? (zzpj)var8 : new zzpl(var7));
         String var6 = var2.readString();
         this.zzb((zzpj)var5, var6);
         var3.writeNoException();
         return true;
      } else {
         return false;
      }
   }
}
