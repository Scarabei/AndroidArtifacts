package com.google.android.gms.internal;

import java.util.Collections;
import java.util.Map;

public final class ei {
   private final Map zzbKZ;
   private final zzbr zzbGv;

   private ei(Map var1, zzbr var2) {
      this.zzbKZ = var1;
      this.zzbGv = var2;
   }

   public static ej zzDx() {
      return new ej((eh)null);
   }

   public final void zza(String var1, zzbr var2) {
      this.zzbKZ.put(var1, var2);
   }

   public final Map zzCZ() {
      return Collections.unmodifiableMap(this.zzbKZ);
   }

   public final zzbr zzBN() {
      return this.zzbGv;
   }

   public final String toString() {
      String var1 = String.valueOf(Collections.unmodifiableMap(this.zzbKZ));
      String var2 = String.valueOf(this.zzbGv);
      return (new StringBuilder(32 + String.valueOf(var1).length() + String.valueOf(var2).length())).append("Properties: ").append(var1).append(" pushAfterEvaluate: ").append(var2).toString();
   }

   // $FF: synthetic method
   ei(Map var1, zzbr var2, eh var3) {
      this(var1, var2);
   }
}
