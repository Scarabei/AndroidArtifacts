package com.google.android.gms.internal;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Status;
import org.json.JSONObject;

final class zzaxf implements zzayt {
   // $FF: synthetic field
   private zzawy zzaxj;
   // $FF: synthetic field
   private zzaxe zzaxk;

   zzaxf(zzaxe var1, zzawy var2) {
      this.zzaxk = var1;
      this.zzaxj = var2;
      super();
   }

   public final void zzx(long var1) {
      this.zzaxk.setResult(zzaxe.zzj(new Status(2103)));
   }

   public final void zza(long var1, int var3, Object var4) {
      try {
         if (var4 == null) {
            this.zzaxk.setResult(new zzaxk(new Status(var3, (String)null, (PendingIntent)null), (String)null, var1, (JSONObject)null));
         } else {
            zzaxm var5;
            String var6 = (var5 = (zzaxm)var4).zzaxn;
            if (var3 == 0 && var6 != null) {
               zzawy.zza(this.zzaxk.zzaxd, var6);
            }

            this.zzaxk.setResult(new zzaxk(new Status(var3, var5.zzaxv, (PendingIntent)null), var6, var5.zzaxo, var5.zzaxp));
         }
      } catch (ClassCastException var7) {
         this.zzaxk.setResult(zzaxe.zzj(new Status(13)));
      }
   }
}
