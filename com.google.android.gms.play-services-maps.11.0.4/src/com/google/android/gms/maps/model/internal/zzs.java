package com.google.android.gms.maps.model.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public interface zzs extends IInterface {
   void remove() throws RemoteException;

   String getId() throws RemoteException;

   void setPoints(List var1) throws RemoteException;

   List getPoints() throws RemoteException;

   void setHoles(List var1) throws RemoteException;

   List getHoles() throws RemoteException;

   void setStrokeWidth(float var1) throws RemoteException;

   float getStrokeWidth() throws RemoteException;

   void setStrokeColor(int var1) throws RemoteException;

   int getStrokeColor() throws RemoteException;

   void setFillColor(int var1) throws RemoteException;

   int getFillColor() throws RemoteException;

   void setZIndex(float var1) throws RemoteException;

   float getZIndex() throws RemoteException;

   void setVisible(boolean var1) throws RemoteException;

   boolean isVisible() throws RemoteException;

   void setGeodesic(boolean var1) throws RemoteException;

   boolean isGeodesic() throws RemoteException;

   boolean zzb(zzs var1) throws RemoteException;

   int hashCodeRemote() throws RemoteException;

   void setClickable(boolean var1) throws RemoteException;

   boolean isClickable() throws RemoteException;

   void setStrokeJointType(int var1) throws RemoteException;

   int getStrokeJointType() throws RemoteException;

   void setStrokePattern(List var1) throws RemoteException;

   List getStrokePattern() throws RemoteException;

   void setTag(IObjectWrapper var1) throws RemoteException;

   IObjectWrapper getTag() throws RemoteException;
}
