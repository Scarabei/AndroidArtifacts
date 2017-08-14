package com.google.android.gms.ads.internal;

import android.os.Handler;

public final class zzbk {
   private final Handler mHandler;

   public zzbk(Handler var1) {
      this.mHandler = var1;
   }

   public final void removeCallbacks(Runnable var1) {
      this.mHandler.removeCallbacks(var1);
   }

   public final boolean postDelayed(Runnable var1, long var2) {
      return this.mHandler.postDelayed(var1, var2);
   }
}
