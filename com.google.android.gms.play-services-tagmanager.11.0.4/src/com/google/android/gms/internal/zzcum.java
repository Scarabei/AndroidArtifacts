package com.google.android.gms.internal;

import android.support.annotation.WorkerThread;
import java.util.Iterator;
import java.util.List;

final class zzcum implements Runnable {
   private final cm zzbHU;
   // $FF: synthetic field
   private zzcuf zzbHS;

   zzcum(zzcuf var1, cm var2) {
      this.zzbHS = var1;
      super();
      this.zzbHU = var2;
   }

   @WorkerThread
   public final void run() {
      db var1 = this.zzbHU.zzCQ().zzCV();
      dj var2 = this.zzbHU.zzCR();
      boolean var3 = zzcuf.zzb(this.zzbHS) == null;
      zzcuf.zza(this.zzbHS, zzcuf.zzm(this.zzbHS).zza(var1, var2));
      zzcuf.zza(this.zzbHS, 2);
      String var4 = zzcuf.zzd(this.zzbHS);
      zzcvk.v((new StringBuilder(48 + String.valueOf(var4).length())).append("Container ").append(var4).append(" loaded during runtime initialization.").toString());
      String var10000;
      String var10001;
      String var10002;
      if (zzcuf.zzi(this.zzbHS) != null) {
         Iterator var6 = zzcuf.zzi(this.zzbHS).iterator();

         while(var6.hasNext()) {
            zzcut var5 = (zzcut)var6.next();
            var10001 = String.valueOf(var5.zzCk());
            if (var10001.length() != 0) {
               var10000 = "Evaluating tags for pending event ".concat(var10001);
            } else {
               var10002 = new String;
               var10000 = var10002;
               var10002.<init>("Evaluating tags for pending event ");
            }

            zzcvk.v(var10000);
            zzcuf.zzb(this.zzbHS).zzb(var5);
         }

         zzcuf.zza(this.zzbHS, (List)null);
      }

      zzcuf.zzb(this.zzbHS).dispatch();
      var10001 = String.valueOf(zzcuf.zzd(this.zzbHS));
      if (var10001.length() != 0) {
         var10000 = "Runtime initialized successfully for container ".concat(var10001);
      } else {
         var10002 = new String;
         var10000 = var10002;
         var10002.<init>("Runtime initialized successfully for container ");
      }

      zzcvk.v(var10000);
      long var7 = this.zzbHU.zzCQ().zzCW() + zzcuf.zzg(this.zzbHS).zzAS();
      if (var3 && zzcuf.zzl(this.zzbHS) && this.zzbHU.getSource() == 1 && var7 < zzcuf.zzn(this.zzbHS).currentTimeMillis()) {
         zzcuf.zza(this.zzbHS, zzcuf.zzg(this.zzbHS).zzCi());
      } else {
         zzcuf.zza(this.zzbHS, Math.max(900000L, var7 - zzcuf.zzn(this.zzbHS).currentTimeMillis()));
      }
   }
}
