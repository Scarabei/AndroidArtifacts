package com.google.android.gms.ads.internal.js;

import com.google.android.gms.ads.internal.zzbs;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzagz;

final class zzo implements Runnable {
   // $FF: synthetic field
   final zzn zzLk;

   zzo(zzn var1) {
      this.zzLk = var1;
   }

   public final void run() {
      synchronized(zzl.zzc(this.zzLk.zzLj.zzLh)) {
         if (this.zzLk.zzLj.zzLg.getStatus() != -1 && this.zzLk.zzLj.zzLg.getStatus() != 1) {
            this.zzLk.zzLj.zzLg.reject();
            zzbs.zzbz();
            zzagz.runOnUiThread(new zzp(this));
            zzafr.v("Could not receive loaded message in a timely manner. Rejecting.");
         }
      }
   }
}
