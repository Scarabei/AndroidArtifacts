package com.google.android.gms.games.achievement;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;

public final class AchievementRef extends zzc implements Achievement {
   AchievementRef(DataHolder var1, int var2) {
      super(var1, var2);
   }

   public final String getAchievementId() {
      return this.getString("external_achievement_id");
   }

   public final int getType() {
      return this.getInteger("type");
   }

   public final String getName() {
      return this.getString("name");
   }

   public final void getName(CharArrayBuffer var1) {
      this.zza("name", var1);
   }

   public final String getDescription() {
      return this.getString("description");
   }

   public final void getDescription(CharArrayBuffer var1) {
      this.zza("description", var1);
   }

   public final Uri getUnlockedImageUri() {
      return this.zzcw("unlocked_icon_image_uri");
   }

   public final String getUnlockedImageUrl() {
      return this.getString("unlocked_icon_image_url");
   }

   public final Uri getRevealedImageUri() {
      return this.zzcw("revealed_icon_image_uri");
   }

   public final String getRevealedImageUrl() {
      return this.getString("revealed_icon_image_url");
   }

   public final int getTotalSteps() {
      com.google.android.gms.common.internal.zzc.zzae(this.getType() == 1);
      return this.getInteger("total_steps");
   }

   public final String getFormattedTotalSteps() {
      com.google.android.gms.common.internal.zzc.zzae(this.getType() == 1);
      return this.getString("formatted_total_steps");
   }

   public final void getFormattedTotalSteps(CharArrayBuffer var1) {
      com.google.android.gms.common.internal.zzc.zzae(this.getType() == 1);
      this.zza("formatted_total_steps", var1);
   }

   public final Player getPlayer() {
      return new PlayerRef(this.zzaCX, this.zzaFx);
   }

   public final int getState() {
      return this.getInteger("state");
   }

   public final int getCurrentSteps() {
      com.google.android.gms.common.internal.zzc.zzae(this.getType() == 1);
      return this.getInteger("current_steps");
   }

   public final String getFormattedCurrentSteps() {
      com.google.android.gms.common.internal.zzc.zzae(this.getType() == 1);
      return this.getString("formatted_current_steps");
   }

   public final void getFormattedCurrentSteps(CharArrayBuffer var1) {
      com.google.android.gms.common.internal.zzc.zzae(this.getType() == 1);
      this.zza("formatted_current_steps", var1);
   }

   public final long getLastUpdatedTimestamp() {
      return this.getLong("last_updated_timestamp");
   }

   public final long getXpValue() {
      return this.zzcv("instance_xp_value") && !this.zzcx("instance_xp_value") ? this.getLong("instance_xp_value") : this.getLong("definition_xp_value");
   }

   public final String toString() {
      return AchievementEntity.zza(this);
   }

   public final int describeContents() {
      return 0;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      ((AchievementEntity)((Achievement)this.freeze())).writeToParcel(var1, var2);
   }

   // $FF: synthetic method
   public final Object freeze() {
      return new AchievementEntity(this);
   }
}
