package com.google.android.gms.internal;

import java.util.Collections;
import java.util.List;

public final class em {
   private final List zzbLb;
   private final List zzbLc;
   private final List zzbLd;
   private final List zzbLe;
   private final List zzbLJ;
   private final List zzbLK;
   private final List zzbLL;
   private final List zzbLM;
   private final List zzbLN;
   private final List zzbLO;

   private em(List var1, List var2, List var3, List var4, List var5, List var6, List var7, List var8, List var9, List var10) {
      this.zzbLb = Collections.unmodifiableList(var1);
      this.zzbLc = Collections.unmodifiableList(var2);
      this.zzbLd = Collections.unmodifiableList(var3);
      this.zzbLe = Collections.unmodifiableList(var4);
      this.zzbLJ = Collections.unmodifiableList(var5);
      this.zzbLK = Collections.unmodifiableList(var6);
      this.zzbLL = Collections.unmodifiableList(var7);
      this.zzbLM = Collections.unmodifiableList(var8);
      this.zzbLN = Collections.unmodifiableList(var9);
      this.zzbLO = Collections.unmodifiableList(var10);
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

   public final List zzDC() {
      return this.zzbLJ;
   }

   public final List zzDD() {
      return this.zzbLK;
   }

   public final String toString() {
      String var1 = String.valueOf(this.zzbLb);
      String var2 = String.valueOf(this.zzbLc);
      String var3 = String.valueOf(this.zzbLd);
      String var4 = String.valueOf(this.zzbLe);
      String var5 = String.valueOf(this.zzbLJ);
      String var6 = String.valueOf(this.zzbLK);
      return (new StringBuilder(102 + String.valueOf(var1).length() + String.valueOf(var2).length() + String.valueOf(var3).length() + String.valueOf(var4).length() + String.valueOf(var5).length() + String.valueOf(var6).length())).append("Positive predicates: ").append(var1).append("  Negative predicates: ").append(var2).append("  Add tags: ").append(var3).append("  Remove tags: ").append(var4).append("  Add macros: ").append(var5).append("  Remove macros: ").append(var6).toString();
   }

   // $FF: synthetic method
   em(List var1, List var2, List var3, List var4, List var5, List var6, List var7, List var8, List var9, List var10, eh var11) {
      this(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10);
   }
}
