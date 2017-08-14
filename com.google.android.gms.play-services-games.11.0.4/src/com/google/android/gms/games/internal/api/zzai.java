package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.GamesClientImpl;

final class zzai extends zzap {
   // $FF: synthetic field
   private String zzbaW;
   // $FF: synthetic field
   private int zzbaX;
   // $FF: synthetic field
   private int zzbaY;

   zzai(zzaf var1, GoogleApiClient var2, String var3, int var4, int var5) {
      this.zzbaW = var3;
      this.zzbaX = var4;
      this.zzbaY = var5;
      super(var2, (zzag)null);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      GamesClientImpl var3 = (GamesClientImpl)var1;
      var3.zza(this, (String)null, this.zzbaW, this.zzbaX, this.zzbaY);
   }
}
