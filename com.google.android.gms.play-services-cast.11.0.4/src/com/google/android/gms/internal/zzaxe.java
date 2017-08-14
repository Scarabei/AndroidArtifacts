package com.google.android.gms.internal;

import com.google.android.gms.cast.games.GameManagerClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import org.json.JSONObject;

public abstract class zzaxe extends zzaxg {
   // $FF: synthetic field
   final zzawy zzaxd;

   public zzaxe(zzawy var1) {
      super(var1);
      this.zzaxd = var1;
      this.zzarw = new zzaxf(this, var1);
   }

   public static GameManagerClient.GameManagerResult zzj(Status var0) {
      return new zzaxk(var0, (String)null, -1L, (JSONObject)null);
   }

   // $FF: synthetic method
   public final Result zzb(Status var1) {
      return zzj(var1);
   }
}
