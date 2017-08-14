package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzcbh extends zzee implements zzcbg {
   public zzcbh() {
      this.attachInterface(this, "com.google.android.gms.identity.intents.internal.IAddressCallbacks");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 2) {
         int var5 = var2.readInt();
         Bundle var6 = (Bundle)zzef.zza(var2, Bundle.CREATOR);
         this.zze(var5, var6);
         var3.writeNoException();
         return true;
      } else {
         return false;
      }
   }
}
