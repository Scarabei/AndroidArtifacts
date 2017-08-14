package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.GamesClientImpl;

final class zzh extends zzm {
   // $FF: synthetic field
   private String val$id;
   // $FF: synthetic field
   private int zzbaQ;

   zzh(zza var1, String var2, GoogleApiClient var3, String var4, int var5) {
      this.val$id = var4;
      this.zzbaQ = var5;
      super(var2, var3);
   }

   // $FF: synthetic method
   public final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      GamesClientImpl var3 = (GamesClientImpl)var1;
      var3.zza(this, this.val$id, this.zzbaQ);
   }
}
