package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.List;

public final class zzczv extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      zzbo.zzaf(var2.length == 3);
      zzbo.zzaf(var2[1] instanceof dw);
      zzbo.zzaf(var2[2] instanceof dw);
      dp var3 = var2[0];
      List var4 = (List)((dw)var2[1]).zzDs();
      List var5;
      zzbo.zzaf((var5 = (List)((dw)var2[2]).zzDs()).size() <= var4.size() + 1);
      boolean var6 = false;

      dp var8;
      for(int var7 = 0; var7 < var4.size(); ++var7) {
         if (var6 || zzcxp.zzd(var3, ed.zza(var1, (dp)var4.get(var7)))) {
            if ((var8 = ed.zza(var1, (dp)var5.get(var7))) instanceof dv) {
               if (var8 == dv.zzbLs || ((dv)var8).zzDr()) {
                  return var8;
               }

               if (var8 == dv.zzbLr) {
                  return dv.zzbLu;
               }
            } else {
               var6 = true;
            }
         }
      }

      if (var4.size() >= var5.size() || !((var8 = ed.zza(var1, (dp)var5.get(var5.size() - 1))) instanceof dv) || var8 != dv.zzbLs && !((dv)var8).zzDr()) {
         return dv.zzbLu;
      } else {
         return var8;
      }
   }
}
