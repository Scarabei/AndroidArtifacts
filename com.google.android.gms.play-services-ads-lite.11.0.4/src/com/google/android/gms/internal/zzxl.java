package com.google.android.gms.internal;

import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzxl extends zzed implements zzxj {
   zzxl(IBinder var1) {
      super(var1, "com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManager");
   }

   public final void onCreate() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(1, var1);
   }

   public final void onDestroy() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(2, var1);
   }

   public final void onActivityResult(int var1, int var2, Intent var3) throws RemoteException {
      Parcel var4;
      (var4 = this.zzZ()).writeInt(var1);
      var4.writeInt(var2);
      zzef.zza(var4, var3);
      this.zzb(3, var4);
   }
}
