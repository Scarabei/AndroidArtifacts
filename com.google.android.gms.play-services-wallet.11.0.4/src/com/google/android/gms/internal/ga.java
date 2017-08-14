package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.fragment.WalletFragmentInitParams;
import com.google.android.gms.wallet.fragment.WalletFragmentOptions;

public interface ga extends IInterface {
   void zza(IObjectWrapper var1, WalletFragmentOptions var2, Bundle var3) throws RemoteException;

   void onCreate(Bundle var1) throws RemoteException;

   IObjectWrapper onCreateView(IObjectWrapper var1, IObjectWrapper var2, Bundle var3) throws RemoteException;

   void onStart() throws RemoteException;

   void onResume() throws RemoteException;

   void onPause() throws RemoteException;

   void onStop() throws RemoteException;

   void onSaveInstanceState(Bundle var1) throws RemoteException;

   void onActivityResult(int var1, int var2, Intent var3) throws RemoteException;

   void initialize(WalletFragmentInitParams var1) throws RemoteException;

   void updateMaskedWalletRequest(MaskedWalletRequest var1) throws RemoteException;

   void setEnabled(boolean var1) throws RemoteException;

   int getState() throws RemoteException;

   void updateMaskedWallet(MaskedWallet var1) throws RemoteException;
}
