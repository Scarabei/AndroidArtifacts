package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;

public final class e extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      zzbo.zzaf(var2.length == 1 || var2.length == 2);
      zzbo.zzaf(var2[0] instanceof eb);
      String var3 = (String)((eb)var2[0]).value();
      int var4 = 0;
      if (var2.length == 2) {
         var4 = (int)zzcxp.zzc(var2[1]);
      }

      return var4 >= 0 && var4 < var3.length() ? new eb(String.valueOf(var3.charAt(var4))) : new eb("");
   }
}
