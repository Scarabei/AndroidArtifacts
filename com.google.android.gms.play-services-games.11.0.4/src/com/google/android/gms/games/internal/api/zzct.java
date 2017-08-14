package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.stats.PlayerStats;
import com.google.android.gms.games.stats.Stats;

final class zzct implements Stats.LoadPlayerStatsResult {
   // $FF: synthetic field
   private Status zzakB;

   zzct(zzcs var1, Status var2) {
      this.zzakB = var2;
      super();
   }

   public final void release() {
   }

   public final Status getStatus() {
      return this.zzakB;
   }

   public final PlayerStats getPlayerStats() {
      return null;
   }
}
