package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameRef;
import java.util.ArrayList;

public final class LeaderboardRef extends com.google.android.gms.common.data.zzc implements Leaderboard {
   private final int zzbcP;
   private final Game zzbcO;

   LeaderboardRef(DataHolder var1, int var2, int var3) {
      super(var1, var2);
      this.zzbcP = var3;
      this.zzbcO = new GameRef(var1, var2);
   }

   public final String getLeaderboardId() {
      return this.getString("external_leaderboard_id");
   }

   public final String getDisplayName() {
      return this.getString("name");
   }

   public final void getDisplayName(CharArrayBuffer var1) {
      this.zza("name", var1);
   }

   public final Uri getIconImageUri() {
      return this.zzcw("board_icon_image_uri");
   }

   public final String getIconImageUrl() {
      return this.getString("board_icon_image_url");
   }

   public final int getScoreOrder() {
      return this.getInteger("score_order");
   }

   public final ArrayList getVariants() {
      ArrayList var1 = new ArrayList(this.zzbcP);

      for(int var2 = 0; var2 < this.zzbcP; ++var2) {
         var1.add(new zzc(this.zzaCX, this.zzaFx + var2));
      }

      return var1;
   }

   public final Game getGame() {
      return this.zzbcO;
   }

   public final int hashCode() {
      return LeaderboardEntity.zza(this);
   }

   public final boolean equals(Object var1) {
      return LeaderboardEntity.zza(this, var1);
   }

   public final String toString() {
      return LeaderboardEntity.zzb(this);
   }

   // $FF: synthetic method
   public final Object freeze() {
      return new LeaderboardEntity(this);
   }
}
