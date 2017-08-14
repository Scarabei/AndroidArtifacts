package com.google.android.gms.internal;

import android.view.MotionEvent;
import android.view.View;

final class zzor implements zznw {
   // $FF: synthetic field
   private View zzIC;
   // $FF: synthetic field
   private zzop zzIB;

   zzor(zzop var1, View var2) {
      this.zzIB = var1;
      this.zzIC = var2;
      super();
   }

   public final void zzeo() {
      this.zzIB.onClick(this.zzIC);
   }

   public final void zzc(MotionEvent var1) {
      this.zzIB.onTouch((View)null, var1);
   }
}
