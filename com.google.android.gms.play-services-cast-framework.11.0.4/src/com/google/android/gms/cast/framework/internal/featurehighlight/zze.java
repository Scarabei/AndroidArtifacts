package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

final class zze extends AnimatorListenerAdapter {
   // $FF: synthetic field
   private zza zzatc;

   zze(zza var1) {
      this.zzatc = var1;
      super();
   }

   public final void onAnimationEnd(Animator var1) {
      zza.zza(this.zzatc, zza.zzc(this.zzatc));
      zza.zzd(this.zzatc).start();
   }
}
