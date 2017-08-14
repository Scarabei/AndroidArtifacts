package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzaxx;
import java.io.IOException;
import org.json.JSONObject;

final class zzl extends RemoteMediaClient.zzb {
   // $FF: synthetic field
   private MediaQueueItem[] zzaqZ;
   // $FF: synthetic field
   private int zzara;
   // $FF: synthetic field
   private int val$repeatMode;
   // $FF: synthetic field
   private long zzarb;
   // $FF: synthetic field
   private JSONObject zzarc;
   // $FF: synthetic field
   private RemoteMediaClient zzauy;

   zzl(RemoteMediaClient var1, GoogleApiClient var2, MediaQueueItem[] var3, int var4, int var5, long var6, JSONObject var8) {
      this.zzauy = var1;
      this.zzaqZ = var3;
      this.zzara = var4;
      this.val$repeatMode = var5;
      this.zzarb = var6;
      this.zzarc = var8;
      super(var2);
   }

   protected final void zza(zzaxx var1) {
      synchronized(RemoteMediaClient.zzd(this.zzauy)) {
         try {
            RemoteMediaClient.zze(this.zzauy).zza(this.zzarw, this.zzaqZ, this.zzara, this.val$repeatMode, this.zzarb, this.zzarc);
         } catch (IOException var4) {
            this.setResult((RemoteMediaClient.MediaChannelResult)this.zzb(new Status(2100)));
         }

      }
   }
}
