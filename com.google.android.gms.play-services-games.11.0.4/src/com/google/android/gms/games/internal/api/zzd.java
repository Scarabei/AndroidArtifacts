package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.internal.zzbaz;

final class zzd extends zzm {
   // $FF: synthetic field
   private String val$id;

   zzd(zza var1, String var2, GoogleApiClient var3, String var4) {
      this.val$id = var4;
      super(var2, var3);
   }

   // $FF: synthetic method
   public final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      GamesClientImpl var3 = (GamesClientImpl)var1;
      var3.zza((zzbaz)this, (String)this.val$id);
   }
}
