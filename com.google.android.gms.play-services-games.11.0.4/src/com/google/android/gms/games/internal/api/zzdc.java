package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.internal.zzbaz;

final class zzdc extends zzdj {
   // $FF: synthetic field
   private String zzbbw;
   // $FF: synthetic field
   private String zzbbz;

   zzdc(zzcu var1, GoogleApiClient var2, String var3, String var4) {
      this.zzbbw = var3;
      this.zzbbz = var4;
      super(var2, (zzcv)null);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      GamesClientImpl var3 = (GamesClientImpl)var1;
      var3.zza((zzbaz)this, (String)this.zzbbw, (String)this.zzbbz);
   }
}
