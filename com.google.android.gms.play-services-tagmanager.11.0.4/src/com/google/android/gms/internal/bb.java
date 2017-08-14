package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.Locale;

public final class bb implements zzcxo {
   public final dp zzb(zzcwa var1, dp... var2) {
      zzbo.zzaf(var2 != null);
      zzbo.zzaf(var2.length == 0);
      return new eb(Locale.getDefault().getCountry());
   }
}
