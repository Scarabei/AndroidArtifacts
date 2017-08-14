package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;
import com.google.android.gms.internal.zzbaz;

final class zzcv extends zzdh {
   // $FF: synthetic field
   private TurnBasedMatchConfig zzbbv;

   zzcv(zzcu var1, GoogleApiClient var2, TurnBasedMatchConfig var3) {
      this.zzbbv = var3;
      super(var2, (zzcv)null);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      GamesClientImpl var3 = (GamesClientImpl)var1;
      var3.zza((zzbaz)this, (TurnBasedMatchConfig)this.zzbbv);
   }
}
