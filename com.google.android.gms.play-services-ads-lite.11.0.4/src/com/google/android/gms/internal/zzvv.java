package com.google.android.gms.internal;

import android.os.RemoteException;

final class zzvv implements Runnable {
   // $FF: synthetic field
   private zzvp zzNk;

   zzvv(zzvp var1) {
      this.zzNk = var1;
      super();
   }

   public final void run() {
      try {
         zzvp.zza(this.zzNk).onAdLeftApplication();
      } catch (RemoteException var2) {
         zzajc.zzc("Could not call onAdLeftApplication.", var2);
      }
   }
}
