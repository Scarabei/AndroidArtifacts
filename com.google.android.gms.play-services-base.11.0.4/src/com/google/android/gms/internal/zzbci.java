package com.google.android.gms.internal;

import android.app.PendingIntent;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzj;

final class zzbci extends zzbcy {
   // $FF: synthetic field
   private zzj zzaDu;

   zzbci(zzbcg var1, zzbcw var2, zzj var3) {
      this.zzaDu = var3;
      super(var2);
   }

   public final void zzpV() {
      this.zzaDu.zzf(new ConnectionResult(16, (PendingIntent)null));
   }
}
