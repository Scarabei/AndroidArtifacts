package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

final class zzax implements zzaw {
   private final IBinder zzrD;

   zzax(IBinder var1) {
      this.zzrD = var1;
   }

   public final IBinder asBinder() {
      return this.zzrD;
   }

   public final void zza(zzau var1, zzx var2) throws RemoteException {
      Parcel var3 = Parcel.obtain();
      Parcel var4 = Parcel.obtain();

      try {
         var3.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
         var3.writeStrongBinder(var1.asBinder());
         var3.writeInt(1);
         var2.writeToParcel(var3, 0);
         this.zzrD.transact(46, var3, var4, 0);
         var4.readException();
      } finally {
         var4.recycle();
         var3.recycle();
      }

   }
}
