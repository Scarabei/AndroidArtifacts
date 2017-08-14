package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.leaderboard.LeaderboardBuffer;
import com.google.android.gms.games.leaderboard.Leaderboards;

final class zzao implements Leaderboards.LeaderboardMetadataResult {
   // $FF: synthetic field
   private Status zzakB;

   zzao(zzan var1, Status var2) {
      this.zzakB = var2;
      super();
   }

   public final Status getStatus() {
      return this.zzakB;
   }

   public final void release() {
   }

   public final LeaderboardBuffer getLeaderboards() {
      return new LeaderboardBuffer(DataHolder.zzau(14));
   }
}
