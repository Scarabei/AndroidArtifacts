package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public interface ICameraUpdateFactoryDelegate extends IInterface {
   IObjectWrapper zoomIn() throws RemoteException;

   IObjectWrapper zoomOut() throws RemoteException;

   IObjectWrapper scrollBy(float var1, float var2) throws RemoteException;

   IObjectWrapper zoomTo(float var1) throws RemoteException;

   IObjectWrapper zoomBy(float var1) throws RemoteException;

   IObjectWrapper zoomByWithFocus(float var1, int var2, int var3) throws RemoteException;

   IObjectWrapper newCameraPosition(CameraPosition var1) throws RemoteException;

   IObjectWrapper newLatLng(LatLng var1) throws RemoteException;

   IObjectWrapper newLatLngZoom(LatLng var1, float var2) throws RemoteException;

   IObjectWrapper newLatLngBounds(LatLngBounds var1, int var2) throws RemoteException;

   IObjectWrapper newLatLngBoundsWithSize(LatLngBounds var1, int var2, int var3, int var4) throws RemoteException;
}
