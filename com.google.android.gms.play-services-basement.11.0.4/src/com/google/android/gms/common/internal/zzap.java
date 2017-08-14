package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;

public abstract class zzap extends zzee implements zzao {
   public static zzao zzH(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzao)((var1 = var0.queryLocalInterface("com.google.android.gms.common.internal.ICancelToken")) instanceof zzao ? (zzao)var1 : new zzaq(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 2) {
         this.cancel();
         return true;
      } else {
         return false;
      }
   }
}
