package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.Plus;

abstract class zzcrg extends Plus.zza {
   private zzcrg(GoogleApiClient var1) {
      super(var1);
   }

   // $FF: synthetic method
   public final Result zzb(Status var1) {
      return new zzcrh(this, var1);
   }

   // $FF: synthetic method
   zzcrg(GoogleApiClient var1, zzcrb var2) {
      this(var1);
   }
}
