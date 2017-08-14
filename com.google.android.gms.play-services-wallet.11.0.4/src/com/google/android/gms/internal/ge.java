package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class ge extends zzee implements gd {
   public ge() {
      this.attachInterface(this, "com.google.android.gms.wallet.fragment.internal.IWalletFragmentStateListener");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 2) {
         int var5 = var2.readInt();
         int var6 = var2.readInt();
         Bundle var7 = (Bundle)zzef.zza(var2, Bundle.CREATOR);
         this.zza(var5, var6, var7);
         var3.writeNoException();
         return true;
      } else {
         return false;
      }
   }
}
