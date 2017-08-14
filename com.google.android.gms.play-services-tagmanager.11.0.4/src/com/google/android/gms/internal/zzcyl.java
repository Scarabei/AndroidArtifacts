package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.ArrayList;
import java.util.List;

public final class zzcyl extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzu(var2);
      zzbo.zzaf(var2.length >= 3);
      zzbo.zzaf(var2[0] instanceof dw);
      dw var3 = (dw)var2[0];
      int var4;
      if ((var4 = (int)zzcxp.zzc(var2[1])) < 0) {
         var4 = Math.max(((List)var3.zzDs()).size() + var4, 0);
      } else {
         var4 = Math.min(var4, ((List)var3.zzDs()).size());
      }

      int var5 = Math.min(Math.max((int)zzcxp.zzc(var2[2]), 0), ((List)var3.zzDs()).size() - var4);
      int var6 = var4 + var5;
      ArrayList var7 = new ArrayList(((List)var3.zzDs()).subList(var4, var6));
      ((List)var3.zzDs()).subList(var4, var6).clear();
      ArrayList var8 = new ArrayList();

      for(int var9 = 3; var9 < var2.length; ++var9) {
         var8.add(var2[var9]);
      }

      ((List)var3.zzDs()).addAll(var4, var8);
      return new dw(var7);
   }
}
