package com.google.android.gms.internal;

import android.content.Intent;
import android.os.IInterface;
import android.os.RemoteException;

public interface zzxj extends IInterface {
   void onCreate() throws RemoteException;

   void onDestroy() throws RemoteException;

   void onActivityResult(int var1, int var2, Intent var3) throws RemoteException;
}
