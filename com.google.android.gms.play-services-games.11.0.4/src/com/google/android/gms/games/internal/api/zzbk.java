package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.GamesClientImpl;

final class zzbk extends zzbq {
   // $FF: synthetic field
   private int[] zzbbh;
   // $FF: synthetic field
   private int zzbaV;
   // $FF: synthetic field
   private boolean zzbaP;

   zzbk(zzbh var1, GoogleApiClient var2, int[] var3, int var4, boolean var5) {
      this.zzbbh = var3;
      this.zzbaV = var4;
      this.zzbaP = var5;
      super(var2, (zzbi)null);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      GamesClientImpl var3 = (GamesClientImpl)var1;
      var3.zza(this, this.zzbbh, this.zzbaV, this.zzbaP);
   }
}
