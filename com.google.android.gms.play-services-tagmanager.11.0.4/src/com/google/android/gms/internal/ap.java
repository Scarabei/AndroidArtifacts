package com.google.android.gms.internal;

import android.os.Build;
import com.google.android.gms.common.internal.zzbo;

public final class ap implements zzcxo {
   private final String zzbKb;

   public ap() {
      this.zzbKb = Build.BRAND;
   }

   public final dp zzb(zzcwa var1, dp... var2) {
      zzbo.zzaf(var2 != null);
      zzbo.zzaf(var2.length == 0);
      return new eb(this.zzbKb);
   }
}
