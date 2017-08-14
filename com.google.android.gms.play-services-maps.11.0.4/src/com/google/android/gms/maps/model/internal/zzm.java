package com.google.android.gms.maps.model.internal;

import android.os.IInterface;
import android.os.RemoteException;

public interface zzm extends IInterface {
   String getName() throws RemoteException;

   String getShortName() throws RemoteException;

   void activate() throws RemoteException;

   boolean zza(zzm var1) throws RemoteException;

   int hashCodeRemote() throws RemoteException;
}
