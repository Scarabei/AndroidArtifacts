package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzbay;
import com.google.android.gms.internal.zzbdw;
import com.google.android.gms.nearby.Nearby;

abstract class zzav extends zzbay {
   private final zzbdw zzbzo;

   public zzav(GoogleApiClient var1) {
      super(Nearby.MESSAGES_API, var1);
      this.zzbzo = var1.zzp(this);
   }

   final zzbdw zzzX() {
      return this.zzbzo;
   }

   // $FF: synthetic method
   public final Result zzb(Status var1) {
      return var1;
   }
}
