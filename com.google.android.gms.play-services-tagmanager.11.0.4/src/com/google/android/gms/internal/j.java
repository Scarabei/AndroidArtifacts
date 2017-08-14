package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;

public final class j extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      zzbo.zzaf(var2.length > 0 && var2.length <= 3);
      zzbo.zzaf(var2[0] instanceof eb);
      String var3 = (String)((eb)var2[0]).value();
      if (var2.length == 1) {
         return new eb(var3);
      } else {
         String var4 = zzcxp.zzd(var2[1]);
         Object var5 = var2.length < 3 ? dv.zzbLu : var2[2];
         int var6;
         if ((var6 = var3.indexOf(var4)) == -1) {
            return new eb(var3);
         } else {
            if (var5 instanceof du) {
               var5 = ((zzcxo)((du)var5).zzDp()).zzb(var1, new eb(var4), new dt((double)var6), new eb(var3));
            }

            String var7 = zzcxp.zzd((dp)var5);
            String var8 = String.valueOf(var3.substring(0, var6));
            String var9 = String.valueOf(var3.substring(var6 + var4.length()));
            return new eb((new StringBuilder(String.valueOf(var8).length() + String.valueOf(var7).length() + String.valueOf(var9).length())).append(var8).append(var7).append(var9).toString());
         }
      }
   }
}
