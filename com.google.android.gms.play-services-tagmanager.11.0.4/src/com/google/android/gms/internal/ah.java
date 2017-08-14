package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.internal.zzbo;

public final class ah implements zzcxo {
   private final Context mContext;

   public ah(Context var1) {
      this.mContext = var1;
   }

   public final dp zzb(zzcwa var1, dp... var2) {
      zzbo.zzaf(var2 != null);
      zzbo.zzaf(var2.length == 0);
      return new eb(this.mContext.getPackageName());
   }
}
