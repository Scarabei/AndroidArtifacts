package com.google.android.gms.location.places;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.location.places.internal.zzas;

public class PlaceBuffer extends AbstractDataBuffer implements Result {
   private final Status mStatus;
   private final String zzbjs;

   public PlaceBuffer(DataHolder var1) {
      super(var1);
      this.mStatus = PlacesStatusCodes.zzaH(var1.getStatusCode());
      if (var1 != null && var1.zzqN() != null) {
         this.zzbjs = var1.zzqN().getString("com.google.android.gms.location.places.PlaceBuffer.ATTRIBUTIONS_EXTRA_KEY");
      } else {
         this.zzbjs = null;
      }
   }

   public Place get(int var1) {
      return new zzas(this.zzaCX, var1);
   }

   public Status getStatus() {
      return this.mStatus;
   }

   @Nullable
   public CharSequence getAttributions() {
      return this.zzbjs;
   }
}
