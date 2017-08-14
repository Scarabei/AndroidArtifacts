package com.google.android.gms.internal;

import android.content.Context;
import java.util.concurrent.Callable;

final class zzace implements Callable {
   // $FF: synthetic field
   private Context zztF;
   // $FF: synthetic field
   private zzacd zzWf;

   zzace(zzacd var1, Context var2) {
      this.zzWf = var1;
      this.zztF = var2;
      super();
   }

   // $FF: synthetic method
   public final Object call() throws Exception {
      zzacb var3;
      label19: {
         zzacf var2;
         if ((var2 = (zzacf)zzacd.zza(this.zzWf).get(this.zztF)) != null) {
            zzme var5 = zzmo.zzEu;
            if (var2.zzWg + ((Long)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var5)).longValue() >= com.google.android.gms.ads.internal.zzbs.zzbF().currentTimeMillis()) {
               zzme var4 = zzmo.zzEt;
               if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var4)).booleanValue()) {
                  var3 = (new zzacc(this.zztF, var2.zzWh)).zzgM();
                  break label19;
               }
            }
         }

         var3 = (new zzacc(this.zztF)).zzgM();
      }

      zzacd.zza(this.zzWf).put(this.zztF, new zzacf(this.zzWf, var3));
      return var3;
   }
}
