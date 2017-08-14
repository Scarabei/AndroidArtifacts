package com.google.android.gms.internal;

import android.os.Handler;
import java.util.concurrent.Executor;

final class zzi implements Executor {
   // $FF: synthetic field
   private Handler zzs;

   zzi(zzh var1, Handler var2) {
      this.zzs = var2;
      super();
   }

   public final void execute(Runnable var1) {
      this.zzs.post(var1);
   }
}
