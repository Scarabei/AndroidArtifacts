package com.google.android.gms.internal;

import android.content.Context;
import java.util.Map;
import org.json.JSONException;

final class zzach implements zzrd {
   // $FF: synthetic field
   private Context zztF;
   // $FF: synthetic field
   private zzabu zzUA;
   // $FF: synthetic field
   private zzacg zzWj;

   zzach(zzacg var1, Context var2, zzabu var3) {
      this.zzWj = var1;
      this.zztF = var2;
      this.zzUA = var3;
      super();
   }

   public final void zza(zzaka var1, Map var2) {
      try {
         zzmo.zza(this.zztF, 1, com.google.android.gms.ads.internal.zzbs.zzbz().zzj(var2));
         zzacg.zza(this.zzWj);
         return;
      } catch (JSONException var7) {
         zzafr.zzb("Unable to save SDK Core Constants.", var7);
      } finally {
         var1.zziw().zzb("/loadSdkConstants", this);
         zzacg.zze(this.zzUA);
      }

   }
}
