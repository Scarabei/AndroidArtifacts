package com.google.android.gms.games.stats;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;

public interface Stats {
   PendingResult loadPlayerStats(GoogleApiClient var1, boolean var2);

   public interface LoadPlayerStatsResult extends Releasable, Result {
      PlayerStats getPlayerStats();
   }
}
