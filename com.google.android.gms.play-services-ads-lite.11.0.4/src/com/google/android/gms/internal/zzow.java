package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface zzow extends IInterface {
   void zzd(String var1, IObjectWrapper var2) throws RemoteException;

   IObjectWrapper zzL(String var1) throws RemoteException;

   void zze(IObjectWrapper var1) throws RemoteException;

   void destroy() throws RemoteException;

   void zzb(IObjectWrapper var1, int var2) throws RemoteException;
}
