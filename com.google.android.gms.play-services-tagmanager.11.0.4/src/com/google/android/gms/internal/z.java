package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public final class z extends zzcxq {
   private final aa zzbJX;

   public z(aa var1) {
      this.zzbJX = var1;
   }

   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzu(var2);
      zzbo.zzaf(var2.length > 0);
      zzbo.zzaf(var2[0] instanceof eb);
      String var3 = (String)((eb)var2[0]).value();
      HashMap var4 = new HashMap();
      if (var2.length >= 2 && var2[1] != dv.zzbLu) {
         zzbo.zzaf(var2[1] instanceof dz);
         Iterator var5 = ((Map)((dz)var2[1]).zzDt()).entrySet().iterator();

         while(var5.hasNext()) {
            Entry var6;
            zzbo.zzae(!((var6 = (Entry)var5.next()).getValue() instanceof ea));
            zzbo.zzae(!ed.zzm((dp)var6.getValue()));
            var4.put((String)var6.getKey(), ((dp)var6.getValue()).zzDl());
         }
      }

      return ed.zzQ(this.zzbJX.zzd(var3, var4));
   }
}
