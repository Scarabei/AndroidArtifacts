package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class dz extends dp {
   private static final Map zzbLo;
   private boolean zzbLC = false;

   public dz(Map var1) {
      this.zzbLl = (Map)zzbo.zzu(var1);
   }

   public final Iterator zzDk() {
      return this.zzDm();
   }

   public final Map zzDt() {
      return this.zzbLl;
   }

   public final dp zzfZ(String var1) {
      Object var2;
      if ((var2 = super.zzfZ(var1)) == null) {
         var2 = dv.zzbLu;
      }

      return (dp)var2;
   }

   public final boolean zzga(String var1) {
      return zzbLo.containsKey(var1);
   }

   public final zzcxo zzgb(String var1) {
      if (this.zzga(var1)) {
         return (zzcxo)zzbLo.get(var1);
      } else {
         throw new IllegalStateException((new StringBuilder(51 + String.valueOf(var1).length())).append("Native Method ").append(var1).append(" is not defined for type ListWrapper.").toString());
      }
   }

   public final void zzDu() {
      this.zzbLC = true;
   }

   public final boolean isImmutable() {
      return this.zzbLC;
   }

   public final boolean equals(Object var1) {
      boolean var2;
      if (this == var1) {
         var2 = true;
      } else if (var1 instanceof dz) {
         var2 = this.zzbLl.entrySet().equals(((Map)((dz)var1).zzbLl).entrySet());
      } else {
         var2 = false;
      }

      return var2;
   }

   public final String toString() {
      return this.zzbLl.toString();
   }

   // $FF: synthetic method
   public final Object zzDl() {
      return this.zzbLl;
   }

   static {
      HashMap var0;
      (var0 = new HashMap()).put("hasOwnProperty", zzczp.zzbJW);
      zzbLo = Collections.unmodifiableMap(var0);
   }
}
