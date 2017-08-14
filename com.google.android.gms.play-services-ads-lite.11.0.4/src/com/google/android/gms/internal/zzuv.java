package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;
import java.util.List;

public final class zzuv extends zzed implements zzut {
   zzuv(IBinder var1) {
      super(var1, "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
   }

   public final void zza(IObjectWrapper var1, zziv var2, zzir var3, String var4, zzuw var5) throws RemoteException {
      Parcel var6;
      zzef.zza(var6 = this.zzZ(), var1);
      zzef.zza(var6, var2);
      zzef.zza(var6, var3);
      var6.writeString(var4);
      zzef.zza(var6, var5);
      this.zzb(1, var6);
   }

   public final IObjectWrapper getView() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      IObjectWrapper var3 = zza.zzM((var2 = this.zza(2, var1)).readStrongBinder());
      var2.recycle();
      return var3;
   }

   public final void zza(IObjectWrapper var1, zzir var2, String var3, zzuw var4) throws RemoteException {
      Parcel var5;
      zzef.zza(var5 = this.zzZ(), var1);
      zzef.zza(var5, var2);
      var5.writeString(var3);
      zzef.zza(var5, var4);
      this.zzb(3, var5);
   }

   public final void showInterstitial() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(4, var1);
   }

   public final void destroy() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(5, var1);
   }

   public final void zza(IObjectWrapper var1, zziv var2, zzir var3, String var4, String var5, zzuw var6) throws RemoteException {
      Parcel var7;
      zzef.zza(var7 = this.zzZ(), var1);
      zzef.zza(var7, var2);
      zzef.zza(var7, var3);
      var7.writeString(var4);
      var7.writeString(var5);
      zzef.zza(var7, var6);
      this.zzb(6, var7);
   }

   public final void zza(IObjectWrapper var1, zzir var2, String var3, String var4, zzuw var5) throws RemoteException {
      Parcel var6;
      zzef.zza(var6 = this.zzZ(), var1);
      zzef.zza(var6, var2);
      var6.writeString(var3);
      var6.writeString(var4);
      zzef.zza(var6, var5);
      this.zzb(7, var6);
   }

   public final void pause() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(8, var1);
   }

   public final void resume() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(9, var1);
   }

   public final void zza(IObjectWrapper var1, zzir var2, String var3, zzaea var4, String var5) throws RemoteException {
      Parcel var6;
      zzef.zza(var6 = this.zzZ(), var1);
      zzef.zza(var6, var2);
      var6.writeString(var3);
      zzef.zza(var6, var4);
      var6.writeString(var5);
      this.zzb(10, var6);
   }

   public final void zzc(zzir var1, String var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      var3.writeString(var2);
      this.zzb(11, var3);
   }

   public final void showVideo() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(12, var1);
   }

   public final boolean isInitialized() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(13, var1));
      var2.recycle();
      return var3;
   }

   public final void zza(IObjectWrapper var1, zzir var2, String var3, String var4, zzuw var5, zzon var6, List var7) throws RemoteException {
      Parcel var8;
      zzef.zza(var8 = this.zzZ(), var1);
      zzef.zza(var8, var2);
      var8.writeString(var3);
      var8.writeString(var4);
      zzef.zza(var8, var5);
      zzef.zza(var8, var6);
      var8.writeStringList(var7);
      this.zzb(14, var8);
   }

   public final zzvc zzfq() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      IBinder var4;
      IInterface var5;
      Object var3 = (var4 = (var2 = this.zza(15, var1)).readStrongBinder()) == null ? null : ((var5 = var4.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.INativeAppInstallAdMapper")) instanceof zzvc ? (zzvc)var5 : new zzve(var4));
      var2.recycle();
      return (zzvc)var3;
   }

   public final zzvf zzfr() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      IBinder var4;
      IInterface var5;
      Object var3 = (var4 = (var2 = this.zza(16, var1)).readStrongBinder()) == null ? null : ((var5 = var4.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper")) instanceof zzvf ? (zzvf)var5 : new zzvh(var4));
      var2.recycle();
      return (zzvf)var3;
   }

   public final Bundle zzfs() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      Bundle var3 = (Bundle)zzef.zza(var2 = this.zza(17, var1), Bundle.CREATOR);
      var2.recycle();
      return var3;
   }

   public final Bundle getInterstitialAdapterInfo() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      Bundle var3 = (Bundle)zzef.zza(var2 = this.zza(18, var1), Bundle.CREATOR);
      var2.recycle();
      return var3;
   }

   public final Bundle zzft() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      Bundle var3 = (Bundle)zzef.zza(var2 = this.zza(19, var1), Bundle.CREATOR);
      var2.recycle();
      return var3;
   }

   public final void zza(zzir var1, String var2, String var3) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), var1);
      var4.writeString(var2);
      var4.writeString(var3);
      this.zzb(20, var4);
   }

   public final void zzk(IObjectWrapper var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(21, var2);
   }

   public final boolean zzfu() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(22, var1));
      var2.recycle();
      return var3;
   }

   public final void zza(IObjectWrapper var1, zzaea var2, List var3) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), var1);
      zzef.zza(var4, var2);
      var4.writeStringList(var3);
      this.zzb(23, var4);
   }

   public final zzpj zzfv() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      zzpj var3 = zzpk.zzk((var2 = this.zza(24, var1)).readStrongBinder());
      var2.recycle();
      return var3;
   }

   public final void setImmersiveMode(boolean var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(25, var2);
   }

   public final zzks getVideoController() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      zzks var3 = zzkt.zzg((var2 = this.zza(26, var1)).readStrongBinder());
      var2.recycle();
      return var3;
   }
}
