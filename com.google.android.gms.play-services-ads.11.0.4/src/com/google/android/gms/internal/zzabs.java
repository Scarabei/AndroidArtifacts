package com.google.android.gms.internal;

import java.util.concurrent.Future;

final class zzabs implements Runnable {
   // $FF: synthetic field
   private Future zzUJ;

   zzabs(zzabm var1, Future var2) {
      this.zzUJ = var2;
      super();
   }

   public final void run() {
      if (!this.zzUJ.isDone()) {
         this.zzUJ.cancel(true);
      }

   }
}
