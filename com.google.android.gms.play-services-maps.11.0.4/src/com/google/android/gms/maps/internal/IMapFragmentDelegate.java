package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.GoogleMapOptions;

public interface IMapFragmentDelegate extends IInterface {
   IGoogleMapDelegate getMap() throws RemoteException;

   void onInflate(IObjectWrapper var1, GoogleMapOptions var2, Bundle var3) throws RemoteException;

   void onCreate(Bundle var1) throws RemoteException;

   IObjectWrapper onCreateView(IObjectWrapper var1, IObjectWrapper var2, Bundle var3) throws RemoteException;

   void onResume() throws RemoteException;

   void onPause() throws RemoteException;

   void onDestroyView() throws RemoteException;

   void onDestroy() throws RemoteException;

   void onLowMemory() throws RemoteException;

   void onSaveInstanceState(Bundle var1) throws RemoteException;

   boolean isReady() throws RemoteException;

   void getMapAsync(zzap var1) throws RemoteException;

   void onEnterAmbient(Bundle var1) throws RemoteException;

   void onExitAmbient() throws RemoteException;

   void onStart() throws RemoteException;

   void onStop() throws RemoteException;
}
