package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzaxx;
import com.google.android.gms.internal.zzayr;
import java.io.IOException;
import org.json.JSONObject;

final class zzn extends RemoteMediaClient.zzb {
   // $FF: synthetic field
   private MediaQueueItem zzarf;
   // $FF: synthetic field
   private int zzare;
   // $FF: synthetic field
   private long zzarb;
   // $FF: synthetic field
   private JSONObject zzarc;
   // $FF: synthetic field
   private RemoteMediaClient zzauy;

   zzn(RemoteMediaClient var1, GoogleApiClient var2, MediaQueueItem var3, int var4, long var5, JSONObject var7) {
      this.zzauy = var1;
      this.zzarf = var3;
      this.zzare = var4;
      this.zzarb = var5;
      this.zzarc = var7;
      super(var2);
   }

   protected final void zza(zzaxx var1) {
      synchronized(RemoteMediaClient.zzd(this.zzauy)) {
         try {
            RemoteMediaClient.zze(this.zzauy).zza(this.zzarw, new MediaQueueItem[]{this.zzarf}, this.zzare, 0, 0, this.zzarb, this.zzarc);
         } catch (zzayr | IOException var4) {
            this.setResult((RemoteMediaClient.MediaChannelResult)this.zzb(new Status(2100)));
         }

      }
   }
}
