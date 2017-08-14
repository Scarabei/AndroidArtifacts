package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.GamesClientImpl;

final class zzcd extends zzcn {
   // $FF: synthetic field
   private String zzbbm;
   // $FF: synthetic field
   private boolean zzbbn;
   // $FF: synthetic field
   private int zzbbo;

   zzcd(zzcb var1, GoogleApiClient var2, String var3, boolean var4, int var5) {
      this.zzbbm = var3;
      this.zzbbn = var4;
      this.zzbbo = var5;
      super(var2, (zzcc)null);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      GamesClientImpl var3 = (GamesClientImpl)var1;
      var3.zza(this, this.zzbbm, this.zzbbn, this.zzbbo);
   }
}
