package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;

public final class LeaderboardScoreRef extends com.google.android.gms.common.data.zzc implements LeaderboardScore {
   private final PlayerRef zzbdd;

   LeaderboardScoreRef(DataHolder var1, int var2) {
      super(var1, var2);
      this.zzbdd = new PlayerRef(var1, var2);
   }

   public final long getRank() {
      return this.getLong("rank");
   }

   public final String getDisplayRank() {
      return this.getString("display_rank");
   }

   public final void getDisplayRank(CharArrayBuffer var1) {
      this.zza("display_rank", var1);
   }

   public final String getDisplayScore() {
      return this.getString("display_score");
   }

   public final void getDisplayScore(CharArrayBuffer var1) {
      this.zza("display_score", var1);
   }

   public final long getRawScore() {
      return this.getLong("raw_score");
   }

   public final long getTimestampMillis() {
      return this.getLong("achieved_timestamp");
   }

   public final String getScoreHolderDisplayName() {
      return this.zzcx("external_player_id") ? this.getString("default_display_name") : this.zzbdd.getDisplayName();
   }

   public final void getScoreHolderDisplayName(CharArrayBuffer var1) {
      if (this.zzcx("external_player_id")) {
         this.zza("default_display_name", var1);
      } else {
         this.zzbdd.getDisplayName(var1);
      }
   }

   public final Uri getScoreHolderIconImageUri() {
      return this.zzcx("external_player_id") ? this.zzcw("default_display_image_uri") : this.zzbdd.getIconImageUri();
   }

   public final String getScoreHolderIconImageUrl() {
      return this.zzcx("external_player_id") ? this.getString("default_display_image_url") : this.zzbdd.getIconImageUrl();
   }

   public final Uri getScoreHolderHiResImageUri() {
      return this.zzcx("external_player_id") ? null : this.zzbdd.getHiResImageUri();
   }

   public final String getScoreHolderHiResImageUrl() {
      return this.zzcx("external_player_id") ? null : this.zzbdd.getHiResImageUrl();
   }

   public final Player getScoreHolder() {
      return this.zzcx("external_player_id") ? null : this.zzbdd;
   }

   public final String getScoreTag() {
      return this.getString("score_tag");
   }

   public final int hashCode() {
      return LeaderboardScoreEntity.zza(this);
   }

   public final boolean equals(Object var1) {
      return LeaderboardScoreEntity.zza(this, var1);
   }

   public final String toString() {
      return LeaderboardScoreEntity.zzb(this);
   }

   // $FF: synthetic method
   public final Object freeze() {
      return new LeaderboardScoreEntity(this);
   }
}
