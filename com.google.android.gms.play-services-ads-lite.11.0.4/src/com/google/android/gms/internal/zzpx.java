package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzpx extends zzee implements zzpw {
   public zzpx() {
      this.attachInterface(this, "com.google.android.gms.ads.internal.formats.client.IOnCustomTemplateAdLoadedListener");
   }

   public static zzpw zzo(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzpw)((var1 = var0.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IOnCustomTemplateAdLoadedListener")) instanceof zzpw ? (zzpw)var1 : new zzpy(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         IBinder var6;
         IInterface var7;
         Object var5 = (var6 = var2.readStrongBinder()) == null ? null : ((var7 = var6.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd")) instanceof zzpj ? (zzpj)var7 : new zzpl(var6));
         this.zza((zzpj)var5);
         var3.writeNoException();
         return true;
      } else {
         return false;
      }
   }
}
