package com.google.android.gms.cast.framework;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzed;

public final class zzr extends zzed implements zzq {
   zzr(IBinder var1) {
      super(var1, "com.google.android.gms.cast.framework.IDiscoveryManager");
   }

   public final IObjectWrapper zznu() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      IObjectWrapper var3 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM((var2 = this.zza(5, var1)).readStrongBinder());
      var2.recycle();
      return var3;
   }
}
