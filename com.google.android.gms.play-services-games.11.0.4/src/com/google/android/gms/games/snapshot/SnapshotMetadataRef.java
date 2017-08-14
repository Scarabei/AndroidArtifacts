package com.google.android.gms.games.snapshot;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameRef;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;

public final class SnapshotMetadataRef extends com.google.android.gms.common.data.zzc implements SnapshotMetadata {
   private final Game zzbcO;
   private final Player zzbeO;

   public SnapshotMetadataRef(DataHolder var1, int var2) {
      super(var1, var2);
      this.zzbcO = new GameRef(var1, var2);
      this.zzbeO = new PlayerRef(var1, var2);
   }

   public final Game getGame() {
      return this.zzbcO;
   }

   public final Player getOwner() {
      return this.zzbeO;
   }

   public final String getSnapshotId() {
      return this.getString("external_snapshot_id");
   }

   public final Uri getCoverImageUri() {
      return this.zzcw("cover_icon_image_uri");
   }

   public final String getCoverImageUrl() {
      return this.getString("cover_icon_image_url");
   }

   public final float getCoverImageAspectRatio() {
      float var1 = this.getFloat("cover_icon_image_height");
      float var2 = this.getFloat("cover_icon_image_width");
      return var1 == 0.0F ? 0.0F : var2 / var1;
   }

   public final String getUniqueName() {
      return this.getString("unique_name");
   }

   public final String getTitle() {
      return this.getString("title");
   }

   public final String getDescription() {
      return this.getString("description");
   }

   public final void getDescription(CharArrayBuffer var1) {
      this.zza("description", var1);
   }

   public final long getLastModifiedTimestamp() {
      return this.getLong("last_modified_timestamp");
   }

   public final long getPlayedTime() {
      return this.getLong("duration");
   }

   public final boolean hasChangePending() {
      return this.getInteger("pending_change_count") > 0;
   }

   public final long getProgressValue() {
      return this.getLong("progress_value");
   }

   public final String getDeviceName() {
      return this.getString("device_name");
   }

   public final int hashCode() {
      return SnapshotMetadataEntity.zza(this);
   }

   public final boolean equals(Object var1) {
      return SnapshotMetadataEntity.zza(this, var1);
   }

   public final String toString() {
      return SnapshotMetadataEntity.zzb(this);
   }

   public final int describeContents() {
      return 0;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      ((SnapshotMetadataEntity)((SnapshotMetadata)this.freeze())).writeToParcel(var1, var2);
   }

   // $FF: synthetic method
   public final Object freeze() {
      return new SnapshotMetadataEntity(this);
   }
}
