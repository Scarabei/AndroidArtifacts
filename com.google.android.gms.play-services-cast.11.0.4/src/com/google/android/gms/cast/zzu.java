package com.google.android.gms.cast;

final class zzu implements Runnable {
   // $FF: synthetic field
   private CastRemoteDisplayLocalService.NotificationSettings zzapM;
   // $FF: synthetic field
   private CastRemoteDisplayLocalService zzapJ;

   zzu(CastRemoteDisplayLocalService var1, CastRemoteDisplayLocalService.NotificationSettings var2) {
      this.zzapJ = var1;
      this.zzapM = var2;
      super();
   }

   public final void run() {
      CastRemoteDisplayLocalService.zza(this.zzapJ, this.zzapM);
   }
}
