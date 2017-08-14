package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzuy extends zzed implements zzuw {
   zzuy(IBinder var1) {
      super(var1, "com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
   }

   public final void onAdClicked() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(1, var1);
   }

   public final void onAdClosed() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(2, var1);
   }

   public final void onAdFailedToLoad(int var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeInt(var1);
      this.zzb(3, var2);
   }

   public final void onAdLeftApplication() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(4, var1);
   }

   public final void onAdOpened() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(5, var1);
   }

   public final void onAdLoaded() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(6, var1);
   }

   public final void zza(zzuz var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(7, var2);
   }

   public final void onAdImpression() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(8, var1);
   }

   public final void onAppEvent(String var1, String var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeString(var1);
      var3.writeString(var2);
      this.zzb(9, var3);
   }

   public final void zzb(zzpj var1, String var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      var3.writeString(var2);
      this.zzb(10, var3);
   }
}
