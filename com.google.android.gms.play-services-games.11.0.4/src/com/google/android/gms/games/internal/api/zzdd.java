package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.GamesClientImpl;

final class zzdd extends zzdf {
   // $FF: synthetic field
   private String zzbbw;

   zzdd(zzcu var1, String var2, GoogleApiClient var3, String var4) {
      this.zzbbw = var4;
      super(var2, var3);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      GamesClientImpl var3 = (GamesClientImpl)var1;
      var3.zzf(this, this.zzbbw);
   }
}
