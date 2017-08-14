package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.stats.Stats;

public final class zzcq implements Stats {
   public final PendingResult loadPlayerStats(GoogleApiClient var1, boolean var2) {
      return var1.zzd(new zzcr(this, var1, var2));
   }
}
