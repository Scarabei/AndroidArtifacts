package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface zzcac extends IInterface {
   void init(IObjectWrapper var1) throws RemoteException;

   boolean getBooleanFlagValue(String var1, boolean var2, int var3) throws RemoteException;

   int getIntFlagValue(String var1, int var2, int var3) throws RemoteException;

   long getLongFlagValue(String var1, long var2, int var4) throws RemoteException;

   String getStringFlagValue(String var1, String var2, int var3) throws RemoteException;
}
