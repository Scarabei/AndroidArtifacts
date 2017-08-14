package com.google.android.gms.gcm;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzed;

public final class zze extends zzed implements zzd {
   zze(IBinder var1) {
      super(var1, "com.google.android.gms.gcm.INetworkTaskCallback");
   }

   public final void zzbh(int var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeInt(var1);
      this.zzb(2, var2);
   }
}
