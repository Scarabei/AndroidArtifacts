package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzd;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.location.places.PlacesStatusCodes;

/** @deprecated */
@Deprecated
public final class zzcdz extends zzd implements Result {
   private final Status mStatus;

   public zzcdz(DataHolder var1) {
      this(var1, PlacesStatusCodes.zzaH(var1.getStatusCode()));
   }

   private zzcdz(DataHolder var1, Status var2) {
      super(var1, zzcdy.CREATOR);
      zzbo.zzaf(var1 == null || var1.getStatusCode() == var2.getStatusCode());
      this.mStatus = var2;
   }

   public final Status getStatus() {
      return this.mStatus;
   }
}
