package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public final class bt extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      zzbo.zzaf(var2.length == 2 || var2.length == 3);
      String var3 = zzcxp.zzd(var2[0]);
      String var4 = zzcxp.zzd(var2[1]);
      boolean var5 = var2.length < 3 ? false : "true".equalsIgnoreCase(zzcxp.zzd(var2[2]));
      byte var6 = 64;
      if (var5) {
         var6 = 66;
      }

      try {
         return new ds(Pattern.compile(var4, var6).matcher(var3).find());
      } catch (PatternSyntaxException var7) {
         return new ds(false);
      }
   }
}
