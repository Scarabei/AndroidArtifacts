package com.google.android.gms.internal;

import android.os.RemoteException;

final class zzvx implements Runnable {
   // $FF: synthetic field
   private zzvp zzNk;

   zzvx(zzvp var1) {
      this.zzNk = var1;
      super();
   }

   public final void run() {
      try {
         zzvp.zza(this.zzNk).onAdLoaded();
      } catch (RemoteException var2) {
         zzajc.zzc("Could not call onAdLoaded.", var2);
      }
   }
}
