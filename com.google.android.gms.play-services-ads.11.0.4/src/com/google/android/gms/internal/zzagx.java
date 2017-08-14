package com.google.android.gms.internal;

import java.util.concurrent.Future;

final class zzagx implements Runnable {
   // $FF: synthetic field
   private zzajg zzZn;
   // $FF: synthetic field
   private Future zzZp;

   zzagx(zzajg var1, Future var2) {
      this.zzZn = var1;
      this.zzZp = var2;
      super();
   }

   public final void run() {
      if (this.zzZn.isCancelled()) {
         this.zzZp.cancel(true);
      }

   }
}
