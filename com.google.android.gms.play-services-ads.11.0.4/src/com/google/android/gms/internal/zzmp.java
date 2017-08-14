package com.google.android.gms.internal;

import android.content.Context;
import java.util.concurrent.Callable;

final class zzmp implements Callable {
   // $FF: synthetic field
   private Context zztF;

   zzmp(Context var1) {
      this.zztF = var1;
      super();
   }

   // $FF: synthetic method
   public final Object call() throws Exception {
      com.google.android.gms.ads.internal.zzbs.zzbL().initialize(this.zztF);
      return null;
   }
}
