package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesMetadata;

public final class zzx implements GamesMetadata {
   public final Game getCurrentGame(GoogleApiClient var1) {
      return Games.zzf(var1).zzuu();
   }

   public final PendingResult loadGame(GoogleApiClient var1) {
      return var1.zzd(new zzy(this, var1));
   }
}
