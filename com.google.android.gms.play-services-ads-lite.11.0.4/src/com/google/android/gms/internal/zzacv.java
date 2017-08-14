package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;

public interface zzacv extends IInterface {
   String getType() throws RemoteException;

   int getAmount() throws RemoteException;
}
