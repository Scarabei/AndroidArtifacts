package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Map;

public final class df {
   private final Map zzbKZ = new HashMap();
   private dm zzbLa;

   public final df zza(String var1, dm var2) {
      this.zzbKZ.put(var1, var2);
      return this;
   }

   public final df zzb(dm var1) {
      this.zzbLa = var1;
      return this;
   }

   public final dd zzDa() {
      return new dd(this.zzbKZ, this.zzbLa, (de)null);
   }
}
