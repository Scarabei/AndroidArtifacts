package com.google.android.gms.games.internal.api;

import android.os.Bundle;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.multiplayer.turnbased.LoadMatchesResponse;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;

final class zzdo implements TurnBasedMultiplayer.LoadMatchesResult {
   // $FF: synthetic field
   private Status zzakB;

   zzdo(zzdn var1, Status var2) {
      this.zzakB = var2;
      super();
   }

   public final Status getStatus() {
      return this.zzakB;
   }

   public final void release() {
   }

   public final LoadMatchesResponse getMatches() {
      return new LoadMatchesResponse(new Bundle());
   }
}
