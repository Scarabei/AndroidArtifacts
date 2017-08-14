package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameRef;
import java.util.ArrayList;

public final class zzb extends com.google.android.gms.common.data.zzc implements Invitation {
   private final Game zzbcO;
   private final ParticipantRef zzbdw;
   private final ArrayList zzbdt;

   zzb(DataHolder var1, int var2, int var3) {
      super(var1, var2);
      this.zzbcO = new GameRef(var1, var2);
      this.zzbdt = new ArrayList(var3);
      ParticipantRef var4 = null;
      String var5 = this.getString("external_inviter_id");

      for(int var6 = 0; var6 < var3; ++var6) {
         ParticipantRef var7;
         if ((var7 = new ParticipantRef(this.zzaCX, this.zzaFx + var6)).getParticipantId().equals(var5)) {
            var4 = var7;
         }

         this.zzbdt.add(var7);
      }

      this.zzbdw = (ParticipantRef)zzbo.zzb(var4, "Must have a valid inviter!");
   }

   public final Game getGame() {
      return this.zzbcO;
   }

   public final String getInvitationId() {
      return this.getString("external_invitation_id");
   }

   public final Participant getInviter() {
      return this.zzbdw;
   }

   public final long getCreationTimestamp() {
      long var1 = this.getLong("creation_timestamp");
      long var3 = this.getLong("last_modified_timestamp");
      return Math.max(var1, var3);
   }

   public final ArrayList getParticipants() {
      return this.zzbdt;
   }

   public final int getInvitationType() {
      return this.getInteger("type");
   }

   public final int getVariant() {
      return this.getInteger("variant");
   }

   public final int getAvailableAutoMatchSlots() {
      return !this.getBoolean("has_automatch_criteria") ? 0 : this.getInteger("automatch_max_players");
   }

   public final int hashCode() {
      return InvitationEntity.zza(this);
   }

   public final boolean equals(Object var1) {
      return InvitationEntity.zza(this, var1);
   }

   public final String toString() {
      return InvitationEntity.zzb(this);
   }

   public final int describeContents() {
      return 0;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      ((InvitationEntity)((Invitation)this.freeze())).writeToParcel(var1, var2);
   }

   // $FF: synthetic method
   public final Object freeze() {
      return new InvitationEntity(this);
   }
}
