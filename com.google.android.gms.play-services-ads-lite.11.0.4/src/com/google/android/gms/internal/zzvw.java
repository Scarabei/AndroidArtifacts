package com.google.android.gms.internal;

import android.os.RemoteException;

final class zzvw implements Runnable {
   // $FF: synthetic field
   private zzvp zzNk;

   zzvw(zzvp var1) {
      this.zzNk = var1;
      super();
   }

   public final void run() {
      try {
         zzvp.zza(this.zzNk).onAdOpened();
      } catch (RemoteException var2) {
         zzajc.zzc("Could not call onAdOpened.", var2);
      }
   }
}
