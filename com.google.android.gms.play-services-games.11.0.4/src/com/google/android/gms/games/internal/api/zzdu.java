package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.GamesClientImpl;

final class zzdu extends zzdv {
   // $FF: synthetic field
   private int zzbbE;

   zzdu(zzdr var1, GoogleApiClient var2, int var3) {
      this.zzbbE = var3;
      super(var2, (zzds)null);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      GamesClientImpl var3 = (GamesClientImpl)var1;
      var3.zzb(this, this.zzbbE);
   }
}
