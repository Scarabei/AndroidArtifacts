package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;

public interface IStreetViewPanoramaDelegate extends IInterface {
   void enableZoom(boolean var1) throws RemoteException;

   void enablePanning(boolean var1) throws RemoteException;

   void enableUserNavigation(boolean var1) throws RemoteException;

   void enableStreetNames(boolean var1) throws RemoteException;

   boolean isZoomGesturesEnabled() throws RemoteException;

   boolean isPanningGesturesEnabled() throws RemoteException;

   boolean isUserNavigationEnabled() throws RemoteException;

   boolean isStreetNamesEnabled() throws RemoteException;

   void animateTo(StreetViewPanoramaCamera var1, long var2) throws RemoteException;

   StreetViewPanoramaCamera getPanoramaCamera() throws RemoteException;

   void setPositionWithID(String var1) throws RemoteException;

   void setPosition(LatLng var1) throws RemoteException;

   void setPositionWithRadius(LatLng var1, int var2) throws RemoteException;

   StreetViewPanoramaLocation getStreetViewPanoramaLocation() throws RemoteException;

   void setOnStreetViewPanoramaChangeListener(zzbh var1) throws RemoteException;

   void setOnStreetViewPanoramaCameraChangeListener(zzbf var1) throws RemoteException;

   void setOnStreetViewPanoramaClickListener(zzbj var1) throws RemoteException;

   StreetViewPanoramaOrientation pointToOrientation(IObjectWrapper var1) throws RemoteException;

   IObjectWrapper orientationToPoint(StreetViewPanoramaOrientation var1) throws RemoteException;

   void setOnStreetViewPanoramaLongClickListener(zzbl var1) throws RemoteException;
}
