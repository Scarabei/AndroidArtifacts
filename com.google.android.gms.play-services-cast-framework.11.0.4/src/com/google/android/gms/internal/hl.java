package com.google.android.gms.internal;

import android.animation.Animator;
import android.support.annotation.Nullable;

public final class hl extends hk {
   protected final Animator zzbUn;
   private final hn zzbUo;
   private final Runnable zzbUp;
   private hp zzbUq = new hm(this);

   public static void zza(Animator var0, @Nullable Runnable var1) {
      var0.addListener(new hl(var0, (Runnable)null));
   }

   private hl(Animator var1, @Nullable Runnable var2) {
      this.zzbUn = var1;
      this.zzbUp = null;
      this.zzbUo = hn.zzEd();
   }

   public final void onAnimationEnd(Animator var1) {
      if (!this.zzb(var1)) {
         this.zzbUo.zza(this.zzbUq);
      }

   }

   // $FF: synthetic method
   static Runnable zza(hl var0) {
      return var0.zzbUp;
   }
}
