package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public interface zzpj extends IInterface {
   String zzP(String var1) throws RemoteException;

   zzos zzQ(String var1) throws RemoteException;

   List getAvailableAssetNames() throws RemoteException;

   String getCustomTemplateId() throws RemoteException;

   void performClick(String var1) throws RemoteException;

   void recordImpression() throws RemoteException;

   zzks getVideoController() throws RemoteException;

   void destroy() throws RemoteException;

   IObjectWrapper zzen() throws RemoteException;

   boolean zzj(IObjectWrapper var1) throws RemoteException;

   IObjectWrapper zzei() throws RemoteException;
}
