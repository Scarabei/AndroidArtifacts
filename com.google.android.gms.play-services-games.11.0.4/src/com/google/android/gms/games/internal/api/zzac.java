package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.internal.zzbaz;

final class zzac extends zzad {
   // $FF: synthetic field
   private int zzbaV;

   zzac(zzab var1, GoogleApiClient var2, int var3) {
      this.zzbaV = var3;
      super(var2, (zzac)null);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      GamesClientImpl var3 = (GamesClientImpl)var1;
      var3.zza((zzbaz)this, this.zzbaV);
   }
}
