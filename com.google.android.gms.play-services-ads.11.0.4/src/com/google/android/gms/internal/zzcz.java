package com.google.android.gms.internal;

import java.util.HashMap;

public final class zzcz extends zzbs {
   public Long zzqy;
   public Boolean zzqz;
   public Boolean zzqA;

   public zzcz() {
   }

   public zzcz(String var1) {
      this.zzi(var1);
   }

   protected final void zzi(String var1) {
      HashMap var2;
      if ((var2 = zzj(var1)) != null) {
         this.zzqy = (Long)var2.get(Integer.valueOf(0));
         this.zzqz = (Boolean)var2.get(Integer.valueOf(1));
         this.zzqA = (Boolean)var2.get(Integer.valueOf(2));
      }

   }

   protected final HashMap zzv() {
      HashMap var1;
      (var1 = new HashMap()).put(Integer.valueOf(0), this.zzqy);
      var1.put(Integer.valueOf(1), this.zzqz);
      var1.put(Integer.valueOf(2), this.zzqA);
      return var1;
   }
}
