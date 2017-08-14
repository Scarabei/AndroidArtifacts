package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzjn extends zzed implements zzjl {
   zzjn(IBinder var1) {
      super(var1, "com.google.android.gms.ads.internal.client.IAdClickListener");
   }

   public final void onAdClicked() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(1, var1);
   }
}
