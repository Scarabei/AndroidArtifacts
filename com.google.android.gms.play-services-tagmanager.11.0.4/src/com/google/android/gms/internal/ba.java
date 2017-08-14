package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.Locale;

public final class ba implements zzcxo {
   public final dp zzb(zzcwa var1, dp... var2) {
      zzbo.zzaf(var2 != null);
      zzbo.zzaf(var2.length == 0);
      Locale var3;
      if ((var3 = Locale.getDefault()) == null) {
         return new eb("");
      } else {
         String var4;
         return (var4 = var3.getLanguage()) == null ? new eb("") : new eb(var4.toLowerCase());
      }
   }
}
