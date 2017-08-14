package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzlr extends zzacz {
   private zzadd zzBu;

   public final void zza(zzadj var1) throws RemoteException {
      zzajc.e("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
      zzaiy.zzaaH.post(new zzls(this));
   }

   public final void show() throws RemoteException {
   }

   public final void zza(zzadd var1) throws RemoteException {
      this.zzBu = var1;
   }

   public final void setUserId(String var1) throws RemoteException {
   }

   public final boolean isLoaded() throws RemoteException {
      return false;
   }

   public final void pause() throws RemoteException {
   }

   public final void resume() throws RemoteException {
   }

   public final void destroy() throws RemoteException {
   }

   public final void zzf(IObjectWrapper var1) throws RemoteException {
   }

   public final void zzg(IObjectWrapper var1) throws RemoteException {
   }

   public final void zzh(IObjectWrapper var1) throws RemoteException {
   }

   public final String getMediationAdapterClassName() throws RemoteException {
      return null;
   }

   public final void setImmersiveMode(boolean var1) throws RemoteException {
   }

   // $FF: synthetic method
   static zzadd zza(zzlr var0) {
      return var0.zzBu;
   }
}
