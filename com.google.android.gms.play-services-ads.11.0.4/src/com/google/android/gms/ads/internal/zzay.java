package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.zzagz;

final class zzay implements Runnable {
   // $FF: synthetic field
   final Runnable zzuL;
   // $FF: synthetic field
   final zzax zzuM;

   zzay(zzax var1, Runnable var2) {
      this.zzuM = var1;
      this.zzuL = var2;
   }

   public final void run() {
      zzbs.zzbz();
      zzagz.runOnUiThread(new zzaz(this));
   }
}
