package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;

public final class bl extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      zzbo.zzaf(var2.length == 2);
      String var3 = zzcxp.zzd(var2[0]);
      String var4 = zzcxp.zzd(var2[1]);
      return new ds(var3.contains(var4));
   }
}
