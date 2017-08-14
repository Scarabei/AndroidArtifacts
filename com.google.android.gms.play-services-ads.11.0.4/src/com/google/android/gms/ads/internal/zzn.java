package com.google.android.gms.ads.internal;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

final class zzn implements OnTouchListener {
   // $FF: synthetic field
   private zzw zztf;
   // $FF: synthetic field
   private zzl zzte;

   zzn(zzl var1, zzw var2) {
      this.zzte = var1;
      this.zztf = var2;
      super();
   }

   public final boolean onTouch(View var1, MotionEvent var2) {
      this.zztf.recordClick();
      if (this.zzte.zztc != null) {
         this.zzte.zztc.zzha();
      }

      return false;
   }
}
