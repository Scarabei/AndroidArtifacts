package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.view.MotionEvent;
import android.view.GestureDetector.SimpleOnGestureListener;

final class zzb extends SimpleOnGestureListener {
   // $FF: synthetic field
   private zza zzatc;

   zzb(zza var1) {
      this.zzatc = var1;
      super();
   }

   public final boolean onSingleTapUp(MotionEvent var1) {
      float var2 = var1.getX();
      float var3 = var1.getY();
      if (!zza.zza(this.zzatc, var2, var3) || !zza.zza(this.zzatc).zzd(var2, var3)) {
         zza.zzb(this.zzatc).dismiss();
      }

      return true;
   }
}
