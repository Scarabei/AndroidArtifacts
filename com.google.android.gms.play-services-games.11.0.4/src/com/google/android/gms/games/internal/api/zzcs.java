package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;

abstract class zzcs extends Games.zza {
   private zzcs(GoogleApiClient var1) {
      super(var1);
   }

   // $FF: synthetic method
   public final Result zzb(Status var1) {
      return new zzct(this, var1);
   }

   // $FF: synthetic method
   zzcs(GoogleApiClient var1, zzcr var2) {
      this(var1);
   }
}
