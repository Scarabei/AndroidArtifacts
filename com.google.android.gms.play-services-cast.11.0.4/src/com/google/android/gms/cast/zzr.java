package com.google.android.gms.cast;

final class zzr implements Runnable {
   // $FF: synthetic field
   private CastRemoteDisplayLocalService zzapJ;

   zzr(CastRemoteDisplayLocalService var1) {
      this.zzapJ = var1;
      super();
   }

   public final void run() {
      CastRemoteDisplayLocalService var10000 = this.zzapJ;
      boolean var1 = CastRemoteDisplayLocalService.zzb(this.zzapJ);
      CastRemoteDisplayLocalService.zza(var10000, (new StringBuilder(59)).append("onCreate after delay. The local service been started: ").append(var1).toString());
      if (!CastRemoteDisplayLocalService.zzb(this.zzapJ)) {
         CastRemoteDisplayLocalService.zzb(this.zzapJ, "The local service has not been been started, stopping it");
         this.zzapJ.stopSelf();
      }

   }
}
