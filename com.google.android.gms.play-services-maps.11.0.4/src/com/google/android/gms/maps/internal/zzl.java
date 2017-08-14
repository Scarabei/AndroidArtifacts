package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.maps.model.CameraPosition;

public interface zzl extends IInterface {
   void onCameraChange(CameraPosition var1) throws RemoteException;
}
