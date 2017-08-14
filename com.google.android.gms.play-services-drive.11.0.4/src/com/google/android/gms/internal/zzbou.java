package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzbou extends zzed implements zzbos {
   zzbou(IBinder var1) {
      super(var1, "com.google.android.gms.drive.internal.IEventReleaseCallback");
   }

   public final void zzq(boolean var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzc(1, var2);
   }
}
