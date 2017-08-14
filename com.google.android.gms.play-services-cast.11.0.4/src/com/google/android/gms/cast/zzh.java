package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.zzaxx;

final class zzh extends Cast.zza {
   // $FF: synthetic field
   private String zzaoR;
   // $FF: synthetic field
   private LaunchOptions zzaoS;

   zzh(Cast.CastApi.zza var1, GoogleApiClient var2, String var3, LaunchOptions var4) {
      this.zzaoR = var3;
      this.zzaoS = var4;
      super(var2);
   }

   public final void zza(zzaxx var1) throws RemoteException {
      try {
         var1.zza(this.zzaoR, (LaunchOptions)this.zzaoS, this);
      } catch (IllegalStateException var2) {
         this.zzad(2001);
      }
   }
}
