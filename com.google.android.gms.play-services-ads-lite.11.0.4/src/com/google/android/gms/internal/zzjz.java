package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface zzjz extends IInterface {
   IObjectWrapper zzal() throws RemoteException;

   void destroy() throws RemoteException;

   boolean isReady() throws RemoteException;

   boolean zza(zzir var1) throws RemoteException;

   void pause() throws RemoteException;

   void resume() throws RemoteException;

   void zza(zzjo var1) throws RemoteException;

   void zza(zzke var1) throws RemoteException;

   void showInterstitial() throws RemoteException;

   void stopLoading() throws RemoteException;

   void zzao() throws RemoteException;

   zziv zzam() throws RemoteException;

   void zza(zziv var1) throws RemoteException;

   void zza(zzxg var1) throws RemoteException;

   void zza(zzxo var1, String var2) throws RemoteException;

   String getMediationAdapterClassName() throws RemoteException;

   void zza(zznh var1) throws RemoteException;

   void zza(zzjl var1) throws RemoteException;

   void zza(zzkk var1) throws RemoteException;

   void setManualImpressionsEnabled(boolean var1) throws RemoteException;

   boolean isLoading() throws RemoteException;

   void zza(zzadd var1) throws RemoteException;

   void setUserId(String var1) throws RemoteException;

   zzks getVideoController() throws RemoteException;

   void zza(zzlx var1) throws RemoteException;

   void zza(zzky var1) throws RemoteException;

   String getAdUnitId() throws RemoteException;

   zzke zzax() throws RemoteException;

   zzjo zzay() throws RemoteException;

   void setImmersiveMode(boolean var1) throws RemoteException;

   String zzaI() throws RemoteException;
}
