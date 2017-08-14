package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.ArrayList;
import java.util.List;

public final class zzcxy extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzu(var2);
      zzbo.zzaf(var2.length == 2);
      zzbo.zzaf(var2[0] instanceof dw);
      zzbo.zzaf(var2[1] instanceof du);
      dw var3 = (dw)var2[0];
      du var4 = (du)var2[1];
      List var5;
      int var6 = (var5 = (List)var3.zzDs()).size();
      ArrayList var7 = new ArrayList();

      for(int var8 = 0; var8 < var6 && var8 < ((List)var3.zzDs()).size(); ++var8) {
         dp var9 = null;
         if (var3.zzbH(var8)) {
            zzbo.zzae(!ed.zzm(var9 = ((zzcxo)var4.zzDp()).zzb(var1, (dp)var5.get(var8), new dt((double)var8), var3)));
         }

         var7.add(var9);
      }

      return new dw(var7);
   }
}
