package com.google.android.gms.maps.model.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;

public interface zzp extends IInterface {
   void remove() throws RemoteException;

   String getId() throws RemoteException;

   void setPosition(LatLng var1) throws RemoteException;

   LatLng getPosition() throws RemoteException;

   void setTitle(String var1) throws RemoteException;

   String getTitle() throws RemoteException;

   void setSnippet(String var1) throws RemoteException;

   String getSnippet() throws RemoteException;

   void setDraggable(boolean var1) throws RemoteException;

   boolean isDraggable() throws RemoteException;

   void showInfoWindow() throws RemoteException;

   void hideInfoWindow() throws RemoteException;

   boolean isInfoWindowShown() throws RemoteException;

   void setVisible(boolean var1) throws RemoteException;

   boolean isVisible() throws RemoteException;

   boolean zzj(zzp var1) throws RemoteException;

   int hashCodeRemote() throws RemoteException;

   void zzK(IObjectWrapper var1) throws RemoteException;

   void setAnchor(float var1, float var2) throws RemoteException;

   void setFlat(boolean var1) throws RemoteException;

   boolean isFlat() throws RemoteException;

   void setRotation(float var1) throws RemoteException;

   float getRotation() throws RemoteException;

   void setInfoWindowAnchor(float var1, float var2) throws RemoteException;

   void setAlpha(float var1) throws RemoteException;

   float getAlpha() throws RemoteException;

   void setZIndex(float var1) throws RemoteException;

   float getZIndex() throws RemoteException;

   void setTag(IObjectWrapper var1) throws RemoteException;

   IObjectWrapper getTag() throws RemoteException;
}
