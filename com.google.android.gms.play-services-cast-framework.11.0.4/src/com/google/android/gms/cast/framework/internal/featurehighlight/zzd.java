package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.view.View;
import android.view.View.OnLayoutChangeListener;

final class zzd implements OnLayoutChangeListener {
   // $FF: synthetic field
   private Runnable zzatg;
   // $FF: synthetic field
   private zza zzatc;

   zzd(zza var1, Runnable var2) {
      this.zzatc = var1;
      this.zzatg = null;
      super();
   }

   public final void onLayoutChange(View var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9) {
      if (this.zzatg != null) {
         this.zzatg.run();
      }

      this.zzatc.zznN();
      this.zzatc.removeOnLayoutChangeListener(this);
   }
}
