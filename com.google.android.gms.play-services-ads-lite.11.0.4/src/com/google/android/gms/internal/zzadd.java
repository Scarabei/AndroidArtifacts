package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;

public interface zzadd extends IInterface {
   void onRewardedVideoAdLoaded() throws RemoteException;

   void onRewardedVideoAdOpened() throws RemoteException;

   void onRewardedVideoStarted() throws RemoteException;

   void onRewardedVideoAdClosed() throws RemoteException;

   void zza(zzacv var1) throws RemoteException;

   void onRewardedVideoAdLeftApplication() throws RemoteException;

   void onRewardedVideoAdFailedToLoad(int var1) throws RemoteException;
}
