package com.google.android.gms.internal;

import android.os.RemoteException;

final class zzlm implements Runnable {
   // $FF: synthetic field
   private zzll zzBs;

   zzlm(zzll var1) {
      this.zzBs = var1;
      super();
   }

   public final void run() {
      if (zzlj.zza(this.zzBs.zzBr) != null) {
         try {
            zzlj.zza(this.zzBs.zzBr).onAdFailedToLoad(1);
            return;
         } catch (RemoteException var2) {
            zzajc.zzc("Could not notify onAdFailedToLoad event.", var2);
         }
      }

   }
}
