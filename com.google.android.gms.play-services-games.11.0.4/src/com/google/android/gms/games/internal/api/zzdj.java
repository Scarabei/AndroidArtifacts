package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;

abstract class zzdj extends Games.zza {
   private zzdj(GoogleApiClient var1) {
      super(var1);
   }

   // $FF: synthetic method
   public final Result zzb(Status var1) {
      return new zzdk(this, var1);
   }

   // $FF: synthetic method
   zzdj(GoogleApiClient var1, zzcv var2) {
      this(var1);
   }
}
