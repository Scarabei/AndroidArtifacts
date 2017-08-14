package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface zzwx extends IInterface {
   void onCreate(Bundle var1) throws RemoteException;

   void onRestart() throws RemoteException;

   void onStart() throws RemoteException;

   void onResume() throws RemoteException;

   void onPause() throws RemoteException;

   void onSaveInstanceState(Bundle var1) throws RemoteException;

   void onStop() throws RemoteException;

   void onDestroy() throws RemoteException;

   void zzaa() throws RemoteException;

   void onBackPressed() throws RemoteException;

   boolean zzfK() throws RemoteException;

   void onActivityResult(int var1, int var2, Intent var3) throws RemoteException;

   void zzo(IObjectWrapper var1) throws RemoteException;
}
