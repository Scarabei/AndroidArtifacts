package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;

abstract class zzdn extends Games.zza {
   private zzdn(GoogleApiClient var1) {
      super(var1);
   }

   // $FF: synthetic method
   public final Result zzb(Status var1) {
      return new zzdo(this, var1);
   }

   // $FF: synthetic method
   zzdn(GoogleApiClient var1, zzcv var2) {
      this(var1);
   }
}
