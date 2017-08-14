package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;

public final class zzczb implements zzcxo {
   public final dp zzb(zzcwa var1, dp... var2) {
      zzbo.zzaf(var2 != null);
      zzbo.zzaf(var2.length == 3);
      dp var3;
      if ((var3 = zzcxp.zza(ed.zza(var1, var2[0])) ? ed.zza(var1, var2[1]) : ed.zza(var1, var2[2])) instanceof dv && var3 != dv.zzbLu && var3 != dv.zzbLt) {
         throw new IllegalArgumentException("Illegal InternalType passed to Ternary.");
      } else {
         return var3;
      }
   }
}
