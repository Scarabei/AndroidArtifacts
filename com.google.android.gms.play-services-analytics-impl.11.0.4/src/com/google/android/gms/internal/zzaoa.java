package com.google.android.gms.internal;

import com.google.android.gms.common.util.zze;

public final class zzaoa {
   private final long zzaih;
   private final int zzaii;
   private double zzaij;
   private long zzaik;
   private final Object zzail;
   private final String zzaeX;
   private final zze zzvw;

   private zzaoa(int var1, long var2, String var4, zze var5) {
      this.zzail = new Object();
      this.zzaii = 60;
      this.zzaij = (double)this.zzaii;
      this.zzaih = 2000L;
      this.zzaeX = var4;
      this.zzvw = var5;
   }

   public zzaoa(String var1, zze var2) {
      this(60, 2000L, var1, var2);
   }

   public final boolean zzlL() {
      Object var1 = this.zzail;
      synchronized(this.zzail) {
         long var2 = this.zzvw.currentTimeMillis();
         double var5;
         if (this.zzaij < (double)this.zzaii && (var5 = (double)(var2 - this.zzaik) / (double)this.zzaih) > 0.0D) {
            this.zzaij = Math.min((double)this.zzaii, this.zzaij + var5);
         }

         this.zzaik = var2;
         if (this.zzaij >= 1.0D) {
            --this.zzaij;
            return true;
         } else {
            String var4 = this.zzaeX;
            zzaob.zzaT((new StringBuilder(34 + String.valueOf(var4).length())).append("Excessive ").append(var4).append(" detected; call ignored.").toString());
            return false;
         }
      }
   }
}
