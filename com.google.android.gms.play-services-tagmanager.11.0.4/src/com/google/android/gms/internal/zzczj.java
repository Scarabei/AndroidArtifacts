package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.HashMap;

public final class zzczj extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      HashMap var3 = new HashMap();

      for(int var4 = 0; var4 < var2.length - 1; var4 += 2) {
         String var5 = zzcxp.zzd(var2[var4]);
         dp var6;
         if ((var6 = var2[var4 + 1]) instanceof dv && var6 != dv.zzbLt && var6 != dv.zzbLu) {
            throw new IllegalStateException("Illegal InternalType found in CreateObject.");
         }

         var3.put(var5, var6);
      }

      return new dz(var3);
   }
}
