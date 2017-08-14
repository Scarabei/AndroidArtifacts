package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.NotifyTransactionStatusRequest;

public interface gf extends IInterface {
   void zza(MaskedWalletRequest var1, Bundle var2, gj var3) throws RemoteException;

   void zza(FullWalletRequest var1, Bundle var2, gj var3) throws RemoteException;

   void zza(String var1, String var2, Bundle var3, gj var4) throws RemoteException;

   void zza(NotifyTransactionStatusRequest var1, Bundle var2) throws RemoteException;

   void zza(Bundle var1, gj var2) throws RemoteException;

   void zzb(Bundle var1, gj var2) throws RemoteException;

   void zza(IsReadyToPayRequest var1, Bundle var2, gj var3) throws RemoteException;
}
