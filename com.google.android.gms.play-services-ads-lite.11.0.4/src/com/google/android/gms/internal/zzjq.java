package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzjq extends zzed implements zzjo {
   zzjq(IBinder var1) {
      super(var1, "com.google.android.gms.ads.internal.client.IAdListener");
   }

   public final void onAdClosed() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(1, var1);
   }

   public final void onAdFailedToLoad(int var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeInt(var1);
      this.zzb(2, var2);
   }

   public final void onAdLeftApplication() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(3, var1);
   }

   public final void onAdLoaded() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(4, var1);
   }

   public final void onAdOpened() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(5, var1);
   }

   public final void onAdClicked() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(6, var1);
   }

   public final void onAdImpression() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(7, var1);
   }
}
