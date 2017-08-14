package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.zzaxx;

final class zzg extends Cast.zza {
   // $FF: synthetic field
   private String zzaoR;

   zzg(Cast.CastApi.zza var1, GoogleApiClient var2, String var3) {
      this.zzaoR = var3;
      super(var2);
   }

   public final void zza(zzaxx var1) throws RemoteException {
      try {
         String var3 = this.zzaoR;
         LaunchOptions var5;
         (var5 = new LaunchOptions()).setRelaunchIfRunning(false);
         var1.zza(var3, (LaunchOptions)var5, this);
      } catch (IllegalStateException var6) {
         this.zzad(2001);
      }
   }
}
