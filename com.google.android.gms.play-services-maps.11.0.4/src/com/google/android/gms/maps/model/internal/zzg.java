package com.google.android.gms.maps.model.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public interface zzg extends IInterface {
   void remove() throws RemoteException;

   String getId() throws RemoteException;

   void setPosition(LatLng var1) throws RemoteException;

   LatLng getPosition() throws RemoteException;

   void setDimensions(float var1) throws RemoteException;

   void zzf(float var1, float var2) throws RemoteException;

   float getWidth() throws RemoteException;

   float getHeight() throws RemoteException;

   void setPositionFromBounds(LatLngBounds var1) throws RemoteException;

   LatLngBounds getBounds() throws RemoteException;

   void setBearing(float var1) throws RemoteException;

   float getBearing() throws RemoteException;

   void setZIndex(float var1) throws RemoteException;

   float getZIndex() throws RemoteException;

   void setVisible(boolean var1) throws RemoteException;

   boolean isVisible() throws RemoteException;

   void setTransparency(float var1) throws RemoteException;

   float getTransparency() throws RemoteException;

   boolean zzb(zzg var1) throws RemoteException;

   int hashCodeRemote() throws RemoteException;

   void zzJ(IObjectWrapper var1) throws RemoteException;

   void setClickable(boolean var1) throws RemoteException;

   boolean isClickable() throws RemoteException;

   void setTag(IObjectWrapper var1) throws RemoteException;

   IObjectWrapper getTag() throws RemoteException;
}
