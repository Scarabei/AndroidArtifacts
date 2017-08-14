package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;

public final class zzczm implements zzcxo {
   public final dp zzb(zzcwa var1, dp... var2) {
      zzbo.zzaf(var2 != null);
      zzbo.zzaf(var2.length == 1);
      zzbo.zzaf(var2[0] instanceof eb);
      dp var3;
      if ((var3 = var1.zzfK((String)((eb)var2[0]).value())) instanceof ea) {
         throw new IllegalStateException("Illegal Statement type encountered in Get.");
      } else if (var3 instanceof dv && var3 != dv.zzbLu && var3 != dv.zzbLt) {
         throw new IllegalStateException("Illegal InternalType encountered in Get.");
      } else {
         return var3;
      }
   }
}
