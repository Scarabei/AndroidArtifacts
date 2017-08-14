package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface zzkh extends IInterface {
   zzjz createBannerAdManager(IObjectWrapper var1, zziv var2, String var3, zzuq var4, int var5) throws RemoteException;

   zzjz createInterstitialAdManager(IObjectWrapper var1, zziv var2, String var3, zzuq var4, int var5) throws RemoteException;

   zzju createAdLoaderBuilder(IObjectWrapper var1, String var2, zzuq var3, int var4) throws RemoteException;

   zzkn getMobileAdsSettingsManager(IObjectWrapper var1) throws RemoteException;

   zzow createNativeAdViewDelegate(IObjectWrapper var1, IObjectWrapper var2) throws RemoteException;

   zzacy createRewardedVideoAd(IObjectWrapper var1, zzuq var2, int var3) throws RemoteException;

   zzxj createInAppPurchaseManager(IObjectWrapper var1) throws RemoteException;

   zzwx createAdOverlay(IObjectWrapper var1) throws RemoteException;

   zzkn getMobileAdsSettingsManagerWithClientJarVersion(IObjectWrapper var1, int var2) throws RemoteException;

   zzjz createSearchAdManager(IObjectWrapper var1, zziv var2, String var3, int var4) throws RemoteException;
}
