package com.google.android.gms.internal;

import com.google.android.gms.cast.games.GameManagerClient;
import com.google.android.gms.common.api.Status;

final class zzaxj implements GameManagerClient.GameManagerInstanceResult {
   private final Status mStatus;
   private final GameManagerClient zzaxl;

   zzaxj(Status var1, GameManagerClient var2) {
      this.mStatus = var1;
      this.zzaxl = var2;
   }

   public final Status getStatus() {
      return this.mStatus;
   }

   public final GameManagerClient getGameManagerClient() {
      return this.zzaxl;
   }
}
