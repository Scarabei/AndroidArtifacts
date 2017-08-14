package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.GamesClientImpl;

final class zzcr extends zzcs {
   // $FF: synthetic field
   private boolean zzbaP;

   zzcr(zzcq var1, GoogleApiClient var2, boolean var3) {
      this.zzbaP = var3;
      super(var2, (zzcr)null);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      GamesClientImpl var3 = (GamesClientImpl)var1;
      var3.zze(this, this.zzbaP);
   }
}
