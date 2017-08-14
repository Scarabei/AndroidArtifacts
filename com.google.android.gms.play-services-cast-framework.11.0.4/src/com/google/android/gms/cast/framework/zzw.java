package com.google.android.gms.cast.framework;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface zzw extends IInterface {
   IObjectWrapper zzny() throws RemoteException;

   void zza(zzy var1) throws RemoteException;

   void zzb(zzy var1) throws RemoteException;

   void zza(zzo var1) throws RemoteException;

   void zzb(zzo var1) throws RemoteException;

   void zzb(boolean var1, boolean var2) throws RemoteException;

   IObjectWrapper zznu() throws RemoteException;

   int getCastState() throws RemoteException;
}
