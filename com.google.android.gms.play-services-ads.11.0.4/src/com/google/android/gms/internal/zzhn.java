package com.google.android.gms.internal;

import android.support.annotation.Nullable;

public final class zzhn {
   final long value;
   final String zzze;
   final int zzzf;

   zzhn(long var1, String var3, int var4) {
      this.value = var1;
      this.zzze = var3;
      this.zzzf = var4;
   }

   public final boolean equals(@Nullable Object var1) {
      if (var1 != null && var1 instanceof zzhn) {
         return ((zzhn)var1).value == this.value && ((zzhn)var1).zzzf == this.zzzf;
      } else {
         return false;
      }
   }

   public final int hashCode() {
      return (int)this.value;
   }
}
