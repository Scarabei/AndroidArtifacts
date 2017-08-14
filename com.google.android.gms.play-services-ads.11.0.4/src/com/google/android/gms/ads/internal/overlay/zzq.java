package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import com.google.android.gms.internal.zzahq;
import com.google.android.gms.internal.zzzn;

@zzzn
final class zzq extends RelativeLayout {
   private zzahq zzwB;
   boolean zzOZ;

   public zzq(Context var1, String var2, String var3) {
      super(var1);
      this.zzwB = new zzahq(var1, var2);
      this.zzwB.zzaO(var3);
   }

   public final boolean onInterceptTouchEvent(MotionEvent var1) {
      if (!this.zzOZ) {
         this.zzwB.zzf(var1);
      }

      return false;
   }
}
