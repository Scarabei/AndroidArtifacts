package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public interface zzvf extends IInterface {
   String getHeadline() throws RemoteException;

   List getImages() throws RemoteException;

   String getBody() throws RemoteException;

   zzos zzem() throws RemoteException;

   String getCallToAction() throws RemoteException;

   String getAdvertiser() throws RemoteException;

   void recordImpression() throws RemoteException;

   void zzl(IObjectWrapper var1) throws RemoteException;

   void zzm(IObjectWrapper var1) throws RemoteException;

   boolean getOverrideImpressionRecording() throws RemoteException;

   boolean getOverrideClickHandling() throws RemoteException;

   Bundle getExtras() throws RemoteException;

   void zzn(IObjectWrapper var1) throws RemoteException;

   IObjectWrapper zzfw() throws RemoteException;

   zzks getVideoController() throws RemoteException;
}
