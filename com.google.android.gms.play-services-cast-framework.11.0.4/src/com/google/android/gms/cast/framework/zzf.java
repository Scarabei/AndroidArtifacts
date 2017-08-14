package com.google.android.gms.cast.framework;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface zzf extends IInterface {
   int zznm() throws RemoteException;

   IObjectWrapper zznn() throws RemoteException;

   void onAppEnteredForeground() throws RemoteException;

   void onAppEnteredBackground() throws RemoteException;
}
