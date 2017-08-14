package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzns;

final class zzt implements Runnable {
   // $FF: synthetic field
   private zzns zztk;
   // $FF: synthetic field
   private zzq zzti;

   zzt(zzq var1, zzns var2) {
      this.zzti = var1;
      this.zztk = var2;
      super();
   }

   public final void run() {
      try {
         if (this.zzti.zzsP.zzwg != null) {
            this.zzti.zzsP.zzwg.zza(this.zztk);
         }

      } catch (RemoteException var2) {
         zzafr.zzc("Could not call OnContentAdLoadedListener.onContentAdLoaded().", var2);
      }
   }
}
