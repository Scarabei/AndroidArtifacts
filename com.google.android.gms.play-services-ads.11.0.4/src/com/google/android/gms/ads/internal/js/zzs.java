package com.google.android.gms.ads.internal.js;

import com.google.android.gms.ads.internal.zzbs;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzagz;

final class zzs implements Runnable {
   // $FF: synthetic field
   final zza zzLi;
   // $FF: synthetic field
   private zzm zzLj;

   zzs(zzm var1, zza var2) {
      this.zzLj = var1;
      this.zzLi = var2;
      super();
   }

   public final void run() {
      synchronized(zzl.zzc(this.zzLj.zzLh)) {
         if (this.zzLj.zzLg.getStatus() != -1 && this.zzLj.zzLg.getStatus() != 1) {
            this.zzLj.zzLg.reject();
            zzbs.zzbz();
            zzagz.runOnUiThread(new zzt(this));
            zzafr.v("Could not receive loaded message in a timely manner. Rejecting.");
         }
      }
   }
}
