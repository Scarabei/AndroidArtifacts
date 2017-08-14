package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.DailyTotalResult;

public abstract class zzbvt extends zzee implements zzbvs {
   public zzbvt() {
      this.attachInterface(this, "com.google.android.gms.fitness.internal.IDailyTotalCallback");
   }

   public static zzbvs zzO(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzbvs)((var1 = var0.queryLocalInterface("com.google.android.gms.fitness.internal.IDailyTotalCallback")) instanceof zzbvs ? (zzbvs)var1 : new zzbvu(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         DailyTotalResult var5 = (DailyTotalResult)zzef.zza(var2, DailyTotalResult.CREATOR);
         this.zza(var5);
         return true;
      } else {
         return false;
      }
   }
}
