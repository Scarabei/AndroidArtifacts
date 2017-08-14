package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.StreetViewPanoramaOptions;

public interface zze extends IInterface {
   IMapFragmentDelegate zzH(IObjectWrapper var1) throws RemoteException;

   IMapViewDelegate zza(IObjectWrapper var1, GoogleMapOptions var2) throws RemoteException;

   ICameraUpdateFactoryDelegate zzwh() throws RemoteException;

   com.google.android.gms.maps.model.internal.zza zzwi() throws RemoteException;

   void zzi(IObjectWrapper var1, int var2) throws RemoteException;

   IStreetViewPanoramaViewDelegate zza(IObjectWrapper var1, StreetViewPanoramaOptions var2) throws RemoteException;

   IStreetViewPanoramaFragmentDelegate zzI(IObjectWrapper var1) throws RemoteException;
}
