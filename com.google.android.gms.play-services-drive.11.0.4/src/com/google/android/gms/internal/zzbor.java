package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzbor extends zzee implements zzboq {
   public zzbor() {
      this.attachInterface(this, "com.google.android.gms.drive.internal.IEventCallback");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         zzbph var5 = (zzbph)zzef.zza(var2, zzbph.CREATOR);
         this.zzc(var5);
         var3.writeNoException();
         return true;
      } else {
         return false;
      }
   }
}
