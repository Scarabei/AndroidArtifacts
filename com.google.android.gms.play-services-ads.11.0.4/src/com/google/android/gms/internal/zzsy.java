package com.google.android.gms.internal;

import android.os.RemoteException;

final class zzsy extends zzade {
   // $FF: synthetic field
   private zzsj zzKb;

   zzsy(zzsj var1) {
      this.zzKb = var1;
      super();
   }

   public final void onRewardedVideoAdLoaded() throws RemoteException {
      zzsj.zza(this.zzKb).add(new zzsz(this));
   }

   public final void onRewardedVideoAdOpened() throws RemoteException {
      zzsj.zza(this.zzKb).add(new zzta(this));
   }

   public final void onRewardedVideoStarted() throws RemoteException {
      zzsj.zza(this.zzKb).add(new zztb(this));
   }

   public final void onRewardedVideoAdClosed() throws RemoteException {
      zzsj.zza(this.zzKb).add(new zztc(this));
   }

   public final void zza(zzacv var1) throws RemoteException {
      zzsj.zza(this.zzKb).add(new zztd(this, var1));
   }

   public final void onRewardedVideoAdLeftApplication() throws RemoteException {
      zzsj.zza(this.zzKb).add(new zzte(this));
   }

   public final void onRewardedVideoAdFailedToLoad(int var1) throws RemoteException {
      zzsj.zza(this.zzKb).add(new zztf(this, var1));
   }
}
