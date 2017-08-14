package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zznq;

final class zzbd implements Runnable {
   // $FF: synthetic field
   private zznq zztj;
   // $FF: synthetic field
   private zzbb zzuQ;

   zzbd(zzbb var1, zznq var2) {
      this.zzuQ = var1;
      this.zztj = var2;
      super();
   }

   public final void run() {
      try {
         if (this.zzuQ.zzsP.zzwf != null) {
            this.zzuQ.zzsP.zzwf.zza(this.zztj);
         }

      } catch (RemoteException var2) {
         zzafr.zzc("Could not call OnAppInstallAdLoadedListener.onAppInstallAdLoaded().", var2);
      }
   }
}
