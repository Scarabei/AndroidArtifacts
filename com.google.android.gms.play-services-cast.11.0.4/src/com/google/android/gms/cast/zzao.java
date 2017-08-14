package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzaxx;
import com.google.android.gms.internal.zzayr;
import java.io.IOException;
import org.json.JSONObject;

final class zzao extends RemoteMediaPlayer.zzb {
   // $FF: synthetic field
   private GoogleApiClient zzaqW;
   // $FF: synthetic field
   private MediaQueueItem[] zzarg;
   // $FF: synthetic field
   private JSONObject zzarc;
   // $FF: synthetic field
   private RemoteMediaPlayer zzaqV;

   zzao(RemoteMediaPlayer var1, GoogleApiClient var2, GoogleApiClient var3, MediaQueueItem[] var4, JSONObject var5) {
      this.zzaqV = var1;
      this.zzaqW = var3;
      this.zzarg = var4;
      this.zzarc = var5;
      super(var2);
   }

   protected final void zza(zzaxx var1) {
      synchronized(RemoteMediaPlayer.zze(this.zzaqV)) {
         RemoteMediaPlayer.zzf(this.zzaqV).zzb(this.zzaqW);

         try {
            RemoteMediaPlayer.zzg(this.zzaqV).zza(this.zzarw, 0, -1L, this.zzarg, 0, (Integer)null, this.zzarc);
         } catch (zzayr | IOException var8) {
            this.setResult((RemoteMediaPlayer.MediaChannelResult)this.zzb(new Status(2100)));
         } finally {
            RemoteMediaPlayer.zzf(this.zzaqV).zzb((GoogleApiClient)null);
         }

      }
   }
}
