package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.Locale;

public final class q extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      zzbo.zzaf(var2.length == 1);
      zzbo.zzaf(var2[0] instanceof eb);
      String var3 = (String)((eb)var2[0]).value();
      return new eb(var3.toLowerCase(Locale.ENGLISH));
   }
}
