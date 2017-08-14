package com.google.android.gms.internal;

import android.animation.ObjectAnimator;
import android.os.Build.VERSION;
import android.view.View;
import android.view.View.OnClickListener;

final class zzaux implements OnClickListener {
   // $FF: synthetic field
   final zzauw zzasJ;

   zzaux(zzauw var1) {
      this.zzasJ = var1;
   }

   public final void onClick(View var1) {
      if (VERSION.SDK_INT >= 16) {
         ObjectAnimator var2;
         (var2 = ObjectAnimator.ofFloat(this, "alpha", new float[]{0.0F})).setDuration(400L).addListener(new zzauy(this));
         var2.start();
      } else {
         zzauw.zza(this.zzasJ);
      }
   }
}
