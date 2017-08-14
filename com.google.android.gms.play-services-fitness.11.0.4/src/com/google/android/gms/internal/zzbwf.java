package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.GoalsResult;

public abstract class zzbwf extends zzee implements zzbwe {
   public zzbwf() {
      this.attachInterface(this, "com.google.android.gms.fitness.internal.IGoalsReadCallback");
   }

   public static zzbwe zzS(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzbwe)((var1 = var0.queryLocalInterface("com.google.android.gms.fitness.internal.IGoalsReadCallback")) instanceof zzbwe ? (zzbwe)var1 : new zzbwg(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         GoalsResult var5 = (GoalsResult)zzef.zza(var2, GoalsResult.CREATOR);
         this.zza(var5);
         return true;
      } else {
         return false;
      }
   }
}
