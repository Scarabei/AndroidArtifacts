package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.util.zzf;
import java.util.Map;

@zzzn
public final class zzrp implements zzrd {
   private final zzw zzJE;
   private final zzwk zzJF;
   private static Map zzJG = zzf.zza("resize", Integer.valueOf(1), "playVideo", Integer.valueOf(2), "storePicture", Integer.valueOf(3), "createCalendarEvent", Integer.valueOf(4), "setOrientationProperties", Integer.valueOf(5), "closeResizedAd", Integer.valueOf(6));

   public zzrp(zzw var1, zzwk var2) {
      this.zzJE = var1;
      this.zzJF = var2;
   }

   public final void zza(zzaka var1, Map var2) {
      String var3 = (String)var2.get("a");
      int var4;
      if ((var4 = ((Integer)zzJG.get(var3)).intValue()) != 5 && this.zzJE != null && !this.zzJE.zzaR()) {
         this.zzJE.zzt((String)null);
      } else {
         switch(var4) {
         case 1:
            this.zzJF.execute(var2);
            return;
         case 2:
         default:
            zzafr.zzaS("Unknown MRAID command called.");
            return;
         case 3:
            (new zzwn(var1, var2)).execute();
            return;
         case 4:
            (new zzwh(var1, var2)).execute();
            return;
         case 5:
            (new zzwm(var1, var2)).execute();
            return;
         case 6:
            this.zzJF.zzk(true);
         }
      }
   }
}
