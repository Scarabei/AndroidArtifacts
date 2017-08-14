package com.google.android.gms.internal;

import android.os.RemoteException;

final class zzvy implements Runnable {
   // $FF: synthetic field
   private zzvp zzNk;

   zzvy(zzvp var1) {
      this.zzNk = var1;
      super();
   }

   public final void run() {
      try {
         zzvp.zza(this.zzNk).onAdClosed();
      } catch (RemoteException var2) {
         zzajc.zzc("Could not call onAdClosed.", var2);
      }
   }
}
