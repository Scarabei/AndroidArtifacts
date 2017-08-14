package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;

public interface zzuw extends IInterface {
   void onAdClicked() throws RemoteException;

   void onAdClosed() throws RemoteException;

   void onAdFailedToLoad(int var1) throws RemoteException;

   void onAdLeftApplication() throws RemoteException;

   void onAdOpened() throws RemoteException;

   void onAdLoaded() throws RemoteException;

   void zza(zzuz var1) throws RemoteException;

   void onAdImpression() throws RemoteException;

   void onAppEvent(String var1, String var2) throws RemoteException;

   void zzb(zzpj var1, String var2) throws RemoteException;
}
