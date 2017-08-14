package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.ArrayList;
import java.util.List;

public final class zzcym extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzu(var2);
      zzbo.zzaf(var2.length > 0);
      zzbo.zzaf(var2[0] instanceof dw);
      dw var3 = (dw)var2[0];
      ArrayList var4 = new ArrayList();

      for(int var5 = 1; var5 < var2.length; ++var5) {
         var4.add(var2[var5]);
      }

      ((List)var3.zzDs()).addAll(0, var4);
      return new dt((double)((List)var3.zzDs()).size());
   }
}
