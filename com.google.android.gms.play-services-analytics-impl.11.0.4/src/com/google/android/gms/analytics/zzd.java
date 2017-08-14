package com.google.android.gms.analytics;

import android.os.Handler;
import com.google.android.gms.internal.zzaoc;

final class zzd implements Runnable {
   // $FF: synthetic field
   private zzaoc zzadv;
   // $FF: synthetic field
   private Handler zzs;
   // $FF: synthetic field
   private int zzadw;
   // $FF: synthetic field
   private CampaignTrackingService zzadx;

   zzd(CampaignTrackingService var1, zzaoc var2, Handler var3, int var4) {
      this.zzadx = var1;
      this.zzadv = var2;
      this.zzs = var3;
      this.zzadw = var4;
      super();
   }

   public final void run() {
      this.zzadx.zza(this.zzadv, this.zzs, this.zzadw);
   }
}
