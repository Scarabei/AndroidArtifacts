package com.google.android.gms.cast.framework.media;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzaxx;
import com.google.android.gms.internal.zzayr;
import java.io.IOException;
import org.json.JSONObject;

final class zzab extends RemoteMediaClient.zzb {
   // $FF: synthetic field
   private long zzaro;
   // $FF: synthetic field
   private int zzarp;
   // $FF: synthetic field
   private JSONObject zzarc;
   // $FF: synthetic field
   private RemoteMediaClient zzauy;

   zzab(RemoteMediaClient var1, GoogleApiClient var2, long var3, int var5, JSONObject var6) {
      this.zzauy = var1;
      this.zzaro = var3;
      this.zzarp = var5;
      this.zzarc = var6;
      super(var2);
   }

   protected final void zza(zzaxx var1) {
      synchronized(RemoteMediaClient.zzd(this.zzauy)) {
         try {
            RemoteMediaClient.zze(this.zzauy).zza(this.zzarw, this.zzaro, this.zzarp, this.zzarc);
         } catch (zzayr | IOException var4) {
            this.setResult((RemoteMediaClient.MediaChannelResult)this.zzb(new Status(2100)));
         }

      }
   }
}
