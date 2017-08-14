package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzns;

final class zzbe implements Runnable {
   // $FF: synthetic field
   private zzns zztk;
   // $FF: synthetic field
   private zzbb zzuQ;

   zzbe(zzbb var1, zzns var2) {
      this.zzuQ = var1;
      this.zztk = var2;
      super();
   }

   public final void run() {
      try {
         if (this.zzuQ.zzsP.zzwg != null) {
            this.zzuQ.zzsP.zzwg.zza(this.zztk);
         }

      } catch (RemoteException var2) {
         zzafr.zzc("Could not call OnContentAdLoadedListener.onContentAdLoaded().", var2);
      }
   }
}
