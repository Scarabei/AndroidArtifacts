package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.ArrayList;
import java.util.List;

public final class zzcyf extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzu(var2);
      zzbo.zzaf(var2.length == 2 || var2.length == 3);
      zzbo.zzaf(var2[0] instanceof dw);
      dw var3 = (dw)var2[0];
      int var4;
      if ((var4 = (int)zzcxp.zzc(var2[1])) < 0) {
         var4 = Math.max(((List)var3.zzDs()).size() + var4, 0);
      } else {
         var4 = Math.min(var4, ((List)var3.zzDs()).size());
      }

      int var5 = ((List)var3.zzDs()).size();
      if (var2.length == 3) {
         if ((var5 = (int)zzcxp.zzc(var2[2])) < 0) {
            var5 = Math.max(var5 + ((List)var3.zzDs()).size(), 0);
         } else {
            var5 = Math.min(var5, ((List)var3.zzDs()).size());
         }
      }

      var5 = Math.max(var4, var5);
      return new dw(new ArrayList(((List)var3.zzDs()).subList(var4, var5)));
   }
}
