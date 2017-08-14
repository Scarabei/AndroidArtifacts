package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;

final class zzdk implements TurnBasedMultiplayer.LeaveMatchResult {
   // $FF: synthetic field
   private Status zzakB;

   zzdk(zzdj var1, Status var2) {
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
