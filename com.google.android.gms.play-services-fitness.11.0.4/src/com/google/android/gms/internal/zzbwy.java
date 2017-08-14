package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.ListSubscriptionsResult;

public abstract class zzbwy extends zzee implements zzbwx {
   public zzbwy() {
      this.attachInterface(this, "com.google.android.gms.fitness.internal.IListSubscriptionsCallback");
   }

   public static zzbwx zzT(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzbwx)((var1 = var0.queryLocalInterface("com.google.android.gms.fitness.internal.IListSubscriptionsCallback")) instanceof zzbwx ? (zzbwx)var1 : new zzbwz(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         ListSubscriptionsResult var5 = (ListSubscriptionsResult)zzef.zza(var2, ListSubscriptionsResult.CREATOR);
         this.zza(var5);
         return true;
      } else {
         return false;
      }
   }
}
