package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.games.leaderboard.Leaderboards;

final class zzaq implements Leaderboards.LoadPlayerScoreResult {
   // $FF: synthetic field
   private Status zzakB;

   zzaq(zzap var1, Status var2) {
      this.zzakB = var2;
      super();
   }

   public final Status getStatus() {
      return this.zzakB;
   }

   public final LeaderboardScore getScore() {
      return null;
   }
}
