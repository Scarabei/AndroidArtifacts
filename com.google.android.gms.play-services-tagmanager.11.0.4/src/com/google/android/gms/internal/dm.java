package com.google.android.gms.internal;

import java.util.Collections;
import java.util.List;

public final class dm {
   private static final String zzbHi = new String("");
   private static final Integer zzbLg = Integer.valueOf(0);
   private final int zzamr;
   private final Object mValue;
   private final List zzbLh;
   private final boolean zzbLi;

   private dm(Integer var1, Object var2, List var3, boolean var4) {
      this.zzamr = var1.intValue();
      this.mValue = var2;
      this.zzbLh = Collections.unmodifiableList(var3);
      this.zzbLi = var4;
   }

   public final int getType() {
      return this.zzamr;
   }

   public final Object getValue() {
      return this.mValue;
   }

   public final List zzDi() {
      return this.zzbLh;
   }

   public final String toString() {
      if (this.mValue == null) {
         zzcvk.e("Fail to convert a null object to string");
         return zzbHi;
      } else {
         return this.mValue.toString();
      }
   }

   public final boolean equals(Object var1) {
      return var1 instanceof dm && ((dm)var1).mValue.equals(this.mValue);
   }

   public final int hashCode() {
      return this.mValue.hashCode();
   }

   // $FF: synthetic method
   dm(Integer var1, Object var2, List var3, boolean var4, dn var5) {
      this(var1, var2, var3, var4);
   }
}
