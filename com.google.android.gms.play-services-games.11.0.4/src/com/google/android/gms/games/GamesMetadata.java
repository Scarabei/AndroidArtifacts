package com.google.android.gms.games;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;

public interface GamesMetadata {
   Game getCurrentGame(GoogleApiClient var1);

   PendingResult loadGame(GoogleApiClient var1);

   public interface LoadGamesResult extends Releasable, Result {
      GameBuffer getGames();
   }
}
