package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzaxx;
import com.google.android.gms.internal.zzayr;
import java.io.IOException;
import org.json.JSONObject;

final class zzw extends RemoteMediaClient.zzb {
   // $FF: synthetic field
   private int zzarm;
   // $FF: synthetic field
   private long zzarb;
   // $FF: synthetic field
   private JSONObject zzarc;
   // $FF: synthetic field
   private RemoteMediaClient zzauy;

   zzw(RemoteMediaClient var1, GoogleApiClient var2, int var3, long var4, JSONObject var6) {
      this.zzauy = var1;
      this.zzarm = var3;
      this.zzarb = var4;
      this.zzarc = var6;
      super(var2);
   }

   protected final void zza(zzaxx var1) {
      synchronized(RemoteMediaClient.zzd(this.zzauy)) {
         if (RemoteMediaClient.zza(this.zzauy, this.zzarm) == -1) {
            this.setResult((RemoteMediaClient.MediaChannelResult)this.zzb(new Status(0)));
         } else {
            try {
               RemoteMediaClient.zze(this.zzauy).zza(this.zzarw, this.zzarm, this.zzarb, (MediaQueueItem[])null, 0, (Integer)null, this.zzarc);
            } catch (zzayr | IOException var4) {
               this.setResult((RemoteMediaClient.MediaChannelResult)this.zzb(new Status(2100)));
            }

         }
      }
   }
}
