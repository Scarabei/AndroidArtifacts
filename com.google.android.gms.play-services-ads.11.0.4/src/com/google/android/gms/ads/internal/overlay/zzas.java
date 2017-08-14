package com.google.android.gms.ads.internal.overlay;

import com.google.android.gms.internal.zzagz;
import com.google.android.gms.internal.zzzn;

@zzzn
final class zzas implements Runnable {
   private zzaa zzQK;
   private boolean zzyr = false;

   zzas(zzaa var1) {
      this.zzQK = var1;
   }

   public final void run() {
      if (!this.zzyr) {
         this.zzQK.zzgc();
         this.zzgl();
      }

   }

   public final void pause() {
      this.zzyr = true;
   }

   public final void resume() {
      this.zzyr = false;
      this.zzgl();
   }

   private final void zzgl() {
      zzagz.zzZr.removeCallbacks(this);
      zzagz.zzZr.postDelayed(this, 250L);
   }
}
