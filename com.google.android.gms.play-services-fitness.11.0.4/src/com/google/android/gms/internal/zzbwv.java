package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.request.zzaw;
import com.google.android.gms.fitness.request.zzay;
import com.google.android.gms.fitness.request.zzba;
import com.google.android.gms.fitness.request.zzbc;

public interface zzbwv extends IInterface {
   void zza(zzay var1) throws RemoteException;

   void zza(zzba var1) throws RemoteException;

   void zza(SessionInsertRequest var1) throws RemoteException;

   void zza(SessionReadRequest var1) throws RemoteException;

   void zza(zzaw var1) throws RemoteException;

   void zza(zzbc var1) throws RemoteException;
}
