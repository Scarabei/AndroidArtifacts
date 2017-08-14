package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzaxx;
import java.io.IOException;
import org.json.JSONObject;

final class zzat extends RemoteMediaPlayer.zzb {
   // $FF: synthetic field
   private GoogleApiClient zzaqW;
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
   private RemoteMediaPlayer zzaqV;

   zzat(RemoteMediaPlayer var1, GoogleApiClient var2, GoogleApiClient var3, MediaInfo var4, boolean var5, long var6, long[] var8, JSONObject var9) {
      this.zzaqV = var1;
      this.zzaqW = var3;
      this.zzarj = var4;
      this.zzark = var5;
      this.zzarb = var6;
      this.zzarl = var8;
      this.zzarc = var9;
      super(var2);
   }

   protected final void zza(zzaxx var1) {
      synchronized(RemoteMediaPlayer.zze(this.zzaqV)) {
         RemoteMediaPlayer.zzf(this.zzaqV).zzb(this.zzaqW);

         try {
            RemoteMediaPlayer.zzg(this.zzaqV).zza(this.zzarw, this.zzarj, this.zzark, this.zzarb, this.zzarl, this.zzarc);
         } catch (IOException var8) {
            this.setResult((RemoteMediaPlayer.MediaChannelResult)this.zzb(new Status(2100)));
         } finally {
            RemoteMediaPlayer.zzf(this.zzaqV).zzb((GoogleApiClient)null);
         }

      }
   }
}
