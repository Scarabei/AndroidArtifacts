package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzcay extends zzed implements zzcax {
   zzcay(IBinder var1) {
      super(var1, "com.google.android.gms.gass.internal.IGassService");
   }

   public final zzcav zza(zzcat var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      zzcav var4 = (zzcav)zzef.zza(var3 = this.zza(1, var2), zzcav.CREATOR);
      var3.recycle();
      return var4;
   }
}
