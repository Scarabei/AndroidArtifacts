package com.google.android.gms.internal;

import android.os.RemoteException;

final class zztg implements Runnable {
   // $FF: synthetic field
   private zzth zzKg;
   // $FF: synthetic field
   private zzti zzKh;

   zztg(zzsj var1, zzth var2, zzti var3) {
      this.zzKg = var2;
      this.zzKh = var3;
      super();
   }

   public final void run() {
      try {
         this.zzKg.zzb(this.zzKh);
      } catch (RemoteException var2) {
         zzafr.zzc("Could not propagate interstitial ad event.", var2);
      }
   }
}
