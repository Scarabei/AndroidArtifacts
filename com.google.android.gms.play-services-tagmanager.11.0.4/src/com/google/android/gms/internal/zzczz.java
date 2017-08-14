package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;

public final class zzczz extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      zzbo.zzaf(var2.length == 2);
      Object var3 = var2[0];
      Object var4 = var2[1];
      if ((!(var3 instanceof dv) || var3 == dv.zzbLu || var3 == dv.zzbLt) && (!(var4 instanceof dv) || var4 == dv.zzbLu || var4 == dv.zzbLt)) {
         if (var3 instanceof dz || var3 instanceof dw || var3 instanceof du) {
            var3 = new eb(zzcxp.zzd((dp)var3));
         }

         if (var4 instanceof dz || var4 instanceof dw || var4 instanceof du) {
            var4 = new eb(zzcxp.zzd((dp)var4));
         }

         if (!(var3 instanceof eb) && !(var4 instanceof eb)) {
            return new dt(zzcxp.zza((dp)var3, (dp)var4));
         } else {
            eb var10000 = new eb;
            String var10002 = String.valueOf(zzcxp.zzd((dp)var3));
            String var10003 = String.valueOf(zzcxp.zzd((dp)var4));
            if (var10003.length() != 0) {
               var10002 = var10002.concat(var10003);
            } else {
               String var10004 = new String;
               var10003 = var10002;
               var10002 = var10004;
               var10004.<init>(var10003);
            }

            var10000.<init>(var10002);
            return var10000;
         }
      } else {
         throw new IllegalArgumentException("Illegal InternalType passed to Add.");
      }
   }
}
