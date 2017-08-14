package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public interface zzpf extends IInterface {
   IObjectWrapper zzei() throws RemoteException;

   String getHeadline() throws RemoteException;

   List getImages() throws RemoteException;

   String getBody() throws RemoteException;

   zzos zzem() throws RemoteException;

   String getCallToAction() throws RemoteException;

   String getAdvertiser() throws RemoteException;

   Bundle getExtras() throws RemoteException;

   void destroy() throws RemoteException;

   zzks getVideoController() throws RemoteException;

   void zzc(Bundle var1) throws RemoteException;

   boolean zzd(Bundle var1) throws RemoteException;

   void zze(Bundle var1) throws RemoteException;
}
