package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;

public final class l extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      zzbo.zzaf(var2.length > 0 && var2.length <= 3);
      zzbo.zzaf(var2[0] instanceof eb);
      String var3 = (String)((eb)var2[0]).value();
      double var4 = var2.length < 2 ? 0.0D : zzcxp.zzc(var2[1]);
      double var6 = (double)var3.length();
      if (var2.length == 3 && var2[2] != dv.zzbLu) {
         var6 = zzcxp.zzc(var2[2]);
      }

      int var8 = var4 < 0.0D ? (int)Math.max((double)var3.length() + var4, 0.0D) : (int)Math.min(var4, (double)var3.length());
      int var9 = var6 < 0.0D ? (int)Math.max((double)var3.length() + var6, 0.0D) : (int)Math.min(var6, (double)var3.length());
      var9 = var8 + Math.max(0, var9 - var8);
      return new eb(var3.substring(var8, var9));
   }
}
