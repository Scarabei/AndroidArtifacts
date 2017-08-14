package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;

abstract class zzdf extends Games.zza {
   private final String zzIi;

   public zzdf(String var1, GoogleApiClient var2) {
      super(var2);
      this.zzIi = var1;
   }

   // $FF: synthetic method
   public final Result zzb(Status var1) {
      return new zzdg(this, var1);
   }

   // $FF: synthetic method
   static String zza(zzdf var0) {
      return var0.zzIi;
   }
}
