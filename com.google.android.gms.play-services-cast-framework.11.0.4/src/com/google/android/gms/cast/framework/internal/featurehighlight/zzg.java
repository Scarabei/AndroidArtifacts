package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

final class zzg extends AnimatorListenerAdapter {
   // $FF: synthetic field
   private Runnable zzati;
   // $FF: synthetic field
   private zza zzatc;

   zzg(zza var1, Runnable var2) {
      this.zzatc = var1;
      this.zzati = var2;
      super();
   }

   public final void onAnimationEnd(Animator var1) {
      this.zzatc.setVisibility(8);
      zza.zza(this.zzatc, (Animator)null);
      if (this.zzati != null) {
         this.zzati.run();
      }

   }
}
