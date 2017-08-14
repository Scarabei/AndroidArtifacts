package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.ArrayList;

public final class m extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      zzbo.zzaf(var2.length == 1 || var2.length == 2);
      zzbo.zzaf(var2[0] instanceof eb);
      ArrayList var3 = new ArrayList();
      if (var2.length == 1) {
         var3.add(var2[0]);
      } else {
         String var4 = (String)((eb)var2[0]).value();
         String var5;
         boolean var6 = (var5 = zzcxp.zzd(var2[1])).equals("");
         String[] var7 = var4.split(var5, var6 ? 0 : -1);

         for(int var8 = var6 && var7.length > 0 && var7[0].equals("") ? 1 : 0; var8 < var7.length; ++var8) {
            var3.add(new eb(var7[var8]));
         }
      }

      return new dw(var3);
   }
}
