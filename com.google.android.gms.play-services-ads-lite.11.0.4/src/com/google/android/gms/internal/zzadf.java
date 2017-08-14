package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzadf extends zzed implements zzadd {
   zzadf(IBinder var1) {
      super(var1, "com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
   }

   public final void onRewardedVideoAdLoaded() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(1, var1);
   }

   public final void onRewardedVideoAdOpened() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(2, var1);
   }

   public final void onRewardedVideoStarted() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(3, var1);
   }

   public final void onRewardedVideoAdClosed() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(4, var1);
   }

   public final void zza(zzacv var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(5, var2);
   }

   public final void onRewardedVideoAdLeftApplication() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(6, var1);
   }

   public final void onRewardedVideoAdFailedToLoad(int var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeInt(var1);
      this.zzb(7, var2);
   }
}
