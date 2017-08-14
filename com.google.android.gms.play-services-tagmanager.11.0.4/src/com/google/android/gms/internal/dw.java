package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class dw extends dp {
   private final ArrayList zzbLx;
   private static final Map zzbLo;

   public dw(List var1) {
      zzbo.zzu(var1);
      this.zzbLx = new ArrayList(var1);
   }

   public final List zzDs() {
      return this.zzbLx;
   }

   public final void zza(int var1, dp var2) {
      if (var1 < 0) {
         throw new IndexOutOfBoundsException();
      } else {
         if (var1 >= this.zzbLx.size()) {
            this.setSize(var1 + 1);
         }

         this.zzbLx.set(var1, var2);
      }
   }

   public final void setSize(int var1) {
      zzbo.zzb(var1 >= 0, "Invalid array length");
      if (this.zzbLx.size() != var1) {
         if (this.zzbLx.size() >= var1) {
            this.zzbLx.subList(var1, this.zzbLx.size()).clear();
         } else {
            this.zzbLx.ensureCapacity(var1);

            for(int var2 = this.zzbLx.size(); var2 < var1; ++var2) {
               this.zzbLx.add((Object)null);
            }

         }
      }
   }

   public final dp zzbG(int var1) {
      if (var1 >= 0 && var1 < this.zzbLx.size()) {
         dp var2;
         return (dp)((var2 = (dp)this.zzbLx.get(var1)) == null ? dv.zzbLu : var2);
      } else {
         return dv.zzbLu;
      }
   }

   public final boolean zzbH(int var1) {
      return var1 >= 0 && var1 < this.zzbLx.size() && this.zzbLx.get(var1) != null;
   }

   public final Iterator zzDk() {
      dx var1 = new dx(this);
      Iterator var2 = super.zzDm();
      return new dy(this, var1, var2);
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
      } else {
         if (var1 instanceof dw) {
            List var3 = (List)((dw)var1).zzbLx;
            if (this.zzbLx.size() == var3.size()) {
               var2 = true;

               for(int var4 = 0; var4 < this.zzbLx.size() && (var2 = this.zzbLx.get(var4) == null ? var3.get(var4) == null : ((dp)this.zzbLx.get(var4)).equals(var3.get(var4))); ++var4) {
                  ;
               }

               return var2;
            }
         }

         var2 = false;
      }

      return var2;
   }

   public final String toString() {
      return this.zzbLx.toString();
   }

   // $FF: synthetic method
   public final Object zzDl() {
      return this.zzbLx;
   }

   // $FF: synthetic method
   static ArrayList zza(dw var0) {
      return var0.zzbLx;
   }

   static {
      HashMap var0;
      (var0 = new HashMap()).put("concat", new zzcxr());
      var0.put("every", new zzcxs());
      var0.put("filter", new zzcxt());
      var0.put("forEach", new zzcxu());
      var0.put("indexOf", new zzcxv());
      var0.put("hasOwnProperty", zzczp.zzbJW);
      var0.put("join", new zzcxw());
      var0.put("lastIndexOf", new zzcxx());
      var0.put("map", new zzcxy());
      var0.put("pop", new zzcxz());
      var0.put("push", new zzcya());
      var0.put("reduce", new zzcyb());
      var0.put("reduceRight", new zzcyc());
      var0.put("reverse", new zzcyd());
      var0.put("shift", new zzcye());
      var0.put("slice", new zzcyf());
      var0.put("some", new zzcyg());
      var0.put("sort", new zzcyh());
      var0.put("splice", new zzcyl());
      var0.put("toString", new r());
      var0.put("unshift", new zzcym());
      zzbLo = Collections.unmodifiableMap(var0);
   }
}
