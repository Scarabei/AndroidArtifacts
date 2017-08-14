package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.List;

public final class zzczd extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      zzbo.zzaf(var2.length == 3);
      zzbo.zzaf(var2[1] instanceof eb);
      zzbo.zzaf(var2[2] instanceof dw);
      dp var3 = var2[0];
      String var4 = (String)((eb)var2[1]).value();
      List var5 = (List)((dw)var2[2]).zzDs();
      if (var3.zzfY(var4)) {
         dp var7;
         if ((var7 = var3.zzfZ(var4)) instanceof du) {
            return ((zzcxo)((du)var7).zzDp()).zzb(var1, (dp[])var5.toArray(new dp[var5.size()]));
         } else {
            throw new IllegalArgumentException((new StringBuilder(35 + String.valueOf(var4).length())).append("Apply TypeError: ").append(var4).append(" is not a function").toString());
         }
      } else if (var3.zzga(var4)) {
         zzcxo var6 = var3.zzgb(var4);
         var5.add(0, var3);
         return var6.zzb(var1, (dp[])var5.toArray(new dp[var5.size()]));
      } else {
         throw new IllegalArgumentException((new StringBuilder(40 + String.valueOf(var4).length())).append("Apply TypeError: object has no ").append(var4).append(" property").toString());
      }
   }
}
