package com.google.android.gms.internal;

import android.view.ViewTreeObserver.OnScrollChangedListener;
import java.lang.ref.WeakReference;

final class zzze implements OnScrollChangedListener {
   // $FF: synthetic field
   private WeakReference zzSd;
   // $FF: synthetic field
   private zzyx zzSb;

   zzze(zzyx var1, WeakReference var2) {
      this.zzSb = var1;
      this.zzSd = var2;
      super();
   }

   public final void onScrollChanged() {
      zzyx.zza(this.zzSb, this.zzSd, true);
   }
}
