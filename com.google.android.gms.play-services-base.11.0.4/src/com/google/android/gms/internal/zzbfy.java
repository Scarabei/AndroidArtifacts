package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzbfy extends zzee implements zzbfx {
   public zzbfy() {
      this.attachInterface(this, "com.google.android.gms.common.internal.service.ICommonCallbacks");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         int var5 = var2.readInt();
         this.zzaC(var5);
         var3.writeNoException();
         return true;
      } else {
         return false;
      }
   }
}
