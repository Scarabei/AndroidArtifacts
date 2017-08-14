package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;

abstract class zzan extends Games.zza {
   private zzan(GoogleApiClient var1) {
      super(var1);
   }

   // $FF: synthetic method
   public final Result zzb(Status var1) {
      return new zzao(this, var1);
   }

   // $FF: synthetic method
   zzan(GoogleApiClient var1, zzag var2) {
      this(var1);
   }
}
