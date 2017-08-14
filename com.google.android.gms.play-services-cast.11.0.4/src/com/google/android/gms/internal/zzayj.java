package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.zzz;

public interface zzayj extends IInterface {
   void disconnect() throws RemoteException;

   void zzoK() throws RemoteException;

   void zzcc(String var1) throws RemoteException;

   void requestStatus() throws RemoteException;

   void zza(double var1, double var3, boolean var5) throws RemoteException;

   void zza(boolean var1, double var2, boolean var4) throws RemoteException;

   void zzb(String var1, String var2, long var3) throws RemoteException;

   void zzcl(String var1) throws RemoteException;

   void zzcm(String var1) throws RemoteException;

   void zzb(String var1, LaunchOptions var2) throws RemoteException;

   void zza(String var1, String var2, zzz var3) throws RemoteException;
}
