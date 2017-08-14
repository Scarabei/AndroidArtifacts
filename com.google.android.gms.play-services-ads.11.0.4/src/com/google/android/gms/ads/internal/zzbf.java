package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.zzaff;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zznu;
import com.google.android.gms.internal.zzpw;

final class zzbf implements Runnable {
   // $FF: synthetic field
   private String zzuR;
   // $FF: synthetic field
   private zzaff zztx;
   // $FF: synthetic field
   private zzbb zzuQ;

   zzbf(zzbb var1, String var2, zzaff var3) {
      this.zzuQ = var1;
      this.zzuR = var2;
      this.zztx = var3;
      super();
   }

   public final void run() {
      try {
         ((zzpw)this.zzuQ.zzsP.zzwi.get(this.zzuR)).zza((zznu)this.zztx.zzXT);
      } catch (RemoteException var2) {
         zzafr.zzc("Could not call onCustomTemplateAdLoadedListener.onCustomTemplateAdLoaded().", var2);
      }
   }
}
