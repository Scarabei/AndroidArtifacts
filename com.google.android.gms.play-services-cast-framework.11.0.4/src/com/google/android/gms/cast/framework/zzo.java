package com.google.android.gms.cast.framework;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface zzo extends IInterface {
   IObjectWrapper zznn() throws RemoteException;

   void onCastStateChanged(int var1) throws RemoteException;
}
