package com.google.android.gms.internal;

import android.os.RemoteException;

final class zzsk extends zzjp {
   // $FF: synthetic field
   private zzsj zzKb;

   zzsk(zzsj var1) {
      this.zzKb = var1;
      super();
   }

   public final void onAdClosed() throws RemoteException {
      zzsj.zza(this.zzKb).add(new zzsl(this));
   }

   public final void onAdFailedToLoad(int var1) throws RemoteException {
      zzsj.zza(this.zzKb).add(new zzsm(this, var1));
      zzafr.v("Pooled interstitial failed to load.");
   }

   public final void onAdLeftApplication() throws RemoteException {
      zzsj.zza(this.zzKb).add(new zzsn(this));
   }

   public final void onAdLoaded() throws RemoteException {
      zzsj.zza(this.zzKb).add(new zzso(this));
      zzafr.v("Pooled interstitial loaded.");
   }

   public final void onAdOpened() throws RemoteException {
      zzsj.zza(this.zzKb).add(new zzsp(this));
   }

   public final void onAdImpression() throws RemoteException {
      zzsj.zza(this.zzKb).add(new zzsq(this));
   }

   public final void onAdClicked() throws RemoteException {
      zzsj.zza(this.zzKb).add(new zzsr(this));
   }
}
