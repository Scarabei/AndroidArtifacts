package com.google.android.gms.games.multiplayer.realtime;

import android.database.CharArrayBuffer;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.ParticipantRef;
import java.util.ArrayList;

public final class zzf extends com.google.android.gms.common.data.zzc implements Room {
   private final int zzbcP;

   zzf(DataHolder var1, int var2, int var3) {
      super(var1, var2);
      this.zzbcP = var3;
   }

   public final String getRoomId() {
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

   public final String getDescription() {
      return this.getString("description");
   }

   public final void getDescription(CharArrayBuffer var1) {
      this.zza("description", var1);
   }

   public final int getVariant() {
      return this.getInteger("variant");
   }

   public final Bundle getAutoMatchCriteria() {
      if (!this.getBoolean("has_automatch_criteria")) {
         return null;
      } else {
         int var1 = this.getInteger("automatch_min_players");
         int var2 = this.getInteger("automatch_max_players");
         long var3 = this.getLong("automatch_bit_mask");
         return RoomConfig.createAutoMatchCriteria(var1, var2, var3);
      }
   }

   public final ArrayList getParticipants() {
      ArrayList var1 = new ArrayList(this.zzbcP);

      for(int var2 = 0; var2 < this.zzbcP; ++var2) {
         var1.add(new ParticipantRef(this.zzaCX, this.zzaFx + var2));
      }

      return var1;
   }

   public final int getAutoMatchWaitEstimateSeconds() {
      return this.getInteger("automatch_wait_estimate_sec");
   }

   public final int getParticipantStatus(String var1) {
      return RoomEntity.zza(this, (String)var1);
   }

   public final ArrayList getParticipantIds() {
      return RoomEntity.zzc(this);
   }

   public final String getParticipantId(String var1) {
      return RoomEntity.zzb(this, var1);
   }

   public final Participant getParticipant(String var1) {
      return RoomEntity.zzc(this, var1);
   }

   public final int hashCode() {
      return RoomEntity.zza(this);
   }

   public final boolean equals(Object var1) {
      return RoomEntity.zza(this, (Object)var1);
   }

   public final String toString() {
      return RoomEntity.zzb(this);
   }

   public final int describeContents() {
      return 0;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      ((RoomEntity)((Room)this.freeze())).writeToParcel(var1, var2);
   }

   // $FF: synthetic method
   public final Object freeze() {
      return new RoomEntity(this);
   }
}
