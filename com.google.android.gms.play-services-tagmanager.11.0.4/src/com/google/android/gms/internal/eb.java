package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class eb extends dp {
   private final String mValue;
   private static final Map zzbLo;

   public eb(String var1) {
      zzbo.zzu(var1);
      this.mValue = var1;
   }

   public final dp zzbI(int var1) {
      return (dp)(var1 >= 0 && var1 < this.mValue.length() ? new eb(String.valueOf(this.mValue.charAt(var1))) : dv.zzbLu);
   }

   public final Iterator zzDk() {
      return new ec(this);
   }

   public final String value() {
      return this.mValue;
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

   public final boolean equals(Object var1) {
      boolean var2;
      if (this == var1) {
         var2 = true;
      } else if (var1 instanceof eb) {
         var2 = this.mValue.equals((String)((eb)var1).mValue);
      } else {
         var2 = false;
      }

      return var2;
   }

   public final String toString() {
      return this.mValue.toString();
   }

   // $FF: synthetic method
   public final Object zzDl() {
      return this.mValue;
   }

   // $FF: synthetic method
   static String zza(eb var0) {
      return var0.mValue;
   }

   static {
      HashMap var0;
      (var0 = new HashMap()).put("charAt", new e());
      var0.put("concat", new f());
      var0.put("hasOwnProperty", zzczp.zzbJW);
      var0.put("indexOf", new g());
      var0.put("lastIndexOf", new h());
      var0.put("match", new i());
      var0.put("replace", new j());
      var0.put("search", new k());
      var0.put("slice", new l());
      var0.put("split", new m());
      var0.put("substring", new n());
      var0.put("toLocaleLowerCase", new o());
      var0.put("toLocaleUpperCase", new p());
      var0.put("toLowerCase", new q());
      var0.put("toUpperCase", new s());
      var0.put("toString", new r());
      var0.put("trim", new t());
      zzbLo = Collections.unmodifiableMap(var0);
   }
}
