package com.google.android.gms.location.places.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.location.places.PlacePhotoMetadata;

public final class zzar extends zzav implements PlacePhotoMetadata {
   private final String zzbkY = this.getString("photo_fife_url");

   public zzar(DataHolder var1, int var2) {
      super(var1, var2);
   }

   public final int getMaxWidth() {
      return this.zzu("photo_max_width", 0);
   }

   public final int getMaxHeight() {
      return this.zzu("photo_max_height", 0);
   }

   public final CharSequence getAttributions() {
      return this.zzD("photo_attributions", (String)null);
   }

   public final PendingResult getPhoto(GoogleApiClient var1) {
      return this.getScaledPhoto(var1, this.getMaxWidth(), this.getMaxHeight());
   }

   public final PendingResult getScaledPhoto(GoogleApiClient var1, int var2, int var3) {
      return ((PlacePhotoMetadata)this.freeze()).getScaledPhoto(var1, var2, var3);
   }

   // $FF: synthetic method
   public final Object freeze() {
      return new zzap(this.zzbkY, this.getMaxWidth(), this.getMaxHeight(), this.getAttributions(), this.zzaFx);
   }
}
