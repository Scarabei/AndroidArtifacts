package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class ds extends dp {
   private final Boolean zzbLn;
   private static final Map zzbLo;

   public ds(Boolean var1) {
      zzbo.zzu(var1);
      this.zzbLn = var1;
   }

   public final Boolean zzDn() {
      return this.zzbLn;
   }

   public final boolean zzga(String var1) {
      return zzbLo.containsKey(var1);
   }

   public final zzcxo zzgb(String var1) {
      if (this.zzga(var1)) {
         return (zzcxo)zzbLo.get(var1);
      } else {
         throw new IllegalStateException((new StringBuilder(54 + String.valueOf(var1).length())).append("Native Method ").append(var1).append(" is not defined for type BooleanWrapper.").toString());
      }
   }

   public final boolean equals(Object var1) {
      boolean var2;
      if (this == var1) {
         var2 = true;
      } else if (var1 instanceof ds) {
         var2 = (Boolean)((ds)var1).zzbLn == this.zzbLn;
      } else {
         var2 = false;
      }

      return var2;
   }

   public final String toString() {
      return this.zzbLn.toString();
   }

   // $FF: synthetic method
   public final Object zzDl() {
      return this.zzbLn;
   }

   static {
      HashMap var0;
      (var0 = new HashMap()).put("hasOwnProperty", zzczp.zzbJW);
      var0.put("toString", new r());
      zzbLo = Collections.unmodifiableMap(var0);
   }
}
