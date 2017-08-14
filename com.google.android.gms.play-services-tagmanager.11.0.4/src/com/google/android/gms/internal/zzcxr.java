package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class zzcxr extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzu(var2);
      zzbo.zzaf(var2.length > 0);
      zzbo.zzaf(var2[0] instanceof dw);
      dw var3 = (dw)var2[0];
      ArrayList var4 = new ArrayList();
      Iterator var5 = ((List)var3.zzDs()).iterator();

      while(var5.hasNext()) {
         dp var6 = (dp)var5.next();
         var4.add(var6);
      }

      for(int var8 = 1; var8 < var2.length; ++var8) {
         if (var2[var8] instanceof dw) {
            Iterator var9 = ((List)((dw)var2[var8]).zzDs()).iterator();

            while(var9.hasNext()) {
               dp var7 = (dp)var9.next();
               var4.add(var7);
            }
         } else {
            var4.add(var2[var8]);
         }
      }

      return new dw(var4);
   }
}
