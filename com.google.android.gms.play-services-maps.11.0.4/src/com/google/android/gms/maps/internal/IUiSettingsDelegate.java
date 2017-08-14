package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;

public interface IUiSettingsDelegate extends IInterface {
   void setZoomControlsEnabled(boolean var1) throws RemoteException;

   void setCompassEnabled(boolean var1) throws RemoteException;

   void setMyLocationButtonEnabled(boolean var1) throws RemoteException;

   void setScrollGesturesEnabled(boolean var1) throws RemoteException;

   void setZoomGesturesEnabled(boolean var1) throws RemoteException;

   void setTiltGesturesEnabled(boolean var1) throws RemoteException;

   void setRotateGesturesEnabled(boolean var1) throws RemoteException;

   void setAllGesturesEnabled(boolean var1) throws RemoteException;

   boolean isZoomControlsEnabled() throws RemoteException;

   boolean isCompassEnabled() throws RemoteException;

   boolean isMyLocationButtonEnabled() throws RemoteException;

   boolean isScrollGesturesEnabled() throws RemoteException;

   boolean isZoomGesturesEnabled() throws RemoteException;

   boolean isTiltGesturesEnabled() throws RemoteException;

   boolean isRotateGesturesEnabled() throws RemoteException;

   void setIndoorLevelPickerEnabled(boolean var1) throws RemoteException;

   boolean isIndoorLevelPickerEnabled() throws RemoteException;

   void setMapToolbarEnabled(boolean var1) throws RemoteException;

   boolean isMapToolbarEnabled() throws RemoteException;
}
