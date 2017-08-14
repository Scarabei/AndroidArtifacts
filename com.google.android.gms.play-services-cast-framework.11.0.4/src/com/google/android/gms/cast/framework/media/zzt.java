package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzaxx;
import java.io.IOException;
import org.json.JSONObject;

final class zzt extends RemoteMediaClient.zzb {
   // $FF: synthetic field
   private MediaInfo zzarj;
   // $FF: synthetic field
   private boolean zzark;
   // $FF: synthetic field
   private long zzarb;
   // $FF: synthetic field
   private long[] zzarl;
   // $FF: synthetic field
   private JSONObject zzarc;
   // $FF: synthetic field
   private RemoteMediaClient zzauy;

   zzt(RemoteMediaClient var1, GoogleApiClient var2, MediaInfo var3, boolean var4, long var5, long[] var7, JSONObject var8) {
      this.zzauy = var1;
      this.zzarj = var3;
      this.zzark = var4;
      this.zzarb = var5;
      this.zzarl = var7;
      this.zzarc = var8;
      super(var2);
   }

   protected final void zza(zzaxx var1) {
      synchronized(RemoteMediaClient.zzd(this.zzauy)) {
         try {
            RemoteMediaClient.zze(this.zzauy).zza(this.zzarw, this.zzarj, this.zzark, this.zzarb, this.zzarl, this.zzarc);
         } catch (IOException | IllegalStateException var4) {
            this.setResult((RemoteMediaClient.MediaChannelResult)this.zzb(new Status(2100)));
         }

      }
   }
}
