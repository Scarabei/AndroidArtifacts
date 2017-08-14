package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.SessionStopResult;

public abstract class zzbxe extends zzee implements zzbxd {
   public zzbxe() {
      this.attachInterface(this, "com.google.android.gms.fitness.internal.ISessionStopCallback");
   }

   public static zzbxd zzV(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzbxd)((var1 = var0.queryLocalInterface("com.google.android.gms.fitness.internal.ISessionStopCallback")) instanceof zzbxd ? (zzbxd)var1 : new zzbxf(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         SessionStopResult var5 = (SessionStopResult)zzef.zza(var2, SessionStopResult.CREATOR);
         this.zza(var5);
         return true;
      } else {
         return false;
      }
   }
}
