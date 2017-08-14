package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;

public final class LeaderboardScoreBuffer extends AbstractDataBuffer {
   private final zza zzbcQ;

   public LeaderboardScoreBuffer(DataHolder var1) {
      super(var1);
      this.zzbcQ = new zza(var1.zzqN());
   }

   public final LeaderboardScore get(int var1) {
      return new LeaderboardScoreRef(this.zzaCX, var1);
   }

   public final zza zzvn() {
      return this.zzbcQ;
   }
}
