package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

public interface zzbf extends IInterface {
   void onStreetViewPanoramaCameraChange(StreetViewPanoramaCamera var1) throws RemoteException;
}
