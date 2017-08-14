package com.google.android.gms.internal;

import android.os.RemoteException;

final class zzvq implements Runnable {
   // $FF: synthetic field
   private zzvp zzNk;

   zzvq(zzvp var1) {
      this.zzNk = var1;
      super();
   }

   public final void run() {
      try {
         zzvp.zza(this.zzNk).onAdClicked();
      } catch (RemoteException var2) {
         zzajc.zzc("Could not call onAdClicked.", var2);
      }
   }
}
