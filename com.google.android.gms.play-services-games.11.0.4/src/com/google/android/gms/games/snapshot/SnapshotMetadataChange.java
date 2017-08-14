package com.google.android.gms.games.snapshot;

import android.graphics.Bitmap;
import android.net.Uri;
import com.google.android.gms.common.data.BitmapTeleporter;

public interface SnapshotMetadataChange {
   SnapshotMetadataChange EMPTY_CHANGE = new zze();

   String getDescription();

   Long getPlayedTimeMillis();

   BitmapTeleporter zzvv();

   Bitmap getCoverImage();

   Long getProgressValue();

   public static final class Builder {
      private String zzafa;
      private Long zzbez;
      private Long zzbeA;
      private BitmapTeleporter zzbeB;
      private Uri zzbeC;

      public final SnapshotMetadataChange.Builder setDescription(String var1) {
         this.zzafa = var1;
         return this;
      }

      public final SnapshotMetadataChange.Builder setPlayedTimeMillis(long var1) {
         this.zzbez = var1;
         return this;
      }

      public final SnapshotMetadataChange.Builder setProgressValue(long var1) {
         this.zzbeA = var1;
         return this;
      }

      public final SnapshotMetadataChange.Builder setCoverImage(Bitmap var1) {
         this.zzbeB = new BitmapTeleporter(var1);
         this.zzbeC = null;
         return this;
      }

      public final SnapshotMetadataChange.Builder fromMetadata(SnapshotMetadata var1) {
         this.zzafa = var1.getDescription();
         this.zzbez = var1.getPlayedTime();
         this.zzbeA = var1.getProgressValue();
         if (this.zzbez.longValue() == -1L) {
            this.zzbez = null;
         }

         this.zzbeC = var1.getCoverImageUri();
         if (this.zzbeC != null) {
            this.zzbeB = null;
         }

         return this;
      }

      public final SnapshotMetadataChange build() {
         return new zze(this.zzafa, this.zzbez, this.zzbeB, this.zzbeC, this.zzbeA);
      }
   }
}
