package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;

public final class ac extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      zzbo.zzaf(var2.length == 2);
      zzbo.zzaf(var2[0] instanceof eb);
      String var3 = zzcxp.zzd(var2[1]);
      String var4 = (String)((eb)var2[0]).value();
      byte var5 = -1;
      switch(var4.hashCode()) {
      case 101:
         if (var4.equals("e")) {
            var5 = 0;
         }
         break;
      case 105:
         if (var4.equals("i")) {
            var5 = 1;
         }
         break;
      case 118:
         if (var4.equals("v")) {
            var5 = 2;
         }
         break;
      case 119:
         if (var4.equals("w")) {
            var5 = 3;
         }
      }

      switch(var5) {
      case 0:
         zzcvk.e(var3);
         break;
      case 1:
         zzcvk.zzaS(var3);
         break;
      case 2:
         zzcvk.v(var3);
         break;
      case 3:
         zzcvk.zzaT(var3);
         break;
      default:
         IllegalArgumentException var10000 = new IllegalArgumentException;
         String var10003 = String.valueOf((String)((eb)var2[0]).value());
         String var10002;
         if (var10003.length() != 0) {
            var10002 = "Invalid logging level: ".concat(var10003);
         } else {
            String var10004 = new String;
            var10002 = var10004;
            var10004.<init>("Invalid logging level: ");
         }

         var10000.<init>(var10002);
         throw var10000;
      }

      return dv.zzbLu;
   }
}
