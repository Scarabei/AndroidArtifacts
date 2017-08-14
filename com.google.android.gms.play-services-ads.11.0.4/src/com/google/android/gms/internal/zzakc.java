package com.google.android.gms.internal;

import android.view.View;

final class zzakc implements Runnable {
   // $FF: synthetic field
   private View val$view;
   // $FF: synthetic field
   private zzaet zzabH;
   // $FF: synthetic field
   private int zzabI;
   // $FF: synthetic field
   private zzakb zzabJ;

   zzakc(zzakb var1, View var2, zzaet var3, int var4) {
      this.zzabJ = var1;
      this.val$view = var2;
      this.zzabH = var3;
      this.zzabI = var4;
      super();
   }

   public final void run() {
      zzakb.zza(this.zzabJ, this.val$view, this.zzabH, this.zzabI - 1);
   }
}
