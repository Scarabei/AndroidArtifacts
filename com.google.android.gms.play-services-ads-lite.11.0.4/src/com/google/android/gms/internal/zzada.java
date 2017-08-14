package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzada extends zzed implements zzacy {
   zzada(IBinder var1) {
      super(var1, "com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
   }

   public final void zza(zzadj var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(1, var2);
   }

   public final void show() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(2, var1);
   }

   public final void zza(zzadd var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(3, var2);
   }

   public final void setUserId(String var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeString(var1);
      this.zzb(4, var2);
   }

   public final boolean isLoaded() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(5, var1));
      var2.recycle();
      return var3;
   }

   public final void pause() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(6, var1);
   }

   public final void resume() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(7, var1);
   }

   public final void destroy() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(8, var1);
   }

   public final void zzf(IObjectWrapper var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(9, var2);
   }

   public final void zzg(IObjectWrapper var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(10, var2);
   }

   public final void zzh(IObjectWrapper var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(11, var2);
   }

   public final String getMediationAdapterClassName() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      String var3 = (var2 = this.zza(12, var1)).readString();
      var2.recycle();
      return var3;
   }

   public final void setImmersiveMode(boolean var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(34, var2);
   }
}
