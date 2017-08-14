package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;

final class hs extends hn {
   private Handler handler;

   public hs(Looper var1) {
      this.handler = new Handler(var1);
   }

   public final void zza(hp var1) {
      this.handler.postDelayed(var1.zzEf(), 0L);
   }
}
