package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.GamesClientImpl;

final class zzbl extends zzbq {
   // $FF: synthetic field
   private boolean zzbaP;
   // $FF: synthetic field
   private String[] zzbbi;

   zzbl(zzbh var1, GoogleApiClient var2, boolean var3, String[] var4) {
      this.zzbaP = var3;
      this.zzbbi = var4;
      super(var2, (zzbi)null);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      GamesClientImpl var3 = (GamesClientImpl)var1;
      var3.zzb(this, this.zzbaP, this.zzbbi);
   }
}
