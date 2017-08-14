package com.google.android.gms.internal;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class ek {
   private final List zzbKW;
   private final Map zzbKX;
   private final String zzaxs;
   private final int zzbKY;

   private ek(List var1, Map var2, String var3, int var4) {
      this.zzbKW = Collections.unmodifiableList(var1);
      this.zzbKX = Collections.unmodifiableMap(var2);
      this.zzaxs = var3;
      this.zzbKY = var4;
   }

   public static el zzDz() {
      return new el((eh)null);
   }

   public final List zzCX() {
      return this.zzbKW;
   }

   public final String getVersion() {
      return this.zzaxs;
   }

   public final Map zzDA() {
      return this.zzbKX;
   }

   public final String toString() {
      String var1 = String.valueOf(this.zzbKW);
      String var2 = String.valueOf(this.zzbKX);
      return (new StringBuilder(17 + String.valueOf(var1).length() + String.valueOf(var2).length())).append("Rules: ").append(var1).append("  Macros: ").append(var2).toString();
   }

   // $FF: synthetic method
   ek(List var1, Map var2, String var3, int var4, eh var5) {
      this(var1, var2, var3, var4);
   }
}
