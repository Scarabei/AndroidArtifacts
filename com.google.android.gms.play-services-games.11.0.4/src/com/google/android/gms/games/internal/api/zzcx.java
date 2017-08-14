package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.internal.zzbaz;

final class zzcx extends zzdh {
   // $FF: synthetic field
   private String zzbbw;

   zzcx(zzcu var1, GoogleApiClient var2, String var3) {
      this.zzbbw = var3;
      super(var2, (zzcv)null);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      GamesClientImpl var3 = (GamesClientImpl)var1;
      var3.zzc((zzbaz)this, this.zzbbw);
   }
}
