package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest;
import com.google.android.gms.fitness.request.DataUpdateRequest;
import com.google.android.gms.fitness.request.zzf;
import com.google.android.gms.fitness.request.zzj;
import com.google.android.gms.fitness.request.zzv;

public interface zzbwn extends IInterface {
   void zza(DataReadRequest var1) throws RemoteException;

   void zza(zzj var1) throws RemoteException;

   void zza(DataDeleteRequest var1) throws RemoteException;

   void zza(zzf var1) throws RemoteException;

   void zza(DataUpdateRequest var1) throws RemoteException;

   void zza(DataUpdateListenerRegistrationRequest var1) throws RemoteException;

   void zza(zzv var1) throws RemoteException;
}
