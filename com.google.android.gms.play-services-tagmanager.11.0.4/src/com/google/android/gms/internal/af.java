package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.internal.zzbo;

public final class af implements zzcxo {
   private final zzcua zzbJY;

   public af(Context var1) {
      this(zzcua.zzbu(var1));
   }

   private af(zzcua var1) {
      this.zzbJY = var1;
   }

   public final dp zzb(zzcwa var1, dp... var2) {
      zzbo.zzaf(var2 != null);
      zzbo.zzaf(var2.length == 0);
      return new ds(!this.zzbJY.isLimitAdTrackingEnabled());
   }
}
