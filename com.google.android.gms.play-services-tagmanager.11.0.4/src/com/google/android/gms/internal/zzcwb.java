package com.google.android.gms.internal;

final class zzcwb implements zzcvt {
   private final long zzaih;
   private final int zzaii;
   private double zzaij;
   private long zzbGB;
   private final Object zzail;

   private zzcwb(int var1, long var2) {
      this.zzail = new Object();
      this.zzaii = 60;
      this.zzaij = (double)this.zzaii;
      this.zzaih = 2000L;
   }

   public zzcwb() {
      this(60, 2000L);
   }

   public final boolean zzlL() {
      Object var1 = this.zzail;
      synchronized(this.zzail) {
         long var2 = System.currentTimeMillis();
         double var4;
         if (this.zzaij < (double)this.zzaii && (var4 = (double)(var2 - this.zzbGB) / (double)this.zzaih) > 0.0D) {
            this.zzaij = Math.min((double)this.zzaii, this.zzaij + var4);
         }

         this.zzbGB = var2;
         if (this.zzaij >= 1.0D) {
            --this.zzaij;
            return true;
         } else {
            zzcvk.zzaT("No more tokens available.");
            return false;
         }
      }
   }
}
