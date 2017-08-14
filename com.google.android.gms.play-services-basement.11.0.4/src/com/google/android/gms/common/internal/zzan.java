package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;

public final class zzan extends zzed implements zzal {
   zzan(IBinder var1) {
      super(var1, "com.google.android.gms.common.internal.IAccountAccessor");
   }

   public final Account getAccount() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      Account var3 = (Account)zzef.zza(var2 = this.zza(2, var1), Account.CREATOR);
      var2.recycle();
      return var3;
   }
}
