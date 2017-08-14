package com.google.android.gms.games.multiplayer;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;

public final class ParticipantRef extends com.google.android.gms.common.data.zzc implements Participant {
   private final PlayerRef zzbdA;

   public ParticipantRef(DataHolder var1, int var2) {
      super(var1, var2);
      this.zzbdA = new PlayerRef(var1, var2);
   }

   public final int getStatus() {
      return this.getInteger("player_status");
   }

   public final String zzvr() {
      return this.getString("client_address");
   }

   public final boolean isConnectedToRoom() {
      return this.getInteger("connected") > 0;
   }

   public final String getDisplayName() {
      return this.zzcx("external_player_id") ? this.getString("default_display_name") : this.zzbdA.getDisplayName();
   }

   public final void getDisplayName(CharArrayBuffer var1) {
      if (this.zzcx("external_player_id")) {
         this.zza("default_display_name", var1);
      } else {
         this.zzbdA.getDisplayName(var1);
      }
   }

   public final Uri getIconImageUri() {
      return this.zzcx("external_player_id") ? this.zzcw("default_display_image_uri") : this.zzbdA.getIconImageUri();
   }

   public final String getIconImageUrl() {
      return this.zzcx("external_player_id") ? this.getString("default_display_image_url") : this.zzbdA.getIconImageUrl();
   }

   public final Uri getHiResImageUri() {
      return this.zzcx("external_player_id") ? this.zzcw("default_display_hi_res_image_uri") : this.zzbdA.getHiResImageUri();
   }

   public final String getHiResImageUrl() {
      return this.zzcx("external_player_id") ? this.getString("default_display_hi_res_image_url") : this.zzbdA.getHiResImageUrl();
   }

   public final String getParticipantId() {
      return this.getString("external_participant_id");
   }

   public final Player getPlayer() {
      return this.zzcx("external_player_id") ? null : this.zzbdA;
   }

   public final ParticipantResult getResult() {
      if (this.zzcx("result_type")) {
         return null;
      } else {
         int var1 = this.getInteger("result_type");
         int var2 = this.getInteger("placing");
         return new ParticipantResult(this.getParticipantId(), var1, var2);
      }
   }

   public final int getCapabilities() {
      return this.getInteger("capabilities");
   }

   public final int hashCode() {
      return ParticipantEntity.zza(this);
   }

   public final boolean equals(Object var1) {
      return ParticipantEntity.zza(this, var1);
   }

   public final String toString() {
      return ParticipantEntity.zzb(this);
   }

   public final int describeContents() {
      return 0;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      ((ParticipantEntity)((Participant)this.freeze())).writeToParcel(var1, var2);
   }

   // $FF: synthetic method
   public final Object freeze() {
      return new ParticipantEntity(this);
   }
}
