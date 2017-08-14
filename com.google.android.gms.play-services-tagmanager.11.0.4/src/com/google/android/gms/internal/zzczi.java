package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.ArrayList;

public final class zzczi extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      ArrayList var3 = new ArrayList();
      dp[] var4 = var2;
      int var5 = var2.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         dp var7;
         zzbo.zzaf(!((var7 = var4[var6]) instanceof dv) || var7 == dv.zzbLu || var7 == dv.zzbLt);
         var3.add(var7);
      }

      return new dw(var3);
   }
}
