package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;

public final class zzcze extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      zzbo.zzaf(var2.length == 2);
      zzbo.zzaf(var2[0] instanceof eb);
      String var3 = (String)((eb)var2[0]).value();
      if (var1.has(var3)) {
         var1.zzb(var3, var2[1]);
         return var2[1];
      } else {
         IllegalStateException var10000 = new IllegalStateException;
         String var10003 = String.valueOf(var3);
         String var10002;
         if (var10003.length() != 0) {
            var10002 = "Attempting to assign to undefined variable ".concat(var10003);
         } else {
            String var10004 = new String;
            var10002 = var10004;
            var10004.<init>("Attempting to assign to undefined variable ");
         }

         var10000.<init>(var10002);
         throw var10000;
      }
   }
}
