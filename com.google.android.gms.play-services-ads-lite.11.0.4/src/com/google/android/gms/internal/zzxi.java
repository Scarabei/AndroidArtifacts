package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzxi extends zzed implements zzxg {
   zzxi(IBinder var1) {
      super(var1, "com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseListener");
   }

   public final void zza(zzxe var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(1, var2);
   }
}
