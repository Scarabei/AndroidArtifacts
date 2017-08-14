package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzpj;
import com.google.android.gms.internal.zzpw;

final class zzbg implements Runnable {
   // $FF: synthetic field
   private zzpj zztl;
   // $FF: synthetic field
   private zzbb zzuQ;

   zzbg(zzbb var1, zzpj var2) {
      this.zzuQ = var1;
      this.zztl = var2;
      super();
   }

   public final void run() {
      try {
         String var1 = this.zztl.getCustomTemplateId();
         ((zzpw)this.zzuQ.zzsP.zzwi.get(var1)).zza(this.zztl);
      } catch (RemoteException var2) {
         zzafr.zzc("Could not call onCustomTemplateAdLoadedListener.onCustomTemplateAdLoaded().", var2);
      }
   }
}
