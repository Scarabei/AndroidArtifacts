package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzaxx;
import com.google.android.gms.internal.zzayr;
import java.io.IOException;
import java.util.Locale;
import org.json.JSONObject;

final class zzx extends RemoteMediaClient.zzb {
   // $FF: synthetic field
   private int zzarm;
   // $FF: synthetic field
   private int zzarn;
   // $FF: synthetic field
   private JSONObject zzarc;
   // $FF: synthetic field
   private RemoteMediaClient zzauy;

   zzx(RemoteMediaClient var1, GoogleApiClient var2, int var3, int var4, JSONObject var5) {
      this.zzauy = var1;
      this.zzarm = var3;
      this.zzarn = var4;
      this.zzarc = var5;
      super(var2);
   }

   protected final void zza(zzaxx var1) {
      synchronized(RemoteMediaClient.zzd(this.zzauy)) {
         int var3;
         if ((var3 = RemoteMediaClient.zza(this.zzauy, this.zzarm)) == -1) {
            this.setResult((RemoteMediaClient.MediaChannelResult)this.zzb(new Status(0)));
         } else if (this.zzarn < 0) {
            this.setResult((RemoteMediaClient.MediaChannelResult)this.zzb(new Status(2001, String.format(Locale.ROOT, "Invalid request: Invalid newIndex %d.", this.zzarn))));
         } else if (var3 == this.zzarn) {
            this.setResult((RemoteMediaClient.MediaChannelResult)this.zzb(new Status(0)));
         } else {
            int var4 = this.zzarn > var3 ? this.zzarn + 1 : this.zzarn;
            MediaQueueItem var5 = this.zzauy.getMediaStatus().getQueueItem(var4);
            int var6 = 0;
            if (var5 != null) {
               var6 = var5.getItemId();
            }

            try {
               RemoteMediaClient.zze(this.zzauy).zza(this.zzarw, new int[]{this.zzarm}, var6, this.zzarc);
            } catch (zzayr | IOException var8) {
               this.setResult((RemoteMediaClient.MediaChannelResult)this.zzb(new Status(2100)));
            }

         }
      }
   }
}
