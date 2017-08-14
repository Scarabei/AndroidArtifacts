package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;

final class zzdg implements TurnBasedMultiplayer.CancelMatchResult {
   // $FF: synthetic field
   private Status zzakB;
   // $FF: synthetic field
   private zzdf zzbbD;

   zzdg(zzdf var1, Status var2) {
      this.zzbbD = var1;
      this.zzakB = var2;
      super();
   }

   public final Status getStatus() {
      return this.zzakB;
   }

   public final String getMatchId() {
      return zzdf.zza(this.zzbbD);
   }
}
