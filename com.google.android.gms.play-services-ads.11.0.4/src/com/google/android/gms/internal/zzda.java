package com.google.android.gms.internal;

import java.util.HashMap;

public final class zzda extends zzbs {
   public Long zzqB;
   public Long zzqC;

   public zzda() {
   }

   public zzda(String var1) {
      this.zzi(var1);
   }

   protected final void zzi(String var1) {
      HashMap var2;
      if ((var2 = zzj(var1)) != null) {
         this.zzqB = (Long)var2.get(Integer.valueOf(0));
         this.zzqC = (Long)var2.get(Integer.valueOf(1));
      }

   }

   protected final HashMap zzv() {
      HashMap var1;
      (var1 = new HashMap()).put(Integer.valueOf(0), this.zzqB);
      var1.put(Integer.valueOf(1), this.zzqC);
      return var1;
   }
}
