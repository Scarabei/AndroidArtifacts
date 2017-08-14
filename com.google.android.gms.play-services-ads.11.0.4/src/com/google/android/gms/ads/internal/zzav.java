package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzaka;
import com.google.android.gms.internal.zzrd;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

final class zzav implements zzrd {
   // $FF: synthetic field
   private CountDownLatch zzsT;

   zzav(CountDownLatch var1) {
      this.zzsT = var1;
      super();
   }

   public final void zza(zzaka var1, Map var2) {
      zzafr.zzaT("Adapter returned an ad, but assets substitution failed");
      this.zzsT.countDown();
      var1.destroy();
   }
}
