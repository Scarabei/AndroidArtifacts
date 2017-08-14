package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzed;

public final class zzat extends zzed implements zzar {
   zzat(IBinder var1) {
      super(var1, "com.google.android.gms.common.internal.ICertData");
   }

   public final IObjectWrapper zzoY() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      IObjectWrapper var3 = IObjectWrapper.zza.zzM((var2 = this.zza(1, var1)).readStrongBinder());
      var2.recycle();
      return var3;
   }

   public final int zzoZ() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      int var3 = (var2 = this.zza(2, var1)).readInt();
      var2.recycle();
      return var3;
   }
}
