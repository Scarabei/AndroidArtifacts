package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.GamesClientImpl;

final class zzq extends zzt {
   // $FF: synthetic field
   private boolean zzbaP;
   // $FF: synthetic field
   private String[] zzbaS;

   zzq(zzp var1, GoogleApiClient var2, boolean var3, String[] var4) {
      this.zzbaP = var3;
      this.zzbaS = var4;
      super(var2, (zzq)null);
   }

   // $FF: synthetic method
   public final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      GamesClientImpl var3 = (GamesClientImpl)var1;
      var3.zza(this, this.zzbaP, this.zzbaS);
   }
}
