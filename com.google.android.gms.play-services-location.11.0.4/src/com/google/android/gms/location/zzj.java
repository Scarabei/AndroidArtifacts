package com.google.android.gms.location;

import android.os.IInterface;
import android.os.RemoteException;

public interface zzj extends IInterface {
   void onLocationResult(LocationResult var1) throws RemoteException;

   void onLocationAvailability(LocationAvailability var1) throws RemoteException;
}
