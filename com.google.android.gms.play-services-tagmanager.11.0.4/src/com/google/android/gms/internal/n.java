package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;

public final class n extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      zzbo.zzaf(var2.length > 0 && var2.length <= 3);
      zzbo.zzaf(var2[0] instanceof eb);
      String var3 = (String)((eb)var2[0]).value();
      int var4 = (int)zzcxp.zzc((dp)(var2.length < 2 ? dv.zzbLu : var2[1]));
      int var5 = var3.length();
      if (var2.length == 3 && var2[2] != dv.zzbLu) {
         var5 = (int)zzcxp.zzc(ed.zza(var1, var2[2]));
      }

      int var6 = Math.min(Math.max(var4, 0), var3.length());
      int var7 = Math.min(Math.max(var5, 0), var3.length());
      return new eb(var3.substring(Math.min(var6, var7), Math.max(var6, var7)));
   }
}
