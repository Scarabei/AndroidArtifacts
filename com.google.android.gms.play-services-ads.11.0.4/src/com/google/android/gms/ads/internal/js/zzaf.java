package com.google.android.gms.ads.internal.js;

import com.google.android.gms.ads.internal.zzbs;
import com.google.android.gms.internal.zzagz;
import com.google.android.gms.internal.zzajs;

final class zzaf implements zzajs {
   // $FF: synthetic field
   final zzac zzLx;

   zzaf(zzac var1) {
      this.zzLx = var1;
   }

   // $FF: synthetic method
   public final void zzc(Object var1) {
      zza var3 = (zza)var1;
      zzbs.zzbz();
      zzagz.runOnUiThread(new zzag(this, var3));
   }
}
