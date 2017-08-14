package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;

public final class zzczx implements zzcxo {
   public final dp zzb(zzcwa var1, dp... var2) {
      zzbo.zzaf(var2 != null);
      zzbo.zzaf(var2.length > 0);
      dp[] var3 = var2;
      int var4 = var2.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         dp var6;
         zzbo.zzu(var6 = var3[var5]);
         zzbo.zzaf(var6 instanceof eb);
         var1.zza((String)((eb)var6).value(), dv.zzbLu);
      }

      return dv.zzbLu;
   }
}
