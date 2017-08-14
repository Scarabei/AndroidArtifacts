package com.google.android.gms.internal;

import android.os.Process;

final class zzbgx implements Runnable {
   private final Runnable zzv;
   private final int mPriority;

   public zzbgx(Runnable var1, int var2) {
      this.zzv = var1;
      this.mPriority = var2;
   }

   public final void run() {
      Process.setThreadPriority(this.mPriority);
      this.zzv.run();
   }
}
