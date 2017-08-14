package com.google.android.gms.games.internal.experience;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.games.Game;

public final class ExperienceEventRef extends zzc implements ExperienceEvent {
   public final String zzuY() {
      return this.getString("external_experience_id");
   }

   public final Game getGame() {
      return null;
   }

   public final String zzuZ() {
      return this.getString("display_title");
   }

   public final String zzva() {
      return this.getString("display_description");
   }

   public final String getIconImageUrl() {
      return this.getString("icon_url");
   }

   public final Uri getIconImageUri() {
      return this.zzcw("icon_uri");
   }

   public final long zzvb() {
      return this.getLong("created_timestamp");
   }

   public final long zzvc() {
      return this.getLong("xp_earned");
   }

   public final long zzvd() {
      return this.getLong("current_xp");
   }

   public final int getType() {
      return this.getInteger("type");
   }

   public final int zzve() {
      return this.getInteger("newLevel");
   }

   public final int hashCode() {
      return ExperienceEventEntity.zza(this);
   }

   public final boolean equals(Object var1) {
      return ExperienceEventEntity.zza(this, var1);
   }

   public final String toString() {
      return ExperienceEventEntity.zzb(this);
   }

   public final int describeContents() {
      return 0;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      ((ExperienceEventEntity)((ExperienceEvent)this.freeze())).writeToParcel(var1, var2);
   }

   // $FF: synthetic method
   public final Object freeze() {
      return new ExperienceEventEntity(this);
   }
}
