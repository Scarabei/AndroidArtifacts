package com.google.android.gms.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class du extends dp {
   private zzcxo zzbLq;
   private static final Map zzbLo;

   public du(zzcxo var1) {
      this.zzbLq = var1;
   }

   public final Iterator zzDk() {
      return this.zzDm();
   }

   public final zzcxo zzDp() {
      return this.zzbLq;
   }

   public final boolean zzga(String var1) {
      return zzbLo.containsKey(var1);
   }

   public final zzcxo zzgb(String var1) {
      if (this.zzga(var1)) {
         return (zzcxo)zzbLo.get(var1);
      } else {
         throw new IllegalStateException((new StringBuilder(60 + String.valueOf(var1).length())).append("Native Method ").append(var1).append(" is not defined for type InstructionReference.").toString());
      }
   }

   public final String toString() {
      return this.zzbLq.toString();
   }

   // $FF: synthetic method
   public final Object zzDl() {
      return this.zzbLq;
   }

   static {
      HashMap var0;
      (var0 = new HashMap()).put("hasOwnProperty", zzczp.zzbJW);
      zzbLo = Collections.unmodifiableMap(var0);
   }
}
