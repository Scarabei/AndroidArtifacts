package com.google.android.gms.cast.framework.media;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzaxx;
import com.google.android.gms.internal.zzayr;
import java.io.IOException;
import org.json.JSONObject;

final class zzp extends RemoteMediaClient.zzb {
   // $FF: synthetic field
   private int[] zzarh;
   // $FF: synthetic field
   private JSONObject zzarc;
   // $FF: synthetic field
   private RemoteMediaClient zzauy;

   zzp(RemoteMediaClient var1, GoogleApiClient var2, int[] var3, JSONObject var4) {
      this.zzauy = var1;
      this.zzarh = var3;
      this.zzarc = var4;
      super(var2);
   }

   protected final void zza(zzaxx var1) {
      synchronized(RemoteMediaClient.zzd(this.zzauy)) {
         try {
            RemoteMediaClient.zze(this.zzauy).zza(this.zzarw, this.zzarh, this.zzarc);
         } catch (zzayr | IOException var4) {
            this.setResult((RemoteMediaClient.MediaChannelResult)this.zzb(new Status(2100)));
         }

      }
   }
}
