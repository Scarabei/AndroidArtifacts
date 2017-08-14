package com.google.android.gms.internal;

import android.os.Build.VERSION;
import com.google.android.gms.common.internal.zzbo;

public final class bi implements zzcxo {
   public final dp zzb(zzcwa var1, dp... var2) {
      zzbo.zzaf(var2 != null);
      zzbo.zzaf(var2.length == 0);
      String var3 = String.valueOf("5.05-");
      int var4 = VERSION.SDK_INT;
      return new eb((new StringBuilder(11 + String.valueOf(var3).length())).append(var3).append(var4).toString());
   }
}
