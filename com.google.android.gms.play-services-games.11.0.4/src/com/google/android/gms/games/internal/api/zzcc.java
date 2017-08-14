package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.GamesClientImpl;

final class zzcc extends zzcl {
   // $FF: synthetic field
   private boolean zzbaP;

   zzcc(zzcb var1, GoogleApiClient var2, boolean var3) {
      this.zzbaP = var3;
      super(var2, (zzcc)null);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      GamesClientImpl var3 = (GamesClientImpl)var1;
      var3.zzf(this, this.zzbaP);
   }
}
