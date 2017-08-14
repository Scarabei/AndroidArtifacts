package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.ads.AdRequest;

final class zzvz implements Runnable {
   // $FF: synthetic field
   private AdRequest.ErrorCode zzNl;
   // $FF: synthetic field
   private zzvp zzNk;

   zzvz(zzvp var1, AdRequest.ErrorCode var2) {
      this.zzNk = var1;
      this.zzNl = var2;
      super();
   }

   public final void run() {
      try {
         zzvp.zza(this.zzNk).onAdFailedToLoad(zzwb.zza(this.zzNl));
      } catch (RemoteException var2) {
         zzajc.zzc("Could not call onAdFailedToLoad.", var2);
      }
   }
}
