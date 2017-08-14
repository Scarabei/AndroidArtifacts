package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzpj;
import com.google.android.gms.internal.zzpw;

final class zzu implements Runnable {
   // $FF: synthetic field
   private zzpj zztl;
   // $FF: synthetic field
   private zzq zzti;

   zzu(zzq var1, zzpj var2) {
      this.zzti = var1;
      this.zztl = var2;
      super();
   }

   public final void run() {
      try {
         String var1 = this.zztl.getCustomTemplateId();
         ((zzpw)this.zzti.zzsP.zzwi.get(var1)).zza(this.zztl);
      } catch (RemoteException var2) {
         zzafr.zzc("Could not call onCustomTemplateAdLoadedListener.onCustomTemplateAdLoaded().", var2);
      }
   }
}
