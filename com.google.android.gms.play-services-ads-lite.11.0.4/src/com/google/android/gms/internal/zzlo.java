package com.google.android.gms.internal;

import android.os.RemoteException;

final class zzlo implements Runnable {
   // $FF: synthetic field
   private zzln zzBt;

   zzlo(zzln var1) {
      this.zzBt = var1;
      super();
   }

   public final void run() {
      if (zzln.zza(this.zzBt) != null) {
         try {
            zzln.zza(this.zzBt).onAdFailedToLoad(1);
            return;
         } catch (RemoteException var2) {
            zzajc.zzc("Could not notify onAdFailedToLoad event.", var2);
         }
      }

   }
}
