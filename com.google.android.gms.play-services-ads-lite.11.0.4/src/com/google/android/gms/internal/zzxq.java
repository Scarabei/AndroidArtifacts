package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzxq extends zzed implements zzxo {
   zzxq(IBinder var1) {
      super(var1, "com.google.android.gms.ads.internal.purchase.client.IPlayStorePurchaseListener");
   }

   public final boolean zzas(String var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeString(var1);
      Parcel var3;
      boolean var4 = zzef.zza(var3 = this.zza(1, var2));
      var3.recycle();
      return var4;
   }

   public final void zza(zzxm var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(2, var2);
   }
}
