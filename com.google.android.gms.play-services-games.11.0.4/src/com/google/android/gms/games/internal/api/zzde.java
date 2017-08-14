package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.GamesClientImpl;

final class zzde extends zzdn {
   // $FF: synthetic field
   private int zzbbB;
   // $FF: synthetic field
   private int[] zzbbC;

   zzde(zzcu var1, GoogleApiClient var2, int var3, int[] var4) {
      this.zzbbB = var3;
      this.zzbbC = var4;
      super(var2, (zzcv)null);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      GamesClientImpl var3 = (GamesClientImpl)var1;
      var3.zza(this, this.zzbbB, this.zzbbC);
   }
}
