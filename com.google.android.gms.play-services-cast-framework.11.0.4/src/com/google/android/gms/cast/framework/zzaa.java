package com.google.android.gms.cast.framework;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface zzaa extends IInterface {
   IObjectWrapper zzcd(String var1) throws RemoteException;

   boolean isSessionRecoverable() throws RemoteException;

   String getCategory() throws RemoteException;
}
