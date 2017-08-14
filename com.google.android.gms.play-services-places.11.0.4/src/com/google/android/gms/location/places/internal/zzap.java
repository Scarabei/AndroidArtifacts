package com.google.android.gms.location.places.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.Places;
import java.util.Arrays;

public final class zzap implements PlacePhotoMetadata {
   private final String zzbkY;
   private final int zzOb;
   private final int zzOc;
   private final CharSequence zzbkZ;
   private int mIndex;

   public zzap(String var1, int var2, int var3, CharSequence var4, int var5) {
      this.zzbkY = var1;
      this.zzOb = var2;
      this.zzOc = var3;
      this.zzbkZ = var4;
      this.mIndex = var5;
   }

   public final int getMaxWidth() {
      return this.zzOb;
   }

   public final int getMaxHeight() {
      return this.zzOc;
   }

   public final CharSequence getAttributions() {
      return this.zzbkZ;
   }

   public final PendingResult getPhoto(GoogleApiClient var1) {
      return this.getScaledPhoto(var1, this.getMaxWidth(), this.getMaxHeight());
   }

   public final PendingResult getScaledPhoto(GoogleApiClient var1, int var2, int var3) {
      return var1.zzd(new zzaq(this, Places.GEO_DATA_API, var1, var2, var3));
   }

   public final boolean isDataValid() {
      return true;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof zzap)) {
         return false;
      } else {
         zzap var2;
         return (var2 = (zzap)var1).zzOb == this.zzOb && var2.zzOc == this.zzOc && zzbe.equal(var2.zzbkY, this.zzbkY) && zzbe.equal(var2.zzbkZ, this.zzbkZ);
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzOb, this.zzOc, this.zzbkY, this.zzbkZ});
   }

   // $FF: synthetic method
   static String zza(zzap var0) {
      return var0.zzbkY;
   }

   // $FF: synthetic method
   static int zzb(zzap var0) {
      return var0.mIndex;
   }
}
