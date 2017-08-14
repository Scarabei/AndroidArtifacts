package com.google.android.gms.internal;

final class zznr implements Runnable {
   // $FF: synthetic field
   private zznq zzHN;

   zznr(zznq var1) {
      this.zzHN = var1;
      super();
   }

   public final void run() {
      if (zznq.zzb(this.zzHN) != null) {
         zznq.zzb(this.zzHN).zzev();
         zznq.zzb(this.zzHN).zzet();
      }

      zznq.zza(this.zzHN, (zzny)null);
   }
}
