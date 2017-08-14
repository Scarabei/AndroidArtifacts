package com.google.android.gms.games.event;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;

public final class EventRef extends zzc implements Event {
   EventRef(DataHolder var1, int var2) {
      super(var1, var2);
   }

   public final String getEventId() {
      return this.getString("external_event_id");
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

   public final Uri getIconImageUri() {
      return this.zzcw("icon_image_uri");
   }

   public final String getIconImageUrl() {
      return this.getString("icon_image_url");
   }

   public final Player getPlayer() {
      return new PlayerRef(this.zzaCX, this.zzaFx);
   }

   public final long getValue() {
      return this.getLong("value");
   }

   public final String getFormattedValue() {
      return this.getString("formatted_value");
   }

   public final void getFormattedValue(CharArrayBuffer var1) {
      this.zza("formatted_value", var1);
   }

   public final boolean isVisible() {
      return this.getBoolean("visibility");
   }

   public final int hashCode() {
      return EventEntity.zza(this);
   }

   public final boolean equals(Object var1) {
      return EventEntity.zza(this, var1);
   }

   public final String toString() {
      return EventEntity.zzb(this);
   }

   public final int describeContents() {
      return 0;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      ((EventEntity)((Event)this.freeze())).writeToParcel(var1, var2);
   }

   // $FF: synthetic method
   public final Object freeze() {
      return new EventEntity(this);
   }
}
