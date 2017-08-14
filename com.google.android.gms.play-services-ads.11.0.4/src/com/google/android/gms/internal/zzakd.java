package com.google.android.gms.internal;

import android.view.View;
import android.view.View.OnAttachStateChangeListener;

final class zzakd implements OnAttachStateChangeListener {
   // $FF: synthetic field
   private zzaet zzabH;
   // $FF: synthetic field
   private zzakb zzabJ;

   zzakd(zzakb var1, zzaet var2) {
      this.zzabJ = var1;
      this.zzabH = var2;
      super();
   }

   public final void onViewAttachedToWindow(View var1) {
      zzakb.zza(this.zzabJ, var1, this.zzabH, 10);
   }

   public final void onViewDetachedFromWindow(View var1) {
   }
}
