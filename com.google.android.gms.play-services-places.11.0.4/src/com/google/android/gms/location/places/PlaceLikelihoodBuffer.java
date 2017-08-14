package com.google.android.gms.location.places;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.location.places.internal.zzai;
import java.util.Comparator;

public class PlaceLikelihoodBuffer extends AbstractDataBuffer implements Result {
   private static final Comparator zzbjE = new zzi();
   private final String zzbjs;
   private final int zzBM;
   private final Status mStatus;

   public static int zzz(Bundle var0) {
      return var0.getInt("com.google.android.gms.location.places.PlaceLikelihoodBuffer.SOURCE_EXTRA_KEY");
   }

   public PlaceLikelihoodBuffer(DataHolder var1, int var2) {
      super(var1);
      this.mStatus = PlacesStatusCodes.zzaH(var1.getStatusCode());
      switch(var2) {
      case 100:
      case 101:
      case 102:
      case 103:
      case 104:
      case 105:
      case 106:
      case 107:
      case 108:
         this.zzBM = var2;
         if (var1 != null && var1.zzqN() != null) {
            this.zzbjs = var1.zzqN().getString("com.google.android.gms.location.places.PlaceLikelihoodBuffer.ATTRIBUTIONS_EXTRA_KEY");
            return;
         }

         this.zzbjs = null;
         return;
      default:
         throw new IllegalArgumentException((new StringBuilder(27)).append("invalid source: ").append(var2).toString());
      }
   }

   public PlaceLikelihood get(int var1) {
      return new zzai(this.zzaCX, var1);
   }

   @Nullable
   public CharSequence getAttributions() {
      return this.zzbjs;
   }

   public Status getStatus() {
      return this.mStatus;
   }

   public String toString() {
      return zzbe.zzt(this).zzg("status", this.getStatus()).zzg("attributions", this.zzbjs).toString();
   }
}
