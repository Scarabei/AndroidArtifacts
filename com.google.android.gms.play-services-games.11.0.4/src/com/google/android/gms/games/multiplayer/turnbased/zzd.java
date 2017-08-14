package com.google.android.gms.games.multiplayer.turnbased;

import android.database.CharArrayBuffer;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameRef;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.ParticipantRef;
import java.util.ArrayList;

public final class zzd extends com.google.android.gms.common.data.zzc implements TurnBasedMatch {
   private final Game zzbcO;
   private final int zzbcP;

   zzd(DataHolder var1, int var2, int var3) {
      super(var1, var2);
      this.zzbcO = new GameRef(var1, var2);
      this.zzbcP = var3;
   }

   public final Game getGame() {
      return this.zzbcO;
   }

   public final String getMatchId() {
      return this.getString("external_match_id");
   }

   public final String getCreatorId() {
      return this.getString("creator_external");
   }

   public final long getCreationTimestamp() {
      return this.getLong("creation_timestamp");
   }

   public final int getStatus() {
      return this.getInteger("status");
   }

   public final int getTurnStatus() {
      return this.getInteger("user_match_status");
   }

   public final String getDescription() {
      return this.getString("description");
   }

   public final void getDescription(CharArrayBuffer var1) {
      this.zza("description", var1);
   }

   public final String getDescriptionParticipantId() {
      return this.getString("description_participant_id");
   }

   public final Participant getDescriptionParticipant() {
      String var1;
      return (var1 = this.getDescriptionParticipantId()) == null ? null : this.getParticipant(var1);
   }

   public final int getVariant() {
      return this.getInteger("variant");
   }

   public final ArrayList getParticipants() {
      ArrayList var1 = new ArrayList(this.zzbcP);

      for(int var2 = 0; var2 < this.zzbcP; ++var2) {
         var1.add(new ParticipantRef(this.zzaCX, this.zzaFx + var2));
      }

      return var1;
   }

   public final String getLastUpdaterId() {
      return this.getString("last_updater_external");
   }

   public final long getLastUpdatedTimestamp() {
      return this.getLong("last_updated_timestamp");
   }

   public final String getPendingParticipantId() {
      return this.getString("pending_participant_external");
   }

   public final byte[] getData() {
      return this.getByteArray("data");
   }

   public final int getVersion() {
      return this.getInteger("version");
   }

   public final String getRematchId() {
      return this.getString("rematch_id");
   }

   public final byte[] getPreviousMatchData() {
      return this.getByteArray("previous_match_data");
   }

   public final int getMatchNumber() {
      return this.getInteger("match_number");
   }

   public final Bundle getAutoMatchCriteria() {
      if (!this.getBoolean("has_automatch_criteria")) {
         return null;
      } else {
         int var1 = this.getInteger("automatch_min_players");
         int var2 = this.getInteger("automatch_max_players");
         long var3 = this.getLong("automatch_bit_mask");
         return TurnBasedMatchConfig.createAutoMatchCriteria(var1, var2, var3);
      }
   }

   public final int getAvailableAutoMatchSlots() {
      return !this.getBoolean("has_automatch_criteria") ? 0 : this.getInteger("automatch_max_players");
   }

   public final boolean canRematch() {
      return this.getTurnStatus() == 3 && this.getRematchId() == null && this.getParticipants().size() > 1;
   }

   public final boolean isLocallyModified() {
      return this.getBoolean("upsync_required");
   }

   public final int getParticipantStatus(String var1) {
      return TurnBasedMatchEntity.zza(this, (String)var1);
   }

   public final ArrayList getParticipantIds() {
      return TurnBasedMatchEntity.zzc(this);
   }

   public final String getParticipantId(String var1) {
      return TurnBasedMatchEntity.zzb(this, var1);
   }

   public final Participant getParticipant(String var1) {
      return TurnBasedMatchEntity.zzc(this, var1);
   }

   public final int hashCode() {
      return TurnBasedMatchEntity.zza(this);
   }

   public final boolean equals(Object var1) {
      return TurnBasedMatchEntity.zza(this, (Object)var1);
   }

   public final String toString() {
      return TurnBasedMatchEntity.zzb(this);
   }

   public final int describeContents() {
      return 0;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      ((TurnBasedMatchEntity)((TurnBasedMatch)this.freeze())).writeToParcel(var1, var2);
   }

   // $FF: synthetic method
   public final Object freeze() {
      return new TurnBasedMatchEntity(this);
   }
}
