package com.google.android.gms.games.internal.api;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.Players;

public final class zzax implements Players {
   public final String getCurrentPlayerId(GoogleApiClient var1) {
      return Games.zzf(var1).zzah(true);
   }

   public final Player getCurrentPlayer(GoogleApiClient var1) {
      return Games.zzf(var1).zzut();
   }

   public final PendingResult loadPlayer(GoogleApiClient var1, String var2) {
      return var1.zzd(new zzay(this, var1, var2));
   }

   public final PendingResult loadPlayer(GoogleApiClient var1, String var2, boolean var3) {
      return var1.zzd(new zzaz(this, var1, var2, var3));
   }

   public final PendingResult loadInvitablePlayers(GoogleApiClient var1, int var2, boolean var3) {
      return var1.zzd(new zzba(this, var1, var2, var3));
   }

   public final PendingResult loadMoreInvitablePlayers(GoogleApiClient var1, int var2) {
      return var1.zzd(new zzbb(this, var1, var2));
   }

   public final PendingResult loadRecentlyPlayedWithPlayers(GoogleApiClient var1, int var2, boolean var3) {
      return var1.zzd(new zzbc(this, var1, var2, var3));
   }

   public final PendingResult loadMoreRecentlyPlayedWithPlayers(GoogleApiClient var1, int var2) {
      return var1.zzd(new zzbd(this, var1, var2));
   }

   public final PendingResult loadConnectedPlayers(GoogleApiClient var1, boolean var2) {
      return var1.zzd(new zzbe(this, var1, var2));
   }

   public final Intent getCompareProfileIntent(GoogleApiClient var1, Player var2) {
      return Games.zzf(var1).zza(new PlayerEntity(var2));
   }

   public final Intent getPlayerSearchIntent(GoogleApiClient var1) {
      return Games.zzf(var1).zzuD();
   }
}
