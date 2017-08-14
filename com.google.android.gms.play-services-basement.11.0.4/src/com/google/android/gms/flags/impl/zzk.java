package com.google.android.gms.flags.impl;

import android.content.Context;
import java.util.concurrent.Callable;

final class zzk implements Callable {
   // $FF: synthetic field
   private Context zztF;

   zzk(Context var1) {
      this.zztF = var1;
      super();
   }

   // $FF: synthetic method
   public final Object call() throws Exception {
      return this.zztF.getSharedPreferences("google_sdk_flags", 0);
   }
}
