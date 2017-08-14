package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public interface zzut extends IInterface {
   void zza(IObjectWrapper var1, zziv var2, zzir var3, String var4, zzuw var5) throws RemoteException;

   IObjectWrapper getView() throws RemoteException;

   void zza(IObjectWrapper var1, zzir var2, String var3, zzuw var4) throws RemoteException;

   void showInterstitial() throws RemoteException;

   void destroy() throws RemoteException;

   void zza(IObjectWrapper var1, zziv var2, zzir var3, String var4, String var5, zzuw var6) throws RemoteException;

   void zza(IObjectWrapper var1, zzir var2, String var3, String var4, zzuw var5) throws RemoteException;

   void pause() throws RemoteException;

   void resume() throws RemoteException;

   void zza(IObjectWrapper var1, zzir var2, String var3, zzaea var4, String var5) throws RemoteException;

   void zzc(zzir var1, String var2) throws RemoteException;

   void showVideo() throws RemoteException;

   boolean isInitialized() throws RemoteException;

   void zza(IObjectWrapper var1, zzir var2, String var3, String var4, zzuw var5, zzon var6, List var7) throws RemoteException;

   zzvc zzfq() throws RemoteException;

   zzvf zzfr() throws RemoteException;

   Bundle zzfs() throws RemoteException;

   Bundle getInterstitialAdapterInfo() throws RemoteException;

   Bundle zzft() throws RemoteException;

   void zza(zzir var1, String var2, String var3) throws RemoteException;

   void zzk(IObjectWrapper var1) throws RemoteException;

   boolean zzfu() throws RemoteException;

   void zza(IObjectWrapper var1, zzaea var2, List var3) throws RemoteException;

   zzpj zzfv() throws RemoteException;

   void setImmersiveMode(boolean var1) throws RemoteException;

   zzks getVideoController() throws RemoteException;
}
