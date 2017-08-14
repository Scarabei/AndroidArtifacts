package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.GamesClientImpl;

final class zzaj extends zzar {
   // $FF: synthetic field
   private String zzbaW;
   // $FF: synthetic field
   private int zzbaX;
   // $FF: synthetic field
   private int zzbaY;
   // $FF: synthetic field
   private int zzbaZ;
   // $FF: synthetic field
   private boolean zzbaP;

   zzaj(zzaf var1, GoogleApiClient var2, String var3, int var4, int var5, int var6, boolean var7) {
      this.zzbaW = var3;
      this.zzbaX = var4;
      this.zzbaY = var5;
      this.zzbaZ = var6;
      this.zzbaP = var7;
      super(var2, (zzag)null);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      GamesClientImpl var3 = (GamesClientImpl)var1;
      var3.zza(this, this.zzbaW, this.zzbaX, this.zzbaY, this.zzbaZ, this.zzbaP);
   }
}
