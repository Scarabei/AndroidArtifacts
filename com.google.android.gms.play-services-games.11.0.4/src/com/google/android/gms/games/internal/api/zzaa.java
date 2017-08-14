package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.GameBuffer;
import com.google.android.gms.games.GamesMetadata;

final class zzaa implements GamesMetadata.LoadGamesResult {
   // $FF: synthetic field
   private Status zzakB;

   zzaa(zzz var1, Status var2) {
      this.zzakB = var2;
      super();
   }

   public final Status getStatus() {
      return this.zzakB;
   }

   public final void release() {
   }

   public final GameBuffer getGames() {
      return new GameBuffer(DataHolder.zzau(14));
   }
}
