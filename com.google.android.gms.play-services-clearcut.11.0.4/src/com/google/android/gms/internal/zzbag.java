package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzbag extends zzed implements zzbaf {
   zzbag(IBinder var1) {
      super(var1, "com.google.android.gms.clearcut.internal.IClearcutLoggerService");
   }

   public final void zza(zzbad var1, zzazu var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      this.zzc(1, var3);
   }
}
