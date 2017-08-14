package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

public abstract class zzbjx extends zzbay {
   public zzbjx(GoogleApiClient var1) {
      super(zzbil.API, var1);
   }

   // $FF: synthetic method
   public final Result zzb(Status var1) {
      return new zzbjy(this, var1);
   }
}
