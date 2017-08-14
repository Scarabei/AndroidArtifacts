package com.google.android.gms.internal;

import android.os.Handler;
import com.google.android.gms.common.internal.zzbo;

abstract class zzanm {
   private final zzamj zzafJ;
   private static volatile Handler zzagY;
   private final Runnable zzv;
   private volatile long zzagZ;

   zzanm(zzamj var1) {
      zzbo.zzu(var1);
      this.zzafJ = var1;
      this.zzv = new zzann(this);
   }

   public abstract void run();

   public final void zzs(long var1) {
      this.cancel();
      if (var1 >= 0L) {
         this.zzagZ = this.zzafJ.zzkq().currentTimeMillis();
         if (!this.getHandler().postDelayed(this.zzv, var1)) {
            this.zzafJ.zzkr().zze("Failed to schedule delayed post. time", var1);
         }
      }

   }

   public final void zzt(long var1) {
      if (this.zzbo()) {
         if (var1 < 0L) {
            this.cancel();
         } else {
            long var3 = Math.abs(this.zzafJ.zzkq().currentTimeMillis() - this.zzagZ);
            long var5;
            if ((var5 = var1 - var3) < 0L) {
               var5 = 0L;
            }

            this.getHandler().removeCallbacks(this.zzv);
            if (!this.getHandler().postDelayed(this.zzv, var5)) {
               this.zzafJ.zzkr().zze("Failed to adjust delayed post. time", var5);
            }

         }
      }
   }

   public final long zzlz() {
      return this.zzagZ == 0L ? 0L : Math.abs(this.zzafJ.zzkq().currentTimeMillis() - this.zzagZ);
   }

   public final boolean zzbo() {
      return this.zzagZ != 0L;
   }

   public final void cancel() {
      this.zzagZ = 0L;
      this.getHandler().removeCallbacks(this.zzv);
   }

   private final Handler getHandler() {
      if (zzagY != null) {
         return zzagY;
      } else {
         Class var1 = zzanm.class;
         synchronized(zzanm.class) {
            if (zzagY == null) {
               zzagY = new Handler(this.zzafJ.getContext().getMainLooper());
            }

            return zzagY;
         }
      }
   }

   // $FF: synthetic method
   static zzamj zza(zzanm var0) {
      return var0.zzafJ;
   }

   // $FF: synthetic method
   static long zza(zzanm var0, long var1) {
      return var0.zzagZ = 0L;
   }
}
