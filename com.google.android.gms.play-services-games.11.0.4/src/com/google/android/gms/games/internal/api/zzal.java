package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;

final class zzal extends zzar {
   // $FF: synthetic field
   private LeaderboardScoreBuffer zzbba;
   // $FF: synthetic field
   private int zzbaZ;
   // $FF: synthetic field
   private int zzbbb;

   zzal(zzaf var1, GoogleApiClient var2, LeaderboardScoreBuffer var3, int var4, int var5) {
      this.zzbba = var3;
      this.zzbaZ = var4;
      this.zzbbb = var5;
      super(var2, (zzag)null);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      GamesClientImpl var3 = (GamesClientImpl)var1;
      var3.zza(this, this.zzbba, this.zzbaZ, this.zzbbb);
   }
}
