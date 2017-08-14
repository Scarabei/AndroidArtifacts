package com.google.android.gms.games.quest;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameRef;
import java.util.ArrayList;
import java.util.List;

public final class QuestRef extends com.google.android.gms.common.data.zzc implements Quest {
   private final Game zzbcO;
   private final int zzbcP;

   QuestRef(DataHolder var1, int var2, int var3) {
      super(var1, var2);
      this.zzbcO = new GameRef(var1, var2);
      this.zzbcP = var3;
   }

   public final String getQuestId() {
      return this.getString("external_quest_id");
   }

   public final String getName() {
      return this.getString("quest_name");
   }

   public final void getName(CharArrayBuffer var1) {
      this.zza("quest_name", var1);
   }

   public final String getDescription() {
      return this.getString("quest_description");
   }

   public final void getDescription(CharArrayBuffer var1) {
      this.zza("quest_description", var1);
   }

   public final Uri getIconImageUri() {
      return this.zzcw("quest_icon_image_uri");
   }

   public final String getIconImageUrl() {
      return this.getString("quest_icon_image_url");
   }

   public final Uri getBannerImageUri() {
      return this.zzcw("quest_banner_image_uri");
   }

   public final String getBannerImageUrl() {
      return this.getString("quest_banner_image_url");
   }

   public final Milestone getCurrentMilestone() {
      return (Milestone)this.zzvt().get(0);
   }

   public final List zzvt() {
      ArrayList var1 = new ArrayList(this.zzbcP);

      for(int var2 = 0; var2 < this.zzbcP; ++var2) {
         var1.add(new zzb(this.zzaCX, this.zzaFx + var2));
      }

      return var1;
   }

   public final Game getGame() {
      return this.zzbcO;
   }

   public final int getState() {
      return this.getInteger("quest_state");
   }

   public final int getType() {
      return this.getInteger("quest_type");
   }

   public final long getAcceptedTimestamp() {
      return this.getLong("accepted_ts");
   }

   public final long getEndTimestamp() {
      return this.getLong("quest_end_ts");
   }

   public final long getLastUpdatedTimestamp() {
      return this.getLong("quest_last_updated_ts");
   }

   public final long zzvu() {
      return this.getLong("notification_ts");
   }

   public final long getStartTimestamp() {
      return this.getLong("quest_start_ts");
   }

   public final boolean isEndingSoon() {
      return this.getLong("notification_ts") <= System.currentTimeMillis() + 1800000L;
   }

   public final int hashCode() {
      return QuestEntity.zza(this);
   }

   public final boolean equals(Object var1) {
      return QuestEntity.zza(this, var1);
   }

   public final String toString() {
      return QuestEntity.zzb(this);
   }

   public final int describeContents() {
      return 0;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      ((QuestEntity)((Quest)this.freeze())).writeToParcel(var1, var2);
   }

   // $FF: synthetic method
   public final Object freeze() {
      return new QuestEntity(this);
   }
}
