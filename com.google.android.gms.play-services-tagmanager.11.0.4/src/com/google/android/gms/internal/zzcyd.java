package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.Collections;
import java.util.List;

public final class zzcyd extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzu(var2);
      zzbo.zzaf(var2.length == 1);
      zzbo.zzaf(var2[0] instanceof dw);
      Collections.reverse((List)((dw)var2[0]).zzDs());
      return var2[0];
   }
}
