package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzed;

public final class zzaq extends zzed implements zzao {
   zzaq(IBinder var1) {
      super(var1, "com.google.android.gms.common.internal.ICancelToken");
   }

   public final void cancel() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzc(2, var1);
   }
}
