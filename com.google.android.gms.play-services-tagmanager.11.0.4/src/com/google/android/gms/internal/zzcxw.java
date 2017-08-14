package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class zzcxw extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzu(var2);
      zzbo.zzaf(var2.length == 1 || var2.length == 2);
      zzbo.zzaf(var2[0] instanceof dw);
      List var3 = (List)((dw)var2[0]).zzDs();
      Object var4;
      String var5 = (var4 = var2.length < 2 ? dv.zzbLu : var2[1]) == dv.zzbLu ? "," : zzcxp.zzd((dp)var4);
      ArrayList var6 = new ArrayList();
      Iterator var7 = var3.iterator();

      while(true) {
         while(var7.hasNext()) {
            dp var8;
            if ((var8 = (dp)var7.next()) != dv.zzbLt && var8 != dv.zzbLu) {
               var6.add(zzcxp.zzd(var8));
            } else {
               var6.add("");
            }
         }

         return new eb(TextUtils.join(var5, var6));
      }
   }
}
