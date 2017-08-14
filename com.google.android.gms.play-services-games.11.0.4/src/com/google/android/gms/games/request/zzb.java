package com.google.android.gms.games.request;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameRef;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;
import java.util.ArrayList;
import java.util.List;

/** @deprecated */
@Deprecated
public final class zzb extends zzc implements GameRequest {
   private final int zzbcP;

   public zzb(DataHolder var1, int var2, int var3) {
      super(var1, var2);
      this.zzbcP = var3;
   }

   public final String getRequestId() {
      return this.getString("external_request_id");
   }

   public final Game getGame() {
      return new GameRef(this.zzaCX, this.zzaFx);
   }

   public final Player getSender() {
      return new PlayerRef(this.zzaCX, super.zzaFx, "sender_");
   }

   public final List getRecipients() {
      ArrayList var1 = new ArrayList(this.zzbcP);

      for(int var2 = 0; var2 < this.zzbcP; ++var2) {
         var1.add(new PlayerRef(this.zzaCX, this.zzaFx + var2, "recipient_"));
      }

      return var1;
   }

   public final boolean isConsumed(String var1) {
      return this.getRecipientStatus(var1) == 1;
   }

   public final byte[] getData() {
      return this.getByteArray("data");
   }

   public final int getType() {
      return this.getInteger("type");
   }

   public final long getCreationTimestamp() {
      return this.getLong("creation_timestamp");
   }

   public final long getExpirationTimestamp() {
      return this.getLong("expiration_timestamp");
   }

   public final int getRecipientStatus(String var1) {
      for(int var2 = this.zzaFx; var2 < this.zzaFx + this.zzbcP; ++var2) {
         int var3 = this.zzaCX.zzat(var2);
         if (this.zzaCX.zzd("recipient_external_player_id", var2, var3).equals(var1)) {
            return this.zzaCX.zzc("recipient_status", var2, var3);
         }
      }

      return -1;
   }

   public final int getStatus() {
      return this.getInteger("status");
   }

   public final int hashCode() {
      return GameRequestEntity.zza(this);
   }

   public final boolean equals(Object var1) {
      return GameRequestEntity.zza(this, var1);
   }

   public final String toString() {
      return GameRequestEntity.zzc(this);
   }

   public final int describeContents() {
      return 0;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      ((GameRequestEntity)((GameRequest)this.freeze())).writeToParcel(var1, var2);
   }

   // $FF: synthetic method
   public final Object freeze() {
      return new GameRequestEntity(this);
   }
}
