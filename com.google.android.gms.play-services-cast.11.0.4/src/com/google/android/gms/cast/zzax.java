package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzaxx;
import com.google.android.gms.internal.zzayr;
import java.io.IOException;
import java.util.Locale;
import org.json.JSONObject;

final class zzax extends RemoteMediaPlayer.zzb {
   // $FF: synthetic field
   private int zzarm;
   // $FF: synthetic field
   private int zzarn;
   // $FF: synthetic field
   private GoogleApiClient zzaqW;
   // $FF: synthetic field
   private JSONObject zzarc;
   // $FF: synthetic field
   private RemoteMediaPlayer zzaqV;

   zzax(RemoteMediaPlayer var1, GoogleApiClient var2, int var3, int var4, GoogleApiClient var5, JSONObject var6) {
      this.zzaqV = var1;
      this.zzarm = var3;
      this.zzarn = var4;
      this.zzaqW = var5;
      this.zzarc = var6;
      super(var2);
   }

   protected final void zza(zzaxx var1) {
      synchronized(RemoteMediaPlayer.zze(this.zzaqV)) {
         int var3;
         if ((var3 = RemoteMediaPlayer.zza(this.zzaqV, this.zzarm)) == -1) {
            this.setResult((RemoteMediaPlayer.MediaChannelResult)this.zzb(new Status(0)));
         } else if (this.zzarn < 0) {
            this.setResult((RemoteMediaPlayer.MediaChannelResult)this.zzb(new Status(2001, String.format(Locale.ROOT, "Invalid request: Invalid newIndex %d.", this.zzarn))));
         } else if (var3 == this.zzarn) {
            this.setResult((RemoteMediaPlayer.MediaChannelResult)this.zzb(new Status(0)));
         } else {
            int var4 = this.zzarn > var3 ? this.zzarn + 1 : this.zzarn;
            MediaQueueItem var5 = this.zzaqV.getMediaStatus().getQueueItem(var4);
            int var6 = 0;
            if (var5 != null) {
               var6 = var5.getItemId();
            }

            RemoteMediaPlayer.zzf(this.zzaqV).zzb(this.zzaqW);

            try {
               RemoteMediaPlayer.zzg(this.zzaqV).zza(this.zzarw, new int[]{this.zzarm}, var6, this.zzarc);
            } catch (zzayr | IOException var12) {
               this.setResult((RemoteMediaPlayer.MediaChannelResult)this.zzb(new Status(2100)));
            } finally {
               RemoteMediaPlayer.zzf(this.zzaqV).zzb((GoogleApiClient)null);
            }

         }
      }
   }
}
