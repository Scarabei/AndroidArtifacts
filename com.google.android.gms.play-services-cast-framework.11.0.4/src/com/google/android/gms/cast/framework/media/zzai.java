package com.google.android.gms.cast.framework.media;

import java.util.TimerTask;

final class zzai extends TimerTask {
   // $FF: synthetic field
   private RemoteMediaClient zzauF;
   // $FF: synthetic field
   private RemoteMediaClient.zzd zzauG;

   zzai(RemoteMediaClient.zzd var1, RemoteMediaClient var2) {
      this.zzauG = var1;
      this.zzauF = var2;
      super();
   }

   public final void run() {
      RemoteMediaClient.zza(this.zzauG.zzauy, RemoteMediaClient.zzd.zza(this.zzauG));
      RemoteMediaClient.zzg(this.zzauG.zzauy).postDelayed(this, RemoteMediaClient.zzd.zzb(this.zzauG));
   }
}
