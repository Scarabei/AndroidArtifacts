package com.google.android.gms.common.stats;

import com.google.android.gms.common.internal.ReflectedParcelable;

public abstract class StatsEvent extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public abstract long getTimeMillis();

   public abstract int getEventType();

   public abstract long zzrV();

   public String toString() {
      long var1 = this.getTimeMillis();
      String var3 = String.valueOf("\t");
      int var4 = this.getEventType();
      String var5 = String.valueOf("\t");
      long var6 = this.zzrV();
      String var8 = String.valueOf(this.zzrW());
      return (new StringBuilder(51 + String.valueOf(var3).length() + String.valueOf(var5).length() + String.valueOf(var8).length())).append(var1).append(var3).append(var4).append(var5).append(var6).append(var8).toString();
   }

   public abstract String zzrW();
}
