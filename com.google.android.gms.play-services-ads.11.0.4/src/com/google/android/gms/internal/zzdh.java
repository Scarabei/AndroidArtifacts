package com.google.android.gms.internal;

import java.util.HashMap;

public final class zzdh extends zzbs {
   public Long zzrj;
   public Long zzcv;
   public Long zzcw;

   public zzdh() {
   }

   public zzdh(String var1) {
      this.zzi(var1);
   }

   protected final void zzi(String var1) {
      HashMap var2;
      if ((var2 = zzj(var1)) != null) {
         this.zzrj = (Long)var2.get(Integer.valueOf(0));
         this.zzcv = (Long)var2.get(Integer.valueOf(1));
         this.zzcw = (Long)var2.get(Integer.valueOf(2));
      }

   }

   protected final HashMap zzv() {
      HashMap var1;
      (var1 = new HashMap()).put(Integer.valueOf(0), this.zzrj);
      var1.put(Integer.valueOf(1), this.zzcv);
      var1.put(Integer.valueOf(2), this.zzcw);
      return var1;
   }
}
