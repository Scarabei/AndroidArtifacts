package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbe;
import java.util.Arrays;

public final class zzaic {
   public final String name;
   private double zzZX;
   private double zzZY;
   public final double zzZZ;
   public final int count;

   public zzaic(String var1, double var2, double var4, double var6, int var8) {
      this.name = var1;
      this.zzZY = var2;
      this.zzZX = var4;
      this.zzZZ = var6;
      this.count = var8;
   }

   public final String toString() {
      return zzbe.zzt(this).zzg("name", this.name).zzg("minBound", this.zzZY).zzg("maxBound", this.zzZX).zzg("percent", this.zzZZ).zzg("count", this.count).toString();
   }

   public final boolean equals(Object var1) {
      if (!(var1 instanceof zzaic)) {
         return false;
      } else {
         zzaic var2 = (zzaic)var1;
         return zzbe.equal(this.name, var2.name) && this.zzZX == var2.zzZX && this.zzZY == var2.zzZY && this.count == var2.count && Double.compare(this.zzZZ, var2.zzZZ) == 0;
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.name, this.zzZX, this.zzZY, this.zzZZ, this.count});
   }
}
