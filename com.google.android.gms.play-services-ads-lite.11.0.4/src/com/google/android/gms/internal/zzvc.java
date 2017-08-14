package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public interface zzvc extends IInterface {
   String getHeadline() throws RemoteException;

   List getImages() throws RemoteException;

   String getBody() throws RemoteException;

   zzos zzeh() throws RemoteException;

   String getCallToAction() throws RemoteException;

   double getStarRating() throws RemoteException;

   String getStore() throws RemoteException;

   String getPrice() throws RemoteException;

   void recordImpression() throws RemoteException;

   void zzl(IObjectWrapper var1) throws RemoteException;

   void zzm(IObjectWrapper var1) throws RemoteException;

   boolean getOverrideImpressionRecording() throws RemoteException;

   boolean getOverrideClickHandling() throws RemoteException;

   Bundle getExtras() throws RemoteException;

   void zzn(IObjectWrapper var1) throws RemoteException;

   zzks getVideoController() throws RemoteException;

   IObjectWrapper zzfw() throws RemoteException;
}
