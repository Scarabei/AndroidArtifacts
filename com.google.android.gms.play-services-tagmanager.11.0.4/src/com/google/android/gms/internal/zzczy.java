package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.List;

public final class zzczy implements zzcxo {
   public final dp zzb(zzcwa var1, dp... var2) {
      zzbo.zzaf(var2 != null);
      zzbo.zzaf(var2.length == 4);
      dp var3;
      zzbo.zzaf((var3 = ed.zza(var1, var2[3])) instanceof dw);
      List var4 = (List)((dw)var3).zzDs();
      dp var5;
      zzbo.zzaf((var5 = var2[2]) instanceof ds);
      dv var6;
      if (((Boolean)((ds)var5).zzDn()).booleanValue()) {
         if ((var6 = ed.zza(var1, var4)) == dv.zzbLr) {
            return dv.zzbLu;
         }

         if (var6.zzDr()) {
            return var6;
         }
      }

      while(zzcxp.zza(ed.zza(var1, var2[0]))) {
         if ((var6 = ed.zza(var1, var4)) == dv.zzbLr) {
            return dv.zzbLu;
         }

         if (var6.zzDr()) {
            return var6;
         }

         ed.zza(var1, var2[1]);
      }

      return dv.zzbLu;
   }
}
