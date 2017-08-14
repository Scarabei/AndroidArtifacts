package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.List;

public final class zzcya extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzu(var2);
      zzbo.zzaf(var2.length > 0);
      zzbo.zzaf(var2[0] instanceof dw);
      dw var3;
      int var4 = ((List)(var3 = (dw)var2[0]).zzDs()).size();
      var3.setSize(var4 + var2.length - 1);

      for(int var5 = 1; var5 < var2.length; ++var5) {
         var3.zza(var4, var2[var5]);
         ++var4;
      }

      return new dt((double)var4);
   }
}
