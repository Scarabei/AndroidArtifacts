package com.google.android.gms.internal;

import android.content.Context;
import java.util.concurrent.Callable;

// $FF: synthetic class
final class zzaex implements Callable {
   private final zzaew zzXH;
   private final Context zzXI;

   zzaex(zzaew var1, Context var2) {
      this.zzXH = var1;
      this.zzXI = var2;
   }

   public final Object call() {
      return this.zzXH.zzy(this.zzXI);
   }
}
