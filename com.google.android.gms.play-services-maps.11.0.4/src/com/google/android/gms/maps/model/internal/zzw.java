package com.google.android.gms.maps.model.internal;

import android.os.IInterface;
import android.os.RemoteException;

public interface zzw extends IInterface {
   void remove() throws RemoteException;

   void clearTileCache() throws RemoteException;

   String getId() throws RemoteException;

   void setZIndex(float var1) throws RemoteException;

   float getZIndex() throws RemoteException;

   void setVisible(boolean var1) throws RemoteException;

   boolean isVisible() throws RemoteException;

   boolean zza(zzw var1) throws RemoteException;

   int hashCodeRemote() throws RemoteException;

   void setFadeIn(boolean var1) throws RemoteException;

   boolean getFadeIn() throws RemoteException;

   void setTransparency(float var1) throws RemoteException;

   float getTransparency() throws RemoteException;
}
