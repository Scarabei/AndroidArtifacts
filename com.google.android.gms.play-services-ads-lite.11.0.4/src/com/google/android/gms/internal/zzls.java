package com.google.android.gms.internal;

import android.os.RemoteException;

final class zzls implements Runnable {
   // $FF: synthetic field
   private zzlr zzBv;

   zzls(zzlr var1) {
      this.zzBv = var1;
      super();
   }

   public final void run() {
      if (zzlr.zza(this.zzBv) != null) {
         try {
            zzlr.zza(this.zzBv).onRewardedVideoAdFailedToLoad(1);
            return;
         } catch (RemoteException var2) {
            zzajc.zzc("Could not notify onRewardedVideoAdFailedToLoad event.", var2);
         }
      }

   }
}
