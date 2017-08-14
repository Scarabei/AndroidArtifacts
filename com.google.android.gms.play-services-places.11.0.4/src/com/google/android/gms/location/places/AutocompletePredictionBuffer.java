package com.google.android.gms.location.places;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzbe;

public class AutocompletePredictionBuffer extends AbstractDataBuffer implements Result {
   public AutocompletePredictionBuffer(DataHolder var1) {
      super(var1);
   }

   public AutocompletePrediction get(int var1) {
      return new com.google.android.gms.location.places.internal.zzd(this.zzaCX, var1);
   }

   public Status getStatus() {
      return PlacesStatusCodes.zzaH(this.zzaCX.getStatusCode());
   }

   public String toString() {
      return zzbe.zzt(this).zzg("status", this.getStatus()).toString();
   }
}
