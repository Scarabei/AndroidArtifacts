package com.google.android.gms.cast.framework;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface zzac extends IInterface {
   IObjectWrapper zznz() throws RemoteException;

   void start(Bundle var1) throws RemoteException;

   void resume(Bundle var1) throws RemoteException;

   void end(boolean var1) throws RemoteException;

   long getSessionRemainingTimeMs() throws RemoteException;
}
