package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzur extends zzee implements zzuq {
   public zzur() {
      this.attachInterface(this, "com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
   }

   public static zzuq zzq(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzuq)((var1 = var0.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator")) instanceof zzuq ? (zzuq)var1 : new zzus(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         String var5;
         switch(var1) {
         case 1:
            var5 = var2.readString();
            zzut var7 = this.zzah(var5);
            var3.writeNoException();
            zzef.zza(var3, var7);
            break;
         case 2:
            var5 = var2.readString();
            boolean var6 = this.zzai(var5);
            var3.writeNoException();
            zzef.zza(var3, var6);
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
