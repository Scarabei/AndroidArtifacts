package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public abstract class zzam extends zzee implements zzal {
   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 2) {
         Account var5 = this.getAccount();
         var3.writeNoException();
         zzef.zzb(var3, var5);
         return true;
      } else {
         return false;
      }
   }
}
