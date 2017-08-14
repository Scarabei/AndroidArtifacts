package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;

final class zzdm implements TurnBasedMultiplayer.LoadMatchResult {
   // $FF: synthetic field
   private Status zzakB;

   zzdm(zzdl var1, Status var2) {
      this.zzakB = var2;
      super();
   }

   public final Status getStatus() {
      return this.zzakB;
   }

   public final TurnBasedMatch getMatch() {
      return null;
   }
}
