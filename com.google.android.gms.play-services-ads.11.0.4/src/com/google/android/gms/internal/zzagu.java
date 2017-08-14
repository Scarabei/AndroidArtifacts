package com.google.android.gms.internal;

import java.util.concurrent.Callable;

final class zzagu implements Callable {
   // $FF: synthetic field
   private Runnable zzZm;

   zzagu(Runnable var1) {
      this.zzZm = var1;
      super();
   }

   // $FF: synthetic method
   public final Object call() throws Exception {
      this.zzZm.run();
      return null;
   }
}
