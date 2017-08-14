package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.Iterator;
import java.util.List;

public final class zzczl extends zzcxq {
   public final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzu(var2);
      zzbo.zzaf(var2.length == 3);
      zzbo.zzaf(var2[0] instanceof eb);
      String var3 = (String)((eb)var2[0]).value();
      zzbo.zzaf(var1.has(var3));
      dp var4;
      zzbo.zzu(var4 = var2[1]);
      dp var5;
      zzbo.zzaf((var5 = var2[2]) instanceof dw);
      List var6 = (List)((dw)var5).zzDs();
      Iterator var7 = var4.zzDk();

      dv var9;
      do {
         if (!var7.hasNext()) {
            return dv.zzbLu;
         }

         dp var8 = (dp)var7.next();
         var1.zzb(var3, var8);
         if ((var9 = ed.zza(var1, var6)) == dv.zzbLr) {
            return dv.zzbLu;
         }
      } while(!var9.zzDr());

      return var9;
   }
}
