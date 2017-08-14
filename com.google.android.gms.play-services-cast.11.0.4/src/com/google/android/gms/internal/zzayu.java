package com.google.android.gms.internal;

import com.google.android.gms.common.util.zze;

public final class zzayu {
   private long zzayR;
   private long zzaxo;
   private long zzayS;
   private zzayt zzayT;
   private final zze zzvw;
   private static final zzayo zzapq = new zzayo("RequestTracker");
   public static final Object zzrl = new Object();

   public zzayu(zze var1, long var2) {
      this.zzvw = var1;
      this.zzayR = var2;
      this.zzaxo = -1L;
      this.zzayS = 0L;
   }

   public final void zza(long var1, zzayt var3) {
      Object var7 = zzrl;
      zzayt var4;
      long var5;
      synchronized(zzrl) {
         var4 = this.zzayT;
         var5 = this.zzaxo;
         this.zzaxo = var1;
         this.zzayT = var3;
         this.zzayS = this.zzvw.elapsedRealtime();
      }

      if (var4 != null) {
         var4.zzx(var5);
      }

   }

   public final void clear() {
      Object var1 = zzrl;
      synchronized(zzrl) {
         if (this.zzaxo != -1L) {
            this.zzoN();
         }

      }
   }

   private final void zzoN() {
      this.zzaxo = -1L;
      this.zzayT = null;
      this.zzayS = 0L;
   }

   public final boolean zzoO() {
      Object var1 = zzrl;
      synchronized(zzrl) {
         return this.zzaxo != -1L;
      }
   }

   public final boolean test(long var1) {
      Object var3 = zzrl;
      synchronized(zzrl) {
         return this.zzaxo != -1L && this.zzaxo == var1;
      }
   }

   public final boolean zzc(long var1, int var3, Object var4) {
      boolean var5 = false;
      zzayt var6 = null;
      Object var7 = zzrl;
      synchronized(zzrl) {
         if (this.zzaxo != -1L && this.zzaxo == var1) {
            zzapq.zzb("request %d completed", this.zzaxo);
            var6 = this.zzayT;
            this.zzoN();
            var5 = true;
         }
      }

      if (var6 != null) {
         var6.zza(var1, var3, var4);
      }

      return var5;
   }

   public final boolean zzd(long var1, int var3) {
      boolean var4 = false;
      zzayt var5 = null;
      long var6 = 0L;
      Object var8 = zzrl;
      synchronized(zzrl) {
         if (this.zzaxo != -1L && var1 - this.zzayS >= this.zzayR) {
            zzapq.zzb("request %d timed out", this.zzaxo);
            var6 = this.zzaxo;
            var5 = this.zzayT;
            this.zzoN();
            var4 = true;
         }
      }

      if (var5 != null) {
         var5.zza(var6, var3, (Object)null);
      }

      return var4;
   }
}
