package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.GamesClientImpl;

final class zzaz extends zzbf {
   // $FF: synthetic field
   private String zzaxg;
   // $FF: synthetic field
   private boolean zzbaP;

   zzaz(zzax var1, GoogleApiClient var2, String var3, boolean var4) {
      this.zzaxg = var3;
      this.zzbaP = var4;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      GamesClientImpl var3 = (GamesClientImpl)var1;
      var3.zza(this, this.zzaxg, this.zzbaP);
   }
}
