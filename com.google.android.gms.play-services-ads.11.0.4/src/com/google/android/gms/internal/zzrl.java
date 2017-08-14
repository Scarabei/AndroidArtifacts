package com.google.android.gms.internal;

import java.util.Map;

@zzzn
public final class zzrl implements zzrd {
   private final zzrm zzJC;

   public zzrl(zzrm var1) {
      this.zzJC = var1;
   }

   public final void zza(zzaka var1, Map var2) {
      boolean var3 = "1".equals(var2.get("transparentBackground"));
      boolean var4 = "1".equals(var2.get("blur"));
      float var5 = 0.0F;

      try {
         if (var2.get("blurRadius") != null) {
            var5 = Float.parseFloat((String)var2.get("blurRadius"));
         }
      } catch (NumberFormatException var7) {
         zzafr.zzb("Fail to parse float", var7);
      }

      this.zzJC.zzc(var3);
      this.zzJC.zza(var4, var5);
   }
}
