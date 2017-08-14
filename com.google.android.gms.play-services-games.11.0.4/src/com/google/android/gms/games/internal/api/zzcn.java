package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;

abstract class zzcn extends Games.zza {
   private zzcn(GoogleApiClient var1) {
      super(var1);
   }

   // $FF: synthetic method
   public final Result zzb(Status var1) {
      return new zzco(this, var1);
   }

   // $FF: synthetic method
   zzcn(GoogleApiClient var1, zzcc var2) {
      this(var1);
   }
}
