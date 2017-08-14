package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.GamesClientImpl;

final class zzba extends zzbf {
   // $FF: synthetic field
   private int zzbbe;
   // $FF: synthetic field
   private boolean zzbaP;

   zzba(zzax var1, GoogleApiClient var2, int var3, boolean var4) {
      this.zzbbe = var3;
      this.zzbaP = var4;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      GamesClientImpl var3 = (GamesClientImpl)var1;
      var3.zza(this, this.zzbbe, false, this.zzbaP);
   }
}
