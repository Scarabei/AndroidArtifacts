package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.VisibleRegion;

public interface IProjectionDelegate extends IInterface {
   LatLng fromScreenLocation(IObjectWrapper var1) throws RemoteException;

   IObjectWrapper toScreenLocation(LatLng var1) throws RemoteException;

   VisibleRegion getVisibleRegion() throws RemoteException;
}
