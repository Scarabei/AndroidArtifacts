package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzaxx;
import com.google.android.gms.internal.zzayr;
import java.io.IOException;
import org.json.JSONObject;

final class zzaw extends RemoteMediaPlayer.zzb {
   // $FF: synthetic field
   private int zzarm;
   // $FF: synthetic field
   private GoogleApiClient zzaqW;
   // $FF: synthetic field
   private long zzarb;
   // $FF: synthetic field
   private JSONObject zzarc;
   // $FF: synthetic field
   private RemoteMediaPlayer zzaqV;

   zzaw(RemoteMediaPlayer var1, GoogleApiClient var2, int var3, GoogleApiClient var4, long var5, JSONObject var7) {
      this.zzaqV = var1;
      this.zzarm = var3;
      this.zzaqW = var4;
      this.zzarb = var5;
      this.zzarc = var7;
      super(var2);
   }

   protected final void zza(zzaxx var1) {
      synchronized(RemoteMediaPlayer.zze(this.zzaqV)) {
         if (RemoteMediaPlayer.zza(this.zzaqV, this.zzarm) == -1) {
            this.setResult((RemoteMediaPlayer.MediaChannelResult)this.zzb(new Status(0)));
         } else {
            RemoteMediaPlayer.zzf(this.zzaqV).zzb(this.zzaqW);

            try {
               RemoteMediaPlayer.zzg(this.zzaqV).zza(this.zzarw, this.zzarm, this.zzarb, (MediaQueueItem[])null, 0, (Integer)null, this.zzarc);
            } catch (zzayr | IOException var8) {
               this.setResult((RemoteMediaPlayer.MediaChannelResult)this.zzb(new Status(2100)));
            } finally {
               RemoteMediaPlayer.zzf(this.zzaqV).zzb((GoogleApiClient)null);
            }

         }
      }
   }
}
