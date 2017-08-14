package com.google.android.gms.games;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;

public interface Players {
   String EXTRA_PLAYER_SEARCH_RESULTS = "player_search_results";

   String getCurrentPlayerId(GoogleApiClient var1);

   Player getCurrentPlayer(GoogleApiClient var1);

   PendingResult loadPlayer(GoogleApiClient var1, String var2);

   PendingResult loadPlayer(GoogleApiClient var1, String var2, boolean var3);

   /** @deprecated */
   @Deprecated
   PendingResult loadInvitablePlayers(GoogleApiClient var1, int var2, boolean var3);

   /** @deprecated */
   @Deprecated
   PendingResult loadMoreInvitablePlayers(GoogleApiClient var1, int var2);

   PendingResult loadRecentlyPlayedWithPlayers(GoogleApiClient var1, int var2, boolean var3);

   PendingResult loadMoreRecentlyPlayedWithPlayers(GoogleApiClient var1, int var2);

   /** @deprecated */
   @Deprecated
   PendingResult loadConnectedPlayers(GoogleApiClient var1, boolean var2);

   Intent getCompareProfileIntent(GoogleApiClient var1, Player var2);

   /** @deprecated */
   @Deprecated
   Intent getPlayerSearchIntent(GoogleApiClient var1);

   public interface LoadPlayersResult extends Releasable, Result {
      PlayerBuffer getPlayers();
   }
}
