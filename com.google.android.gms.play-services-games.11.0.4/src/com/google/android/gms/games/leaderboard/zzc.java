package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.DataHolder;

public final class zzc extends com.google.android.gms.common.data.zzc implements LeaderboardVariant {
   zzc(DataHolder var1, int var2) {
      super(var1, var2);
   }

   public final int getTimeSpan() {
      return this.getInteger("timespan");
   }

   public final int getCollection() {
      return this.getInteger("collection");
   }

   public final boolean hasPlayerInfo() {
      return !this.zzcx("player_raw_score");
   }

   public final long getRawPlayerScore() {
      return this.zzcx("player_raw_score") ? -1L : this.getLong("player_raw_score");
   }

   public final String getDisplayPlayerScore() {
      return this.getString("player_display_score");
   }

   public final long getPlayerRank() {
      return this.zzcx("player_rank") ? -1L : this.getLong("player_rank");
   }

   public final String getDisplayPlayerRank() {
      return this.getString("player_display_rank");
   }

   public final String getPlayerScoreTag() {
      return this.getString("player_score_tag");
   }

   public final long getNumScores() {
      return this.zzcx("total_scores") ? -1L : this.getLong("total_scores");
   }

   public final String zzvo() {
      return this.getString("top_page_token_next");
   }

   public final String zzvp() {
      return this.getString("window_page_token_prev");
   }

   public final String zzvq() {
      return this.getString("window_page_token_next");
   }

   public final int hashCode() {
      return zzb.zza(this);
   }

   public final boolean equals(Object var1) {
      return zzb.zza(this, var1);
   }

   public final String toString() {
      return zzb.zzb(this);
   }

   // $FF: synthetic method
   public final Object freeze() {
      return new zzb(this);
   }
}
