package com.google.android.gms.internal;

import android.os.Build;
import com.google.android.gms.common.internal.zzbo;

public final class ar implements zzcxo {
   private final String zzaUg;

   public ar() {
      this.zzaUg = Build.MODEL;
   }

   public final dp zzb(zzcwa var1, dp... var2) {
      zzbo.zzaf(var2 != null);
      zzbo.zzaf(var2.length == 0);
      return new eb(this.zzaUg);
   }
}
