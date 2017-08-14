package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.SessionReadResult;

public abstract class zzbxb extends zzee implements zzbxa {
   public zzbxb() {
      this.attachInterface(this, "com.google.android.gms.fitness.internal.ISessionReadCallback");
   }

   public static zzbxa zzU(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzbxa)((var1 = var0.queryLocalInterface("com.google.android.gms.fitness.internal.ISessionReadCallback")) instanceof zzbxa ? (zzbxa)var1 : new zzbxc(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         SessionReadResult var5 = (SessionReadResult)zzef.zza(var2, SessionReadResult.CREATOR);
         this.zza(var5);
         return true;
      } else {
         return false;
      }
   }
}
