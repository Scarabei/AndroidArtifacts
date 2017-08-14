package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzvb extends zzed implements zzuz {
   zzvb(IBinder var1) {
      super(var1, "com.google.android.gms.ads.internal.mediation.client.IMediationResponseMetadata");
   }

   public final int zzfo() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      int var3 = (var2 = this.zza(1, var1)).readInt();
      var2.recycle();
      return var3;
   }
}
