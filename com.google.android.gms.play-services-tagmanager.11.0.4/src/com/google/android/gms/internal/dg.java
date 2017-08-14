package com.google.android.gms.internal;

import java.util.Collections;
import java.util.List;

public final class dg {
   private final List zzbLb;
   private final List zzbLc;
   private final List zzbLd;
   private final List zzbLe;

   private dg(List var1, List var2, List var3, List var4) {
      this.zzbLb = Collections.unmodifiableList(var1);
      this.zzbLc = Collections.unmodifiableList(var2);
      this.zzbLd = Collections.unmodifiableList(var3);
      this.zzbLe = Collections.unmodifiableList(var4);
   }

   public final List zzDb() {
      return this.zzbLb;
   }

   public final List zzDc() {
      return this.zzbLc;
   }

   public final List zzDd() {
      return this.zzbLd;
   }

   public final List zzDe() {
      return this.zzbLe;
   }

   public final String toString() {
      String var1 = String.valueOf(this.zzbLb);
      String var2 = String.valueOf(this.zzbLc);
      String var3 = String.valueOf(this.zzbLd);
      String var4 = String.valueOf(this.zzbLe);
      return (new StringBuilder(71 + String.valueOf(var1).length() + String.valueOf(var2).length() + String.valueOf(var3).length() + String.valueOf(var4).length())).append("Positive predicates: ").append(var1).append("  Negative predicates: ").append(var2).append("  Add tags: ").append(var3).append("  Remove tags: ").append(var4).toString();
   }

   // $FF: synthetic method
   dg(List var1, List var2, List var3, List var4, dh var5) {
      this(var1, var2, var3, var4);
   }
}
