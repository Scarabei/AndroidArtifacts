package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.GamesClientImpl;

final class zzam extends zzat {
   // $FF: synthetic field
   private String zzbaW;
   // $FF: synthetic field
   private long zzbbc;
   // $FF: synthetic field
   private String zzbbd;

   zzam(zzaf var1, GoogleApiClient var2, String var3, long var4, String var6) {
      this.zzbaW = var3;
      this.zzbbc = var4;
      this.zzbbd = var6;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      GamesClientImpl var3 = (GamesClientImpl)var1;
      var3.zza(this, this.zzbaW, this.zzbbc, this.zzbbd);
   }
}
