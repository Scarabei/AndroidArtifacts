package com.google.android.gms.ads.internal.overlay;

import android.graphics.drawable.Drawable;

final class zzt implements Runnable {
   // $FF: synthetic field
   private Drawable zzPb;
   // $FF: synthetic field
   private zzs zzPc;

   zzt(zzs var1, Drawable var2) {
      this.zzPc = var1;
      this.zzPb = var2;
      super();
   }

   public final void run() {
      zzm.zza(this.zzPc.zzOY).getWindow().setBackgroundDrawable(this.zzPb);
   }
}
