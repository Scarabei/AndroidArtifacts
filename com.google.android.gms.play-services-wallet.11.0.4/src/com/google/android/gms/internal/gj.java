package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.MaskedWallet;

public interface gj extends IInterface {
   void zza(int var1, MaskedWallet var2, Bundle var3) throws RemoteException;

   void zza(int var1, FullWallet var2, Bundle var3) throws RemoteException;

   void zza(int var1, boolean var2, Bundle var3) throws RemoteException;

   void zzg(int var1, Bundle var2) throws RemoteException;

   void zzb(int var1, boolean var2, Bundle var3) throws RemoteException;

   void zza(Status var1, boolean var2, Bundle var3) throws RemoteException;
}
