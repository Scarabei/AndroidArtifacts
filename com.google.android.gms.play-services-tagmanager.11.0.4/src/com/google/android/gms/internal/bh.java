package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public final class bh extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      zzbo.zzaf(var2.length >= 2);
      if (var2[0] != dv.zzbLu && var2[1] != dv.zzbLu) {
         String var3 = zzcxp.zzd(var2[0]);
         String var4 = zzcxp.zzd(var2[1]);
         byte var5 = 64;
         if (var2.length > 2 && var2[2] != dv.zzbLu && zzcxp.zza(var2[2])) {
            var5 = 66;
         }

         int var6 = 1;
         if (var2.length > 3 && var2[3] != dv.zzbLu) {
            if (!(var2[3] instanceof dt)) {
               return dv.zzbLu;
            }

            double var7;
            if (Double.isInfinite(var7 = zzcxp.zzc(var2[3])) || var7 < 0.0D) {
               return dv.zzbLu;
            }

            var6 = (int)var7;
         }

         try {
            String var10 = null;
            Matcher var8;
            if ((var8 = Pattern.compile(var4, var5).matcher(var3)).find() && var8.groupCount() >= var6) {
               var10 = var8.group(var6);
            }

            if (var10 == null) {
               return dv.zzbLu;
            } else {
               return new eb(var10);
            }
         } catch (PatternSyntaxException var9) {
            return dv.zzbLu;
         }
      } else {
         return dv.zzbLu;
      }
   }
}
