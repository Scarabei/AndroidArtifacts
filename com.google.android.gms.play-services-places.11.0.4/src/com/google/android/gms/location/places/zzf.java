package com.google.android.gms.location.places;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;

public abstract class zzf extends zzm.zzb {
   public zzf(Api var1, GoogleApiClient var2) {
      super(var1, var2);
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return new PlacePhotoMetadataResult(var1, (DataHolder)null);
   }
}
