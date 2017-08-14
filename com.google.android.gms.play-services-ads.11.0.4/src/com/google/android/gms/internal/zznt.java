package com.google.android.gms.internal;

final class zznt implements Runnable {
   // $FF: synthetic field
   private zzns zzHQ;

   zznt(zzns var1) {
      this.zzHQ = var1;
      super();
   }

   public final void run() {
      if (zzns.zzb(this.zzHQ) != null) {
         zzns.zzb(this.zzHQ).zzev();
         zzns.zzb(this.zzHQ).zzet();
      }

      zzns.zza(this.zzHQ, (zzny)null);
   }
}
