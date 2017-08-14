package com.google.android.gms.location.places.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceLikelihood;

public final class zzai extends zzav implements PlaceLikelihood {
   public zzai(DataHolder var1, int var2) {
      super(var1, var2);
   }

   public final float getLikelihood() {
      return this.zza("place_likelihood", -1.0F);
   }

   public final Place getPlace() {
      return new zzas(this.zzaCX, this.zzaFx);
   }

   // $FF: synthetic method
   public final Object freeze() {
      PlaceEntity var10000 = (PlaceEntity)this.getPlace().freeze();
      float var3 = this.getLikelihood();
      PlaceEntity var2 = var10000;
      return new zzag((PlaceEntity)zzbo.zzu(var2), var3);
   }
}
