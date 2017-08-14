package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface zzkn extends IInterface {
   void initialize() throws RemoteException;

   void setAppVolume(float var1) throws RemoteException;

   void zzu(String var1) throws RemoteException;

   void setAppMuted(boolean var1) throws RemoteException;

   void zzb(IObjectWrapper var1, String var2) throws RemoteException;

   void zzc(String var1, IObjectWrapper var2) throws RemoteException;
}
