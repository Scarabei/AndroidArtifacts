package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.IInterface;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.PutDataRequest;

public interface zzdn extends IInterface {
   void zza(zzdi var1, PutDataRequest var2) throws RemoteException;

   void zza(zzdi var1, Uri var2) throws RemoteException;

   void zza(zzdi var1) throws RemoteException;

   void zza(zzdi var1, Uri var2, int var3) throws RemoteException;

   void zzb(zzdi var1, Uri var2, int var3) throws RemoteException;

   void zza(zzdi var1, String var2, String var3, byte[] var4) throws RemoteException;

   void zza(zzdi var1, Asset var2) throws RemoteException;

   void zzb(zzdi var1) throws RemoteException;

   void zzc(zzdi var1) throws RemoteException;

   void zza(zzdi var1, String var2, int var3) throws RemoteException;

   void zza(zzdi var1, int var2) throws RemoteException;

   void zza(zzdi var1, String var2) throws RemoteException;

   void zzb(zzdi var1, String var2) throws RemoteException;

   void zza(zzdi var1, zzd var2) throws RemoteException;

   void zza(zzdi var1, zzeo var2) throws RemoteException;

   void zza(zzdi var1, String var2, String var3) throws RemoteException;

   void zzc(zzdi var1, String var2) throws RemoteException;

   void zzb(zzdi var1, String var2, int var3) throws RemoteException;

   void zza(zzdi var1, zzdg var2, String var3) throws RemoteException;

   void zzb(zzdi var1, zzdg var2, String var3) throws RemoteException;

   void zza(zzdi var1, String var2, ParcelFileDescriptor var3) throws RemoteException;

   void zza(zzdi var1, String var2, ParcelFileDescriptor var3, long var4, long var6) throws RemoteException;
}
