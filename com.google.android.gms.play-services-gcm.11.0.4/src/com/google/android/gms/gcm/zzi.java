package com.google.android.gms.gcm;

import android.os.Bundle;

public final class zzi {
   public static final zzi zzbgb = new zzi(0, 30, 3600);
   private static zzi zzbgc = new zzi(1, 30, 3600);
   private final int zzbgd;
   private final int zzbge;
   private final int zzbgf;

   private zzi(int var1, int var2, int var3) {
      this.zzbgd = var1;
      this.zzbge = 30;
      this.zzbgf = 3600;
   }

   public final int zzvE() {
      return this.zzbgd;
   }

   public final int zzvF() {
      return this.zzbge;
   }

   public final int zzvG() {
      return this.zzbgf;
   }

   public final Bundle zzx(Bundle var1) {
      var1.putInt("retry_policy", this.zzbgd);
      var1.putInt("initial_backoff_seconds", this.zzbge);
      var1.putInt("maximum_backoff_seconds", this.zzbgf);
      return var1;
   }

   public final String toString() {
      int var1 = this.zzbgd;
      int var2 = this.zzbge;
      int var3 = this.zzbgf;
      return (new StringBuilder(74)).append("policy=").append(var1).append(" initial_backoff=").append(var2).append(" maximum_backoff=").append(var3).toString();
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof zzi)) {
         return false;
      } else {
         zzi var2;
         return (var2 = (zzi)var1).zzbgd == this.zzbgd && var2.zzbge == this.zzbge && var2.zzbgf == this.zzbgf;
      }
   }

   public final int hashCode() {
      return ((1000003 ^ this.zzbgd + 1) * 1000003 ^ this.zzbge) * 1000003 ^ this.zzbgf;
   }
}
