package com.google.android.gms.internal;

import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import java.lang.ref.WeakReference;

final class zzzd implements OnGlobalLayoutListener {
   // $FF: synthetic field
   private WeakReference zzSd;
   // $FF: synthetic field
   private zzyx zzSb;

   zzzd(zzyx var1, WeakReference var2) {
      this.zzSb = var1;
      this.zzSd = var2;
      super();
   }

   public final void onGlobalLayout() {
      zzyx.zza(this.zzSb, this.zzSd, false);
   }
}
