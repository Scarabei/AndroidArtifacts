package com.google.android.gms.games.internal.api;

import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.internal.zzbaz;

public final class zzaf implements Leaderboards {
   public final Intent getAllLeaderboardsIntent(GoogleApiClient var1) {
      return Games.zzf(var1).zzuv();
   }

   public final Intent getLeaderboardIntent(GoogleApiClient var1, String var2) {
      return this.getLeaderboardIntent(var1, var2, -1);
   }

   public final Intent getLeaderboardIntent(GoogleApiClient var1, String var2, int var3) {
      return this.getLeaderboardIntent(var1, var2, var3, -1);
   }

   public final Intent getLeaderboardIntent(GoogleApiClient var1, String var2, int var3, int var4) {
      return Games.zzf(var1).zzj(var2, var3, var4);
   }

   public final PendingResult loadLeaderboardMetadata(GoogleApiClient var1, boolean var2) {
      return var1.zzd(new zzag(this, var1, var2));
   }

   public final PendingResult loadLeaderboardMetadata(GoogleApiClient var1, String var2, boolean var3) {
      return var1.zzd(new zzah(this, var1, var2, var3));
   }

   public final PendingResult loadCurrentPlayerLeaderboardScore(GoogleApiClient var1, String var2, int var3, int var4) {
      return var1.zzd(new zzai(this, var1, var2, var3, var4));
   }

   public final PendingResult loadTopScores(GoogleApiClient var1, String var2, int var3, int var4, int var5) {
      return this.loadTopScores(var1, var2, var3, var4, var5, false);
   }

   public final PendingResult loadTopScores(GoogleApiClient var1, String var2, int var3, int var4, int var5, boolean var6) {
      return var1.zzd(new zzaj(this, var1, var2, var3, var4, var5, var6));
   }

   public final PendingResult loadPlayerCenteredScores(GoogleApiClient var1, String var2, int var3, int var4, int var5) {
      return this.loadPlayerCenteredScores(var1, var2, var3, var4, var5, false);
   }

   public final PendingResult loadPlayerCenteredScores(GoogleApiClient var1, String var2, int var3, int var4, int var5, boolean var6) {
      return var1.zzd(new zzak(this, var1, var2, var3, var4, var5, var6));
   }

   public final PendingResult loadMoreScores(GoogleApiClient var1, LeaderboardScoreBuffer var2, int var3, int var4) {
      return var1.zzd(new zzal(this, var1, var2, var3, var4));
   }

   public final void submitScore(GoogleApiClient var1, String var2, long var3) {
      this.submitScore(var1, var2, var3, (String)null);
   }

   public final void submitScore(GoogleApiClient var1, String var2, long var3, String var5) {
      GamesClientImpl var6;
      if ((var6 = Games.zza(var1, false)) != null) {
         try {
            var6.zza((zzbaz)null, var2, var3, var5);
            return;
         } catch (RemoteException var7) {
            com.google.android.gms.games.internal.zze.zzy("LeaderboardsImpl", "service died");
         }
      }

   }

   public final PendingResult submitScoreImmediate(GoogleApiClient var1, String var2, long var3) {
      return this.submitScoreImmediate(var1, var2, var3, (String)null);
   }

   public final PendingResult submitScoreImmediate(GoogleApiClient var1, String var2, long var3, String var5) {
      return var1.zze(new zzam(this, var1, var2, var3, var5));
   }
}
