package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;

public final class zzkb extends zzed implements zzjz {
   zzkb(IBinder var1) {
      super(var1, "com.google.android.gms.ads.internal.client.IAdManager");
   }

   public final IObjectWrapper zzal() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      IObjectWrapper var3 = zza.zzM((var2 = this.zza(1, var1)).readStrongBinder());
      var2.recycle();
      return var3;
   }

   public final void destroy() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(2, var1);
   }

   public final boolean isReady() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(3, var1));
      var2.recycle();
      return var3;
   }

   public final boolean zza(zzir var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      boolean var4 = zzef.zza(var3 = this.zza(4, var2));
      var3.recycle();
      return var4;
   }

   public final void pause() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(5, var1);
   }

   public final void resume() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(6, var1);
   }

   public final void zza(zzjo var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(7, var2);
   }

   public final void zza(zzke var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(8, var2);
   }

   public final void showInterstitial() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(9, var1);
   }

   public final void stopLoading() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(10, var1);
   }

   public final void zzao() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(11, var1);
   }

   public final zziv zzam() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      zziv var3 = (zziv)zzef.zza(var2 = this.zza(12, var1), zziv.CREATOR);
      var2.recycle();
      return var3;
   }

   public final void zza(zziv var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(13, var2);
   }

   public final void zza(zzxg var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(14, var2);
   }

   public final void zza(zzxo var1, String var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      var3.writeString(var2);
      this.zzb(15, var3);
   }

   public final String getMediationAdapterClassName() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      String var3 = (var2 = this.zza(18, var1)).readString();
      var2.recycle();
      return var3;
   }

   public final void zza(zznh var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(19, var2);
   }

   public final void zza(zzjl var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(20, var2);
   }

   public final void zza(zzkk var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(21, var2);
   }

   public final void setManualImpressionsEnabled(boolean var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(22, var2);
   }

   public final boolean isLoading() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(23, var1));
      var2.recycle();
      return var3;
   }

   public final void zza(zzadd var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(24, var2);
   }

   public final void setUserId(String var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeString(var1);
      this.zzb(25, var2);
   }

   public final zzks getVideoController() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      IBinder var4;
      IInterface var5;
      Object var3 = (var4 = (var2 = this.zza(26, var1)).readStrongBinder()) == null ? null : ((var5 = var4.queryLocalInterface("com.google.android.gms.ads.internal.client.IVideoController")) instanceof zzks ? (zzks)var5 : new zzku(var4));
      var2.recycle();
      return (zzks)var3;
   }

   public final void zza(zzlx var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(29, var2);
   }

   public final void zza(zzky var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(30, var2);
   }

   public final String getAdUnitId() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      String var3 = (var2 = this.zza(31, var1)).readString();
      var2.recycle();
      return var3;
   }

   public final zzke zzax() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      IBinder var4;
      IInterface var5;
      Object var3 = (var4 = (var2 = this.zza(32, var1)).readStrongBinder()) == null ? null : ((var5 = var4.queryLocalInterface("com.google.android.gms.ads.internal.client.IAppEventListener")) instanceof zzke ? (zzke)var5 : new zzkg(var4));
      var2.recycle();
      return (zzke)var3;
   }

   public final zzjo zzay() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      IBinder var4;
      IInterface var5;
      Object var3 = (var4 = (var2 = this.zza(33, var1)).readStrongBinder()) == null ? null : ((var5 = var4.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdListener")) instanceof zzjo ? (zzjo)var5 : new zzjq(var4));
      var2.recycle();
      return (zzjo)var3;
   }

   public final void setImmersiveMode(boolean var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(34, var2);
   }

   public final String zzaI() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      String var3 = (var2 = this.zza(35, var1)).readString();
      var2.recycle();
      return var3;
   }
}
