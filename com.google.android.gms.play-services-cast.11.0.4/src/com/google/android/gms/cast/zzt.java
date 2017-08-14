package com.google.android.gms.cast;

final class zzt implements Runnable {
   // $FF: synthetic field
   private boolean zzapP;
   // $FF: synthetic field
   private CastRemoteDisplayLocalService zzapJ;

   zzt(CastRemoteDisplayLocalService var1, boolean var2) {
      this.zzapJ = var1;
      this.zzapP = var2;
      super();
   }

   public final void run() {
      CastRemoteDisplayLocalService.zza(this.zzapJ, this.zzapP);
   }
}
