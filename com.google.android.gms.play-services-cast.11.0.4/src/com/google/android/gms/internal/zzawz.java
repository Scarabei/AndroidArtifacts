package com.google.android.gms.internal;

import com.google.android.gms.cast.games.GameManagerClient;
import java.io.IOException;
import org.json.JSONObject;

final class zzawz extends zzaxh {
   // $FF: synthetic field
   final zzawy zzaxd;

   zzawz(zzawy var1, GameManagerClient var2) {
      super(var1, var2);
      this.zzaxd = var1;
   }

   public final void execute() {
      try {
         zzawy.zzb(this.zzaxd).setMessageReceivedCallbacks(zzawy.zza(this.zzaxd), this.zzaxd.getNamespace(), new zzaxa(this));
      } catch (IllegalStateException | IOException var1) {
         super.zzarw.zza(-1L, 8, (Object)null);
         return;
      }

      zzawy.zzc(this.zzaxd);
      zzawy.zzd(this.zzaxd);
      zzawy.zza(this.zzaxd, (String)null, 1100, (JSONObject)null, super.zzarw);
   }
}
