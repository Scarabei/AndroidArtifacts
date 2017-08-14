package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.zzaka;
import com.google.android.gms.internal.zzrd;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

final class zzau implements zzrd {
   // $FF: synthetic field
   private CountDownLatch zzsT;

   zzau(CountDownLatch var1) {
      this.zzsT = var1;
      super();
   }

   public final void zza(zzaka var1, Map var2) {
      this.zzsT.countDown();
      var1.getView().setVisibility(0);
   }
}
