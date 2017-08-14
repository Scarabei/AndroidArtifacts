package com.google.android.gms.analytics;

import com.google.android.gms.internal.zzaoc;

final class zze implements Runnable {
   // $FF: synthetic field
   private int zzadw;
   // $FF: synthetic field
   private zzaoc zzadv;
   // $FF: synthetic field
   private CampaignTrackingService zzadx;

   zze(CampaignTrackingService var1, int var2, zzaoc var3) {
      this.zzadx = var1;
      this.zzadw = var2;
      this.zzadv = var3;
      super();
   }

   public final void run() {
      boolean var1;
      if (var1 = this.zzadx.stopSelfResult(this.zzadw)) {
         this.zzadv.zza("Install campaign broadcast processed", var1);
      }

   }
}
