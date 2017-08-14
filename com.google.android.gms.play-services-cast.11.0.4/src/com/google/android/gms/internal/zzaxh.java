package com.google.android.gms.internal;

import com.google.android.gms.cast.games.GameManagerClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

public abstract class zzaxh extends zzaxg {
   private GameManagerClient zzaxl;
   // $FF: synthetic field
   final zzawy zzaxd;

   public zzaxh(zzawy var1, GameManagerClient var2) {
      super(var1);
      this.zzaxd = var1;
      this.zzaxl = var2;
      this.zzarw = new zzaxi(this, var1);
   }

   public static GameManagerClient.GameManagerInstanceResult zzk(Status var0) {
      return new zzaxj(var0, (GameManagerClient)null);
   }

   // $FF: synthetic method
   public final Result zzb(Status var1) {
      return zzk(var1);
   }

   // $FF: synthetic method
   static GameManagerClient zza(zzaxh var0) {
      return var0.zzaxl;
   }
}
