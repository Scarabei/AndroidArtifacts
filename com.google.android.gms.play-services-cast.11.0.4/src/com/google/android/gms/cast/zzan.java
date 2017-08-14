package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzaxx;
import com.google.android.gms.internal.zzayr;
import java.io.IOException;
import org.json.JSONObject;

final class zzan extends RemoteMediaPlayer.zzb {
   // $FF: synthetic field
   private GoogleApiClient zzaqW;
   // $FF: synthetic field
   private MediaQueueItem zzarf;
   // $FF: synthetic field
   private int zzare;
   // $FF: synthetic field
   private long zzarb;
   // $FF: synthetic field
   private JSONObject zzarc;
   // $FF: synthetic field
   private RemoteMediaPlayer zzaqV;

   zzan(RemoteMediaPlayer var1, GoogleApiClient var2, GoogleApiClient var3, MediaQueueItem var4, int var5, long var6, JSONObject var8) {
      this.zzaqV = var1;
      this.zzaqW = var3;
      this.zzarf = var4;
      this.zzare = var5;
      this.zzarb = var6;
      this.zzarc = var8;
      super(var2);
   }

   protected final void zza(zzaxx var1) {
      synchronized(RemoteMediaPlayer.zze(this.zzaqV)) {
         RemoteMediaPlayer.zzf(this.zzaqV).zzb(this.zzaqW);

         try {
            RemoteMediaPlayer.zzg(this.zzaqV).zza(this.zzarw, new MediaQueueItem[]{this.zzarf}, this.zzare, 0, 0, this.zzarb, this.zzarc);
         } catch (zzayr | IOException var8) {
            this.setResult((RemoteMediaPlayer.MediaChannelResult)this.zzb(new Status(2100)));
         } finally {
            RemoteMediaPlayer.zzf(this.zzaqV).zzb((GoogleApiClient)null);
         }

      }
   }
}
