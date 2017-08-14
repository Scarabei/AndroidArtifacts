package com.google.android.gms.internal;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class db {
   private final List zzbKW;
   private final Map zzbKX;
   private String zzaxs;
   private int zzbKY;

   public db(List var1, Map var2, String var3, int var4) {
      this.zzbKW = Collections.unmodifiableList(var1);
      this.zzbKX = Collections.unmodifiableMap(var2);
      this.zzaxs = var3;
      this.zzbKY = 0;
   }

   public final List zzCX() {
      return this.zzbKW;
   }

   public final dd zzfV(String var1) {
      return (dd)this.zzbKX.get(var1);
   }

   public final String getVersion() {
      return this.zzaxs;
   }

   public final String toString() {
      String var1 = String.valueOf(this.zzbKW);
      String var2 = String.valueOf(this.zzbKX);
      return (new StringBuilder(18 + String.valueOf(var1).length() + String.valueOf(var2).length())).append("Rules: ").append(var1).append("\n  Macros: ").append(var2).toString();
   }
}
