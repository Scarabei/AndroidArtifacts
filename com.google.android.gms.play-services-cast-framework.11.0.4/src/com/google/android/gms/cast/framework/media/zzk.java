package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.TextTrackStyle;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzaxx;
import com.google.android.gms.internal.zzayr;
import java.io.IOException;

final class zzk extends RemoteMediaClient.zzb {
   // $FF: synthetic field
   private TextTrackStyle zzaqY;
   // $FF: synthetic field
   private RemoteMediaClient zzauy;

   zzk(RemoteMediaClient var1, GoogleApiClient var2, TextTrackStyle var3) {
      this.zzauy = var1;
      this.zzaqY = var3;
      super(var2);
   }

   protected final void zza(zzaxx var1) {
      synchronized(RemoteMediaClient.zzd(this.zzauy)) {
         try {
            RemoteMediaClient.zze(this.zzauy).zza(this.zzarw, this.zzaqY);
         } catch (zzayr | IOException var4) {
            this.setResult((RemoteMediaClient.MediaChannelResult)this.zzb(new Status(2100)));
         }

      }
   }
}
