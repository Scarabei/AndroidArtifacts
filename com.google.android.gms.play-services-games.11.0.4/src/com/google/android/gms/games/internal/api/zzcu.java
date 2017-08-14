package com.google.android.gms.games.internal.api;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchUpdateReceivedListener;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;
import com.google.android.gms.internal.zzbdw;
import java.util.List;

public final class zzcu implements TurnBasedMultiplayer {
   public final Intent getInboxIntent(GoogleApiClient var1) {
      return Games.zzf(var1).zzux();
   }

   public final void registerMatchUpdateListener(GoogleApiClient var1, OnTurnBasedMatchUpdateReceivedListener var2) {
      GamesClientImpl var3;
      if ((var3 = Games.zza(var1, false)) != null) {
         zzbdw var4 = var1.zzp(var2);
         var3.zzb(var4);
      }

   }

   public final void unregisterMatchUpdateListener(GoogleApiClient var1) {
      GamesClientImpl var2;
      if ((var2 = Games.zza(var1, false)) != null) {
         var2.zzuA();
      }

   }

   public final Intent getSelectOpponentsIntent(GoogleApiClient var1, int var2, int var3) {
      return Games.zzf(var1).zzb(var2, var3, true);
   }

   public final Intent getSelectOpponentsIntent(GoogleApiClient var1, int var2, int var3, boolean var4) {
      return Games.zzf(var1).zzb(var2, var3, var4);
   }

   public final PendingResult createMatch(GoogleApiClient var1, TurnBasedMatchConfig var2) {
      return var1.zze(new zzcv(this, var1, var2));
   }

   public final PendingResult rematch(GoogleApiClient var1, String var2) {
      return var1.zze(new zzcx(this, var1, var2));
   }

   public final PendingResult acceptInvitation(GoogleApiClient var1, String var2) {
      return var1.zze(new zzcy(this, var1, var2));
   }

   public final void declineInvitation(GoogleApiClient var1, String var2) {
      GamesClientImpl var3;
      if ((var3 = Games.zza(var1, false)) != null) {
         var3.zzp(var2, 1);
      }

   }

   public final void dismissInvitation(GoogleApiClient var1, String var2) {
      GamesClientImpl var3;
      if ((var3 = Games.zza(var1, false)) != null) {
         var3.zzo(var2, 1);
      }

   }

   public final int getMaxMatchDataSize(GoogleApiClient var1) {
      return Games.zzf(var1).zzuG();
   }

   public final PendingResult takeTurn(GoogleApiClient var1, String var2, byte[] var3, String var4) {
      return this.takeTurn(var1, var2, var3, var4, (ParticipantResult[])null);
   }

   public final PendingResult takeTurn(GoogleApiClient var1, String var2, byte[] var3, String var4, ParticipantResult... var5) {
      return var1.zze(new zzcz(this, var1, var2, var3, var4, var5));
   }

   public final PendingResult takeTurn(GoogleApiClient var1, String var2, byte[] var3, String var4, List var5) {
      ParticipantResult[] var6 = var5 == null ? null : (ParticipantResult[])var5.toArray(new ParticipantResult[var5.size()]);
      return this.takeTurn(var1, var2, var3, var4, var6);
   }

   public final PendingResult finishMatch(GoogleApiClient var1, String var2) {
      return this.finishMatch(var1, var2, (byte[])null, (ParticipantResult[])null);
   }

   public final PendingResult finishMatch(GoogleApiClient var1, String var2, byte[] var3, ParticipantResult... var4) {
      return var1.zze(new zzda(this, var1, var2, var3, var4));
   }

   public final PendingResult finishMatch(GoogleApiClient var1, String var2, byte[] var3, List var4) {
      ParticipantResult[] var5 = var4 == null ? null : (ParticipantResult[])var4.toArray(new ParticipantResult[var4.size()]);
      return this.finishMatch(var1, var2, var3, var5);
   }

   public final PendingResult leaveMatch(GoogleApiClient var1, String var2) {
      return var1.zze(new zzdb(this, var1, var2));
   }

   public final PendingResult leaveMatchDuringTurn(GoogleApiClient var1, String var2, String var3) {
      return var1.zze(new zzdc(this, var1, var2, var3));
   }

   public final PendingResult cancelMatch(GoogleApiClient var1, String var2) {
      return var1.zze(new zzdd(this, var2, var1, var2));
   }

   public final void dismissMatch(GoogleApiClient var1, String var2) {
      GamesClientImpl var3;
      if ((var3 = Games.zza(var1, false)) != null) {
         var3.zzdj(var2);
      }

   }

   public final PendingResult loadMatchesByStatus(GoogleApiClient var1, int[] var2) {
      return this.loadMatchesByStatus(var1, 0, var2);
   }

   public final PendingResult loadMatchesByStatus(GoogleApiClient var1, int var2, int[] var3) {
      return var1.zzd(new zzde(this, var1, var2, var3));
   }

   public final PendingResult loadMatch(GoogleApiClient var1, String var2) {
      return var1.zzd(new zzcw(this, var1, var2));
   }
}
