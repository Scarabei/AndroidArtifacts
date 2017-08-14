package com.google.android.gms.internal;

import android.content.Context;

final class zzlg implements Runnable {
   // $FF: synthetic field
   private Context zztF;
   // $FF: synthetic field
   private zzlf zzBq;

   zzlg(zzlf var1, Context var2) {
      this.zzBq = var1;
      this.zztF = var2;
      super();
   }

   public final void run() {
      this.zzBq.getRewardedVideoAdInstance(this.zztF);
   }
}
