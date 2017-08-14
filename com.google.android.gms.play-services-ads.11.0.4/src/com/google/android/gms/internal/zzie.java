package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzie extends zzed implements zzid {
   zzie(IBinder var1) {
      super(var1, "com.google.android.gms.ads.internal.cache.ICacheService");
   }

   public final zzhx zza(zzia var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      zzhx var4 = (zzhx)zzef.zza(var3 = this.zza(1, var2), zzhx.CREATOR);
      var3.recycle();
      return var4;
   }
}
