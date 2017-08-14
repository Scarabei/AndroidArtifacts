package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.List;

public final class zzcyc extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzu(var2);
      zzbo.zzaf(var2.length == 2 || var2.length == 3);
      zzbo.zzaf(var2[0] instanceof dw);
      zzbo.zzaf(var2[1] instanceof du);
      dw var3 = (dw)var2[0];
      du var4 = (du)var2[1];
      List var5;
      int var6 = (var5 = (List)var3.zzDs()).size();
      dp var7;
      int var8;
      int var9;
      if (var2.length == 3) {
         var7 = var2[2];
         var8 = var6 - 1;
      } else {
         zzbo.zzae(var6 > 0);
         var7 = var3.zzbG(var6 - 1);
         var8 = var6 - 2;

         for(var9 = var6 - 1; var9 >= 0; --var9) {
            if (var3.zzbH(var9)) {
               var7 = var3.zzbG(var9);
               var8 = var9 - 1;
               break;
            }
         }

         zzbo.zzae(var9 >= 0);
      }

      for(var9 = var8; var9 >= 0; --var9) {
         if (var3.zzbH(var9)) {
            var7 = ((zzcxo)var4.zzDp()).zzb(var1, var7, (dp)var5.get(var9), new dt((double)var9), var3);
         }
      }

      return var7;
   }
}
