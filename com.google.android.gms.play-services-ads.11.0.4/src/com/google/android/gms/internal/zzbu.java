package com.google.android.gms.internal;

import java.util.HashMap;

public final class zzbu extends zzbs {
   public String zzaT;
   public long zzlO;
   public String zzaV;
   public String zzaW;
   public String zzaX;

   public zzbu(String var1) {
      this();
      this.zzi(var1);
   }

   public zzbu() {
      this.zzaT = "E";
      this.zzlO = -1L;
      this.zzaV = "E";
      this.zzaW = "E";
      this.zzaX = "E";
   }

   protected final void zzi(String var1) {
      HashMap var2;
      if ((var2 = zzj(var1)) != null) {
         this.zzaT = var2.get(Integer.valueOf(0)) == null ? "E" : (String)var2.get(Integer.valueOf(0));
         this.zzlO = var2.get(Integer.valueOf(1)) == null ? -1L : ((Long)var2.get(Integer.valueOf(1))).longValue();
         this.zzaV = var2.get(Integer.valueOf(2)) == null ? "E" : (String)var2.get(Integer.valueOf(2));
         this.zzaW = var2.get(Integer.valueOf(3)) == null ? "E" : (String)var2.get(Integer.valueOf(3));
         this.zzaX = var2.get(Integer.valueOf(4)) == null ? "E" : (String)var2.get(Integer.valueOf(4));
      }

   }

   protected final HashMap zzv() {
      HashMap var1;
      (var1 = new HashMap()).put(Integer.valueOf(0), this.zzaT);
      var1.put(Integer.valueOf(4), this.zzaX);
      var1.put(Integer.valueOf(3), this.zzaW);
      var1.put(Integer.valueOf(2), this.zzaV);
      var1.put(Integer.valueOf(1), this.zzlO);
      return var1;
   }
}
