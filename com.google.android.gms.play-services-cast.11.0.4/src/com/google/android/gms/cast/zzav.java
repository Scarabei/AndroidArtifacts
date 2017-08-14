package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzaxx;
import com.google.android.gms.internal.zzayr;
import java.io.IOException;
import org.json.JSONObject;

final class zzav extends RemoteMediaPlayer.zzb {
   // $FF: synthetic field
   private int zzarm;
   // $FF: synthetic field
   private GoogleApiClient zzaqW;
   // $FF: synthetic field
   private JSONObject zzarc;
   // $FF: synthetic field
   private RemoteMediaPlayer zzaqV;

   zzav(RemoteMediaPlayer var1, GoogleApiClient var2, int var3, GoogleApiClient var4, JSONObject var5) {
      this.zzaqV = var1;
      this.zzarm = var3;
      this.zzaqW = var4;
      this.zzarc = var5;
      super(var2);
   }

   protected final void zza(zzaxx var1) {
      synchronized(RemoteMediaPlayer.zze(this.zzaqV)) {
         if (RemoteMediaPlayer.zza(this.zzaqV, this.zzarm) == -1) {
            this.setResult((RemoteMediaPlayer.MediaChannelResult)this.zzb(new Status(0)));
         } else {
            RemoteMediaPlayer.zzf(this.zzaqV).zzb(this.zzaqW);

            try {
               RemoteMediaPlayer.zzg(this.zzaqV).zza(this.zzarw, new int[]{this.zzarm}, this.zzarc);
            } catch (zzayr | IOException var8) {
               this.setResult((RemoteMediaPlayer.MediaChannelResult)this.zzb(new Status(2100)));
            } finally {
               RemoteMediaPlayer.zzf(this.zzaqV).zzb((GoogleApiClient)null);
            }

         }
      }
   }
}
