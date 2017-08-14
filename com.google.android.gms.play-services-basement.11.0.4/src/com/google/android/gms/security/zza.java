package com.google.android.gms.security;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.zze;

final class zza extends AsyncTask {
   // $FF: synthetic field
   private Context zztF;
   // $FF: synthetic field
   private ProviderInstaller.ProviderInstallListener zzbCI;

   zza(Context var1, ProviderInstaller.ProviderInstallListener var2) {
      this.zztF = var1;
      this.zzbCI = var2;
      super();
   }

   private final Integer zzb(Void... var1) {
      try {
         ProviderInstaller.installIfNeeded(this.zztF);
      } catch (GooglePlayServicesRepairableException var2) {
         return var2.getConnectionStatusCode();
      } catch (GooglePlayServicesNotAvailableException var3) {
         return var3.errorCode;
      }

      return Integer.valueOf(0);
   }

   // $FF: synthetic method
   protected final void onPostExecute(Object var1) {
      Integer var3 = (Integer)var1;
      if (var3.intValue() == 0) {
         this.zzbCI.onProviderInstalled();
      } else {
         ProviderInstaller.zzAo();
         Intent var4 = zze.zza(this.zztF, var3.intValue(), "pi");
         this.zzbCI.onProviderInstallFailed(var3.intValue(), var4);
      }
   }

   // $FF: synthetic method
   protected final Object doInBackground(Object[] var1) {
      return this.zzb((Void[])var1);
   }
}
