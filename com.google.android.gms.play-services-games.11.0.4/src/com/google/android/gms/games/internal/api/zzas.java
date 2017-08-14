package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.leaderboard.Leaderboard;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.games.leaderboard.Leaderboards;

final class zzas implements Leaderboards.LoadScoresResult {
   // $FF: synthetic field
   private Status zzakB;

   zzas(zzar var1, Status var2) {
      this.zzakB = var2;
      super();
   }

   public final Status getStatus() {
      return this.zzakB;
   }

   public final void release() {
   }

   public final Leaderboard getLeaderboard() {
      return null;
   }

   public final LeaderboardScoreBuffer getScores() {
      return new LeaderboardScoreBuffer(DataHolder.zzau(14));
   }
}
