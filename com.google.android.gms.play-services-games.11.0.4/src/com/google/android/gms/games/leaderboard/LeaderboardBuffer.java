package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzg;

public final class LeaderboardBuffer extends zzg {
   public LeaderboardBuffer(DataHolder var1) {
      super(var1);
   }

   protected final String zzqS() {
      return "external_leaderboard_id";
   }

   // $FF: synthetic method
   protected final Object zzi(int var1, int var2) {
      return new LeaderboardRef(this.zzaCX, var1, var2);
   }
}
