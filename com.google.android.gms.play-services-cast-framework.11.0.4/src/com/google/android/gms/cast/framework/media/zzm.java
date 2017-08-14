package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzaxx;
import com.google.android.gms.internal.zzayr;
import java.io.IOException;
import org.json.JSONObject;

final class zzm extends RemoteMediaClient.zzb {
   // $FF: synthetic field
   private MediaQueueItem[] zzard;
   // $FF: synthetic field
   private int zzare;
   // $FF: synthetic field
   private JSONObject zzarc;
   // $FF: synthetic field
   private RemoteMediaClient zzauy;

   zzm(RemoteMediaClient var1, GoogleApiClient var2, MediaQueueItem[] var3, int var4, JSONObject var5) {
      this.zzauy = var1;
      this.zzard = var3;
      this.zzare = var4;
      this.zzarc = var5;
      super(var2);
   }

   protected final void zza(zzaxx var1) {
      synchronized(RemoteMediaClient.zzd(this.zzauy)) {
         try {
            RemoteMediaClient.zze(this.zzauy).zza(this.zzarw, this.zzard, this.zzare, 0, -1, -1L, this.zzarc);
         } catch (zzayr | IOException var4) {
            this.setResult((RemoteMediaClient.MediaChannelResult)this.zzb(new Status(2100)));
         }

      }
   }
}
