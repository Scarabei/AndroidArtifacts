package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;

final class zzc extends SimpleOnGestureListener {
   // $FF: synthetic field
   private View zzatd;
   // $FF: synthetic field
   private boolean zzate;
   // $FF: synthetic field
   private zzh zzatf;

   zzc(zza var1, View var2, boolean var3, zzh var4) {
      this.zzatd = var2;
      this.zzate = true;
      this.zzatf = var4;
      super();
   }

   public final boolean onSingleTapUp(MotionEvent var1) {
      if (this.zzatd.getParent() != null) {
         this.zzatd.performClick();
      }

      if (this.zzate) {
         this.zzatf.zznL();
      }

      return true;
   }
}
