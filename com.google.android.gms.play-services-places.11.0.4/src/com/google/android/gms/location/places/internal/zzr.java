package com.google.android.gms.location.places.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.location.places.AddPlaceRequest;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.List;

public interface zzr extends IInterface {
   void zza(List var1, zzat var2, zzv var3) throws RemoteException;

   void zza(String var1, LatLngBounds var2, AutocompleteFilter var3, zzat var4, zzv var5) throws RemoteException;

   void zza(AddPlaceRequest var1, zzat var2, zzv var3) throws RemoteException;

   void zza(String var1, zzat var2, zzt var3) throws RemoteException;

   void zza(String var1, int var2, int var3, int var4, zzat var5, zzt var6) throws RemoteException;
}
