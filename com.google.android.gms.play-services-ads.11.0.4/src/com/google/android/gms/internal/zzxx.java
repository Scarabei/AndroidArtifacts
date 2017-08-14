package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zza;
import com.google.android.gms.ads.internal.zzbb;
import com.google.android.gms.common.util.zzq;

@zzzn
public final class zzxx {
   public static zzahp zza(Context var0, zza var1, zzafg var2, zzcu var3, @Nullable zzaka var4, zzuq var5, zzxy var6, zznb var7) {
      zzaai var9 = var2.zzXY;
      Object var8;
      if (var2.zzXY.zzTo) {
         var8 = new zzyd(var0, var2, var5, var6, var7, var4);
      } else if (!var9.zzAv && !(var1 instanceof zzbb)) {
         zzme var10 = zzmo.zzDm;
         if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var10)).booleanValue() && zzq.zzsc() && !zzq.zzse() && var4 != null && var4.zzam().zzAt) {
            var8 = new zzyc(var0, var2, var4, var6);
         } else {
            var8 = new zzxz(var0, var2, var4, var6);
         }
      } else if (var9.zzAv && var1 instanceof zzbb) {
         var8 = new zzyf(var0, (zzbb)var1, var2, var3, var6, var7);
      } else {
         var8 = new zzya(var2, var6);
      }

      String var10001 = String.valueOf(var8.getClass().getName());
      String var10000;
      if (var10001.length() != 0) {
         var10000 = "AdRenderer: ".concat(var10001);
      } else {
         String var10002 = new String;
         var10000 = var10002;
         var10002.<init>("AdRenderer: ");
      }

      zzafr.zzaC(var10000);
      ((zzahp)var8).zzgp();
      return (zzahp)var8;
   }
}
