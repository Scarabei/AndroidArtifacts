package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;

public interface zzjo extends IInterface {
   void onAdClosed() throws RemoteException;

   void onAdFailedToLoad(int var1) throws RemoteException;

   void onAdLeftApplication() throws RemoteException;

   void onAdLoaded() throws RemoteException;

   void onAdOpened() throws RemoteException;

   void onAdClicked() throws RemoteException;

   void onAdImpression() throws RemoteException;
}
