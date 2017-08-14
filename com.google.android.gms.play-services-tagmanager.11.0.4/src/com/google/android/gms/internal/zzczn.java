package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;

public final class zzczn implements zzcxo {
   private static zzcvu zzbHP;

   public zzczn(zzcvu var1) {
      zzbHP = var1;
   }

   public final dp zzb(zzcwa var1, dp... var2) {
      zzbo.zzaf(var2 != null);
      zzbo.zzaf(var2.length == 1);
      zzbo.zzaf(var2[0] instanceof eb);
      return zzbHP.zzfH((String)((eb)var2[0]).value());
   }
}
