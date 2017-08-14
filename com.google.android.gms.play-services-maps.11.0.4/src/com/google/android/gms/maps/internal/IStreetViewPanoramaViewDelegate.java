package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface IStreetViewPanoramaViewDelegate extends IInterface {
   IStreetViewPanoramaDelegate getStreetViewPanorama() throws RemoteException;

   void onCreate(Bundle var1) throws RemoteException;

   void onResume() throws RemoteException;

   void onPause() throws RemoteException;

   void onDestroy() throws RemoteException;

   void onLowMemory() throws RemoteException;

   void onSaveInstanceState(Bundle var1) throws RemoteException;

   IObjectWrapper getView() throws RemoteException;

   void getStreetViewPanoramaAsync(zzbn var1) throws RemoteException;
}
