package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafeBrowsingData;
import com.google.android.gms.safetynet.zza;
import com.google.android.gms.safetynet.zzd;
import com.google.android.gms.safetynet.zzf;

public interface zzcrw extends IInterface {
   void zza(Status var1, zza var2) throws RemoteException;

   void zzeG(String var1) throws RemoteException;

   void zza(Status var1, SafeBrowsingData var2) throws RemoteException;

   void zza(Status var1, boolean var2) throws RemoteException;

   void zza(Status var1, zzd var2) throws RemoteException;

   void zza(Status var1, zzf var2) throws RemoteException;

   void zzb(Status var1, boolean var2) throws RemoteException;

   void zzc(Status var1, boolean var2) throws RemoteException;

   void zzd(Status var1, boolean var2) throws RemoteException;

   void zzF(Status var1) throws RemoteException;
}
