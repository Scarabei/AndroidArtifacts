package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public class zzee extends Binder implements IInterface {
   public IBinder asBinder() {
      return this;
   }

   protected final boolean zza(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (var1 > 16777215) {
         return super.onTransact(var1, var2, var3, var4);
      } else {
         var2.enforceInterface(this.getInterfaceDescriptor());
         return false;
      }
   }
}
