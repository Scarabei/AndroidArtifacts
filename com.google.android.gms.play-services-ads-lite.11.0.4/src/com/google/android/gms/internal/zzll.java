package com.google.android.gms.internal;

import android.os.RemoteException;

final class zzll extends zzjs {
   // $FF: synthetic field
   final zzlj zzBr;

   private zzll(zzlj var1) {
      this.zzBr = var1;
   }

   public final boolean isLoading() throws RemoteException {
      return false;
   }

   public final String getMediationAdapterClassName() throws RemoteException {
      return null;
   }

   public final String zzaI() throws RemoteException {
      return null;
   }

   public final void zzc(zzir var1) throws RemoteException {
      zzajc.e("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
      zzaiy.zzaaH.post(new zzlm(this));
   }

   // $FF: synthetic method
   zzll(zzlj var1, zzlk var2) {
      this(var1);
   }
}
