package com.google.android.gms.internal;

import android.os.RemoteException;

final class zzuo implements Runnable {
   // $FF: synthetic field
   private zzuh zzMX;

   zzuo(zzun var1, zzuh var2) {
      this.zzMX = var2;
      super();
   }

   public final void run() {
      try {
         this.zzMX.zzMH.destroy();
      } catch (RemoteException var2) {
         zzafr.zzc("Could not destroy mediation adapter.", var2);
      }
   }
}
