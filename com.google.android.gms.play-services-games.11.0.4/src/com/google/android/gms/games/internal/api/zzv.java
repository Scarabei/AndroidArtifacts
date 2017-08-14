package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;

abstract class zzv extends Games.zza {
   private zzv(GoogleApiClient var1) {
      super(var1);
   }

   public final Result zzb(Status var1) {
      return new zzw(this, var1);
   }

   // $FF: synthetic method
   zzv(GoogleApiClient var1, zzq var2) {
      this(var1);
   }
}
