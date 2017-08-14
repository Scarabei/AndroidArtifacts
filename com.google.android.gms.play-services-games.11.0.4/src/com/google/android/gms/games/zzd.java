package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.internal.zzbaz;

final class zzd extends Games.zzc {
   // $FF: synthetic field
   private String zzaYt;

   zzd(GoogleApiClient var1, String var2) {
      this.zzaYt = var2;
      super(var1, (zzb)null);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      GamesClientImpl var3 = (GamesClientImpl)var1;
      var3.zzb((String)this.zzaYt, (zzbaz)this);
   }
}
