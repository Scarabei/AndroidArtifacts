package com.google.android.gms.maps.model.internal;

import android.os.IInterface;
import android.os.RemoteException;
import java.util.List;

public interface zzj extends IInterface {
   int getActiveLevelIndex() throws RemoteException;

   int getDefaultLevelIndex() throws RemoteException;

   List getLevels() throws RemoteException;

   boolean isUnderground() throws RemoteException;

   boolean zzb(zzj var1) throws RemoteException;

   int hashCodeRemote() throws RemoteException;
}
