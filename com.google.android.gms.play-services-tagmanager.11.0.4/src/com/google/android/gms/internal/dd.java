package com.google.android.gms.internal;

import java.util.Collections;
import java.util.Map;

public final class dd {
   private final Map zzbKZ;
   private final dm zzbLa;

   private dd(Map var1, dm var2) {
      this.zzbKZ = Collections.unmodifiableMap(var1);
      this.zzbLa = var2;
   }

   public final Map zzCZ() {
      return this.zzbKZ;
   }

   public final String toString() {
      String var1 = String.valueOf(this.zzbKZ);
      String var2 = String.valueOf(this.zzbLa);
      return (new StringBuilder(32 + String.valueOf(var1).length() + String.valueOf(var2).length())).append("Properties: ").append(var1).append(" pushAfterEvaluate: ").append(var2).toString();
   }

   // $FF: synthetic method
   dd(Map var1, dm var2, de var3) {
      this(var1, var2);
   }
}
