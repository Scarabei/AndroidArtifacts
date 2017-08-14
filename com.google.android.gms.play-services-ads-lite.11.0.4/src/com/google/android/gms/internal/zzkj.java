package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzkj extends zzed implements zzkh {
   zzkj(IBinder var1) {
      super(var1, "com.google.android.gms.ads.internal.client.IClientApi");
   }

   public final zzjz createBannerAdManager(IObjectWrapper var1, zziv var2, String var3, zzuq var4, int var5) throws RemoteException {
      Parcel var6;
      zzef.zza(var6 = this.zzZ(), var1);
      zzef.zza(var6, var2);
      var6.writeString(var3);
      zzef.zza(var6, var4);
      var6.writeInt(var5);
      Parcel var7;
      IBinder var9;
      IInterface var10;
      Object var8 = (var9 = (var7 = this.zza(1, var6)).readStrongBinder()) == null ? null : ((var10 = var9.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager")) instanceof zzjz ? (zzjz)var10 : new zzkb(var9));
      var7.recycle();
      return (zzjz)var8;
   }

   public final zzjz createInterstitialAdManager(IObjectWrapper var1, zziv var2, String var3, zzuq var4, int var5) throws RemoteException {
      Parcel var6;
      zzef.zza(var6 = this.zzZ(), var1);
      zzef.zza(var6, var2);
      var6.writeString(var3);
      zzef.zza(var6, var4);
      var6.writeInt(var5);
      Parcel var7;
      IBinder var9;
      IInterface var10;
      Object var8 = (var9 = (var7 = this.zza(2, var6)).readStrongBinder()) == null ? null : ((var10 = var9.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager")) instanceof zzjz ? (zzjz)var10 : new zzkb(var9));
      var7.recycle();
      return (zzjz)var8;
   }

   public final zzju createAdLoaderBuilder(IObjectWrapper var1, String var2, zzuq var3, int var4) throws RemoteException {
      Parcel var5;
      zzef.zza(var5 = this.zzZ(), var1);
      var5.writeString(var2);
      zzef.zza(var5, var3);
      var5.writeInt(var4);
      Parcel var6;
      IBinder var8;
      IInterface var9;
      Object var7 = (var8 = (var6 = this.zza(3, var5)).readStrongBinder()) == null ? null : ((var9 = var8.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilder")) instanceof zzju ? (zzju)var9 : new zzjw(var8));
      var6.recycle();
      return (zzju)var7;
   }

   public final zzkn getMobileAdsSettingsManager(IObjectWrapper var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      IBinder var5;
      IInterface var6;
      Object var4 = (var5 = (var3 = this.zza(4, var2)).readStrongBinder()) == null ? null : ((var6 = var5.queryLocalInterface("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager")) instanceof zzkn ? (zzkn)var6 : new zzkp(var5));
      var3.recycle();
      return (zzkn)var4;
   }

   public final zzow createNativeAdViewDelegate(IObjectWrapper var1, IObjectWrapper var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      Parcel var4;
      zzow var5 = zzox.zzj((var4 = this.zza(5, var3)).readStrongBinder());
      var4.recycle();
      return var5;
   }

   public final zzacy createRewardedVideoAd(IObjectWrapper var1, zzuq var2, int var3) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), var1);
      zzef.zza(var4, var2);
      var4.writeInt(var3);
      Parcel var5;
      zzacy var6 = zzacz.zzv((var5 = this.zza(6, var4)).readStrongBinder());
      var5.recycle();
      return var6;
   }

   public final zzxj createInAppPurchaseManager(IObjectWrapper var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      zzxj var4 = zzxk.zzt((var3 = this.zza(7, var2)).readStrongBinder());
      var3.recycle();
      return var4;
   }

   public final zzwx createAdOverlay(IObjectWrapper var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      zzwx var4 = zzwy.zzr((var3 = this.zza(8, var2)).readStrongBinder());
      var3.recycle();
      return var4;
   }

   public final zzkn getMobileAdsSettingsManagerWithClientJarVersion(IObjectWrapper var1, int var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      var3.writeInt(var2);
      Parcel var4;
      IBinder var6;
      IInterface var7;
      Object var5 = (var6 = (var4 = this.zza(9, var3)).readStrongBinder()) == null ? null : ((var7 = var6.queryLocalInterface("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager")) instanceof zzkn ? (zzkn)var7 : new zzkp(var6));
      var4.recycle();
      return (zzkn)var5;
   }

   public final zzjz createSearchAdManager(IObjectWrapper var1, zziv var2, String var3, int var4) throws RemoteException {
      Parcel var5;
      zzef.zza(var5 = this.zzZ(), var1);
      zzef.zza(var5, var2);
      var5.writeString(var3);
      var5.writeInt(var4);
      Parcel var6;
      IBinder var8;
      IInterface var9;
      Object var7 = (var8 = (var6 = this.zza(10, var5)).readStrongBinder()) == null ? null : ((var9 = var8.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager")) instanceof zzjz ? (zzjz)var9 : new zzkb(var8));
      var6.recycle();
      return (zzjz)var7;
   }
}
