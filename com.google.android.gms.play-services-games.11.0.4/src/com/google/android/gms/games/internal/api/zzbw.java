package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.GamesClientImpl;

final class zzbw extends zzbx {
   // $FF: synthetic field
   private int zzbbk;
   // $FF: synthetic field
   private int zzbbl;
   // $FF: synthetic field
   private int zzbaV;

   zzbw(zzbt var1, GoogleApiClient var2, int var3, int var4, int var5) {
      this.zzbbk = var3;
      this.zzbbl = var4;
      this.zzbaV = var5;
      super(var2, (zzbu)null);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      GamesClientImpl var3 = (GamesClientImpl)var1;
      var3.zza(this, this.zzbbk, this.zzbbl, this.zzbaV);
   }
}
