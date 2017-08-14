package com.google.android.gms.ads.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzagz;
import com.google.android.gms.internal.zzir;
import com.google.android.gms.internal.zzzn;
import java.lang.ref.WeakReference;

@zzzn
public final class zzbi {
   private final zzbk zzuS;
   private final Runnable zzv;
   @Nullable
   private zzir zzuT;
   private boolean zzuU;
   private boolean zzuV;
   private long zzuW;

   public zzbi(zza var1) {
      this(var1, new zzbk(zzagz.zzZr));
   }

   private zzbi(zza var1, zzbk var2) {
      this.zzuU = false;
      this.zzuV = false;
      this.zzuW = 0L;
      this.zzuS = var2;
      WeakReference var3 = new WeakReference(var1);
      this.zzv = new zzbj(this, var3);
   }

   public final void zzf(zzir var1) {
      this.zzuT = var1;
   }

   public final void cancel() {
      this.zzuU = false;
      this.zzuS.removeCallbacks(this.zzv);
   }

   public final void pause() {
      this.zzuV = true;
      if (this.zzuU) {
         this.zzuS.removeCallbacks(this.zzv);
      }

   }

   public final void resume() {
      this.zzuV = false;
      if (this.zzuU) {
         this.zzuU = false;
         this.zza(this.zzuT, this.zzuW);
      }

   }

   public final boolean zzbo() {
      return this.zzuU;
   }

   public final void zzg(zzir var1) {
      this.zza(var1, 60000L);
   }

   public final void zza(zzir var1, long var2) {
      if (this.zzuU) {
         zzafr.zzaT("An ad refresh is already scheduled.");
      } else {
         this.zzuT = var1;
         this.zzuU = true;
         this.zzuW = var2;
         if (!this.zzuV) {
            zzafr.zzaS((new StringBuilder(65)).append("Scheduling ad refresh ").append(var2).append(" milliseconds from now.").toString());
            this.zzuS.postDelayed(this.zzv, var2);
         }

      }
   }

   // $FF: synthetic method
   static boolean zza(zzbi var0, boolean var1) {
      return var0.zzuU = false;
   }

   // $FF: synthetic method
   static zzir zza(zzbi var0) {
      return var0.zzuT;
   }
}
