package com.google.android.gms.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.auth.AccountChangeEventsRequest;
import com.google.android.gms.auth.AccountChangeEventsResponse;

public interface zzeg extends IInterface {
   Bundle zza(String var1, String var2, Bundle var3) throws RemoteException;

   Bundle zza(String var1, Bundle var2) throws RemoteException;

   AccountChangeEventsResponse zza(AccountChangeEventsRequest var1) throws RemoteException;

   Bundle zza(Account var1, String var2, Bundle var3) throws RemoteException;

   Bundle zza(Bundle var1) throws RemoteException;

   Bundle zza(Account var1) throws RemoteException;
}
