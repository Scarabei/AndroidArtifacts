package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.GamesClientImpl;

final class zzb extends zzk {
   // $FF: synthetic field
   private boolean zzbaP;

   zzb(zza var1, GoogleApiClient var2, boolean var3) {
      this.zzbaP = var3;
      super(var2, (zzb)null);
   }

   // $FF: synthetic method
   public final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      GamesClientImpl var3 = (GamesClientImpl)var1;
      var3.zzc(this, this.zzbaP);
   }
}
