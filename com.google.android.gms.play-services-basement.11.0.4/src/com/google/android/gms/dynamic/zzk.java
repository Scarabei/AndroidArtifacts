package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;

public interface zzk extends IInterface {
   IObjectWrapper zzty() throws RemoteException;

   Bundle getArguments() throws RemoteException;

   int getId() throws RemoteException;

   zzk zztz() throws RemoteException;

   IObjectWrapper zztA() throws RemoteException;

   boolean getRetainInstance() throws RemoteException;

   String getTag() throws RemoteException;

   zzk zztB() throws RemoteException;

   int getTargetRequestCode() throws RemoteException;

   boolean getUserVisibleHint() throws RemoteException;

   IObjectWrapper getView() throws RemoteException;

   boolean isAdded() throws RemoteException;

   boolean isDetached() throws RemoteException;

   boolean isHidden() throws RemoteException;

   boolean isInLayout() throws RemoteException;

   boolean isRemoving() throws RemoteException;

   boolean isResumed() throws RemoteException;

   boolean isVisible() throws RemoteException;

   void zzC(IObjectWrapper var1) throws RemoteException;

   void setHasOptionsMenu(boolean var1) throws RemoteException;

   void setMenuVisibility(boolean var1) throws RemoteException;

   void setRetainInstance(boolean var1) throws RemoteException;

   void setUserVisibleHint(boolean var1) throws RemoteException;

   void startActivity(Intent var1) throws RemoteException;

   void startActivityForResult(Intent var1, int var2) throws RemoteException;

   void zzD(IObjectWrapper var1) throws RemoteException;
}
