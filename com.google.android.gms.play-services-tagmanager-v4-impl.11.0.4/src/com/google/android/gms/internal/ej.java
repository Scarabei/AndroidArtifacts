package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Map;

public final class ej {
   private final Map zzbKZ;
   private zzbr zzbGv;

   private ej() {
      this.zzbKZ = new HashMap();
   }

   public final ej zzb(String var1, zzbr var2) {
      this.zzbKZ.put(var1, var2);
      return this;
   }

   public final ej zzl(zzbr var1) {
      this.zzbGv = var1;
      return this;
   }

   public final ei zzDy() {
      return new ei(this.zzbKZ, this.zzbGv, (eh)null);
   }

   // $FF: synthetic method
   ej(eh var1) {
      this();
   }
}
