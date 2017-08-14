package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.GamesClientImpl;

final class zzs extends zzv {
   // $FF: synthetic field
   private String zzbaT;
   // $FF: synthetic field
   private int zzbaU;

   zzs(zzp var1, GoogleApiClient var2, String var3, int var4) {
      this.zzbaT = var3;
      this.zzbaU = var4;
      super(var2, (zzq)null);
   }

   // $FF: synthetic method
   public final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      GamesClientImpl var3 = (GamesClientImpl)var1;
      var3.zzn(this.zzbaT, this.zzbaU);
   }
}
