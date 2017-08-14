package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

final class zzf extends AnimatorListenerAdapter {
   // $FF: synthetic field
   private Runnable zzath;
   // $FF: synthetic field
   private zza zzatc;

   zzf(zza var1, Runnable var2) {
      this.zzatc = var1;
      this.zzath = var2;
      super();
   }

   public final void onAnimationEnd(Animator var1) {
      this.zzatc.setVisibility(8);
      zza.zza(this.zzatc, (Animator)null);
      if (this.zzath != null) {
         this.zzath.run();
      }

   }
}
