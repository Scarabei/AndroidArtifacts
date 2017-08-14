package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.Iterator;
import java.util.List;

public final class zzczg extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      zzbo.zzaf(var2.length == 1);
      zzbo.zzaf(var2[0] instanceof dw);
      Iterator var3 = ((List)((dw)var2[0]).zzDs()).iterator();

      dp var4;
      dp var5;
      do {
         do {
            if (!var3.hasNext()) {
               return dv.zzbLu;
            }

            var4 = (dp)var3.next();
         } while(!((var5 = ed.zza(var1, var4)) instanceof dv));
      } while(var5 != dv.zzbLr && var5 != dv.zzbLs && !((dv)var5).zzDr());

      return var5;
   }
}
