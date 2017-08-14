package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class dt extends dp {
   private Double zzbLp;
   private static final Map zzbLo;

   public dt(Double var1) {
      zzbo.zzu(var1);
      this.zzbLp = var1;
   }

   public final Double zzDo() {
      return this.zzbLp;
   }

   public final boolean zzga(String var1) {
      return zzbLo.containsKey(var1);
   }

   public final zzcxo zzgb(String var1) {
      if (this.zzga(var1)) {
         return (zzcxo)zzbLo.get(var1);
      } else {
         throw new IllegalStateException((new StringBuilder(53 + String.valueOf(var1).length())).append("Native Method ").append(var1).append(" is not defined for type DoubleWrapper.").toString());
      }
   }

   public final boolean equals(Object var1) {
      boolean var2;
      if (this == var1) {
         var2 = true;
      } else if (var1 instanceof dt) {
         var2 = this.zzbLp.equals((Double)((dt)var1).zzbLp);
      } else {
         var2 = false;
      }

      return var2;
   }

   public final String toString() {
      return this.zzbLp.toString();
   }

   // $FF: synthetic method
   public final Object zzDl() {
      return this.zzbLp;
   }

   static {
      HashMap var0;
      (var0 = new HashMap()).put("hasOwnProperty", zzczp.zzbJW);
      var0.put("toString", new r());
      zzbLo = Collections.unmodifiableMap(var0);
   }
}
