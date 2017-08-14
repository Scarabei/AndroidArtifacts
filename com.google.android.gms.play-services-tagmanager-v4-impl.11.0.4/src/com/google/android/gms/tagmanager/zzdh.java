package com.google.android.gms.tagmanager;

final class zzdh implements zzek {
   private final long zzaih = 900000L;
   private final long zzbFo = 5000L;
   private final int zzaii = 5;
   private double zzaij = (double)Math.min(1, 5);
   private long zzaik;
   private final Object zzail = new Object();
   private final String zzaeX;
   private final com.google.android.gms.common.util.zze zzvw;

   public zzdh(int var1, int var2, long var3, long var5, String var7, com.google.android.gms.common.util.zze var8) {
      this.zzaeX = var7;
      this.zzvw = var8;
   }

   public final boolean zzlL() {
      Object var1 = this.zzail;
      synchronized(this.zzail) {
         long var2;
         String var4;
         if ((var2 = this.zzvw.currentTimeMillis()) - this.zzaik < this.zzbFo) {
            var4 = this.zzaeX;
            zzdj.zzaT((new StringBuilder(34 + String.valueOf(var4).length())).append("Excessive ").append(var4).append(" detected; call ignored.").toString());
            return false;
         } else {
            double var5;
            if (this.zzaij < (double)this.zzaii && (var5 = (double)(var2 - this.zzaik) / (double)this.zzaih) > 0.0D) {
               this.zzaij = Math.min((double)this.zzaii, this.zzaij + var5);
            }

            this.zzaik = var2;
            if (this.zzaij >= 1.0D) {
               --this.zzaij;
               return true;
            } else {
               var4 = this.zzaeX;
               zzdj.zzaT((new StringBuilder(34 + String.valueOf(var4).length())).append("Excessive ").append(var4).append(" detected; call ignored.").toString());
               return false;
            }
         }
      }
   }
}
