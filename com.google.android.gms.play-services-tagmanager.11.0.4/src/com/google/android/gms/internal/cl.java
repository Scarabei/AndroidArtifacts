package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;

public final class cl {
   private bz zzbKF;

   public final cl zza(bz var1) throws IllegalArgumentException {
      zzbo.zzu(var1);
      this.zzbKF = var1;
      return this;
   }

   public final bz zzCP() {
      return this.zzbKF;
   }

   public final String getId() {
      return this.zzbKF == null ? "" : this.zzbKF.getContainerId();
   }
}
