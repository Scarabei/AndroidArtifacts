package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.PlayerBuffer;
import com.google.android.gms.games.Players;

final class zzbg implements Players.LoadPlayersResult {
   // $FF: synthetic field
   private Status zzakB;

   zzbg(zzbf var1, Status var2) {
      this.zzakB = var2;
      super();
   }

   public final Status getStatus() {
      return this.zzakB;
   }

   public final void release() {
   }

   public final PlayerBuffer getPlayers() {
      return new PlayerBuffer(DataHolder.zzau(14));
   }
}
