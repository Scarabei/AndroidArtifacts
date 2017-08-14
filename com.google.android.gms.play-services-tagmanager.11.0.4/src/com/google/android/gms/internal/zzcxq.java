package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;

public abstract class zzcxq implements zzcxo {
   public final dp zzb(zzcwa var1, dp... var2) {
      zzbo.zzaf(var1 != null);
      zzbo.zzaf(var2 != null);
      dp[] var3 = new dp[var2.length];

      for(int var4 = 0; var4 < var2.length; ++var4) {
         zzbo.zzaf(var2[var4] != null);
         zzbo.zzaf(var2[var4] != dv.zzbLr);
         zzbo.zzaf(var2[var4] != dv.zzbLs);
         var3[var4] = ed.zza(var1, var2[var4]);
         zzbo.zzaf(var3[var4] != null);
         zzbo.zzaf(var3[var4] != dv.zzbLr);
         zzbo.zzaf(var3[var4] != dv.zzbLs);
      }

      dp var5;
      zzbo.zzae((var5 = this.zza(var1, var3)) != null);
      return var5;
   }

   protected abstract dp zza(zzcwa var1, dp... var2);
}
