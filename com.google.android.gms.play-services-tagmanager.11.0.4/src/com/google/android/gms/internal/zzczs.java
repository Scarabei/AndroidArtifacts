package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.ArrayList;

public final class zzczs implements zzcxo {
   public final dp zzb(zzcwa var1, dp... var2) {
      zzbo.zzaf(var2 != null);
      ArrayList var3 = new ArrayList(var2.length);
      dp[] var4 = var2;
      int var5 = var2.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         dp var7 = var4[var6];
         var3.add(var7);
      }

      return new dw(var3);
   }
}
