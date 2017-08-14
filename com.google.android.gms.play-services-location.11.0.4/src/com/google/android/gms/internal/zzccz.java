package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.location.Location;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.zzaa;

public interface zzccz extends IInterface {
   void zza(GeofencingRequest var1, PendingIntent var2, zzccx var3) throws RemoteException;

   void zza(zzaa var1, zzccx var2) throws RemoteException;

   void zza(long var1, boolean var3, PendingIntent var4) throws RemoteException;

   void zzc(PendingIntent var1) throws RemoteException;

   void zza(zzcdp var1) throws RemoteException;

   void zzai(boolean var1) throws RemoteException;

   void zzc(Location var1) throws RemoteException;

   Location zzdv(String var1) throws RemoteException;

   void zza(zzccu var1) throws RemoteException;

   LocationAvailability zzdw(String var1) throws RemoteException;

   void zza(LocationSettingsRequest var1, zzcdb var2, String var3) throws RemoteException;
}
