package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzbot extends zzee implements zzbos {
   public static zzbos zzK(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzbos)((var1 = var0.queryLocalInterface("com.google.android.gms.drive.internal.IEventReleaseCallback")) instanceof zzbos ? (zzbos)var1 : new zzbou(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         boolean var5 = zzef.zza(var2);
         this.zzq(var5);
         return true;
      } else {
         return false;
      }
   }
}
