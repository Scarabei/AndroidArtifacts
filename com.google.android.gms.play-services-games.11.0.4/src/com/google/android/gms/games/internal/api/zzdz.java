package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;

abstract class zzdz extends Games.zza {
   private zzdz(GoogleApiClient var1) {
      super(var1);
   }

   // $FF: synthetic method
   public final Result zzb(Status var1) {
      return new zzea(this, var1);
   }

   // $FF: synthetic method
   zzdz(GoogleApiClient var1, zzds var2) {
      this(var1);
   }
}
