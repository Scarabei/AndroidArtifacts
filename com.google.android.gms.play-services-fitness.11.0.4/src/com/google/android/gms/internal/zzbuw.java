package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbo;

abstract class zzbuw extends zzbuu {
   zzbuw(GoogleApiClient var1) {
      super(var1);
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      zzbo.zzaf(!var1.isSuccess());
      return var1;
   }
}
