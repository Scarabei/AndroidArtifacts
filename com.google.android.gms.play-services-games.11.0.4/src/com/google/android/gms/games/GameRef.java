package com.google.android.gms.games;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;

public final class GameRef extends com.google.android.gms.common.data.zzc implements Game {
   public GameRef(DataHolder var1, int var2) {
      super(var1, var2);
   }

   public final String getApplicationId() {
      return this.getString("external_game_id");
   }

   public final String getDisplayName() {
      return this.getString("display_name");
   }

   public final void getDisplayName(CharArrayBuffer var1) {
      this.zza("display_name", var1);
   }

   public final String getPrimaryCategory() {
      return this.getString("primary_category");
   }

   public final String getSecondaryCategory() {
      return this.getString("secondary_category");
   }

   public final String getDescription() {
      return this.getString("game_description");
   }

   public final void getDescription(CharArrayBuffer var1) {
      this.zza("game_description", var1);
   }

   public final String getDeveloperName() {
      return this.getString("developer_name");
   }

   public final void getDeveloperName(CharArrayBuffer var1) {
      this.zza("developer_name", var1);
   }

   public final Uri getIconImageUri() {
      return this.zzcw("game_icon_image_uri");
   }

   public final String getIconImageUrl() {
      return this.getString("game_icon_image_url");
   }

   public final Uri getHiResImageUri() {
      return this.zzcw("game_hi_res_image_uri");
   }

   public final String getHiResImageUrl() {
      return this.getString("game_hi_res_image_url");
   }

   public final Uri getFeaturedImageUri() {
      return this.zzcw("featured_image_uri");
   }

   public final String getFeaturedImageUrl() {
      return this.getString("featured_image_url");
   }

   public final boolean zzud() {
      return this.getBoolean("play_enabled_game");
   }

   public final boolean isMuted() {
      return this.getBoolean("muted");
   }

   public final boolean zzue() {
      return this.getBoolean("identity_sharing_confirmed");
   }

   public final boolean zzuf() {
      return this.getInteger("installed") > 0;
   }

   public final String zzug() {
      return this.getString("package_name");
   }

   public final int getAchievementTotalCount() {
      return this.getInteger("achievement_total_count");
   }

   public final int getLeaderboardCount() {
      return this.getInteger("leaderboard_count");
   }

   public final boolean isRealTimeMultiplayerEnabled() {
      return this.getInteger("real_time_support") > 0;
   }

   public final boolean isTurnBasedMultiplayerEnabled() {
      return this.getInteger("turn_based_support") > 0;
   }

   public final boolean areSnapshotsEnabled() {
      return this.getInteger("snapshots_enabled") > 0;
   }

   public final String getThemeColor() {
      return this.getString("theme_color");
   }

   public final boolean hasGamepadSupport() {
      return this.getInteger("gamepad_support") > 0;
   }

   public final int hashCode() {
      return GameEntity.zza(this);
   }

   public final boolean equals(Object var1) {
      return GameEntity.zza(this, var1);
   }

   public final String toString() {
      return GameEntity.zzb(this);
   }

   public final int describeContents() {
      return 0;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      ((GameEntity)((Game)this.freeze())).writeToParcel(var1, var2);
   }

   // $FF: synthetic method
   public final Object freeze() {
      return new GameEntity(this);
   }
}
