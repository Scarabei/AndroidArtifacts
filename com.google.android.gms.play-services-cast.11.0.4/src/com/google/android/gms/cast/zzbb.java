package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzaxx;
import com.google.android.gms.internal.zzayr;
import java.io.IOException;
import org.json.JSONObject;

final class zzbb extends RemoteMediaPlayer.zzb {
   // $FF: synthetic field
   private GoogleApiClient zzaqW;
   // $FF: synthetic field
   private long zzaro;
   // $FF: synthetic field
   private int zzarp;
   // $FF: synthetic field
   private JSONObject zzarc;
   // $FF: synthetic field
   private RemoteMediaPlayer zzaqV;

   zzbb(RemoteMediaPlayer var1, GoogleApiClient var2, GoogleApiClient var3, long var4, int var6, JSONObject var7) {
      this.zzaqV = var1;
      this.zzaqW = var3;
      this.zzaro = var4;
      this.zzarp = var6;
      this.zzarc = var7;
      super(var2);
   }

   protected final void zza(zzaxx var1) {
      synchronized(RemoteMediaPlayer.zze(this.zzaqV)) {
         RemoteMediaPlayer.zzf(this.zzaqV).zzb(this.zzaqW);

         try {
            RemoteMediaPlayer.zzg(this.zzaqV).zza(this.zzarw, this.zzaro, this.zzarp, this.zzarc);
         } catch (zzayr | IOException var8) {
            this.setResult((RemoteMediaPlayer.MediaChannelResult)this.zzb(new Status(2100)));
         } finally {
            RemoteMediaPlayer.zzf(this.zzaqV).zzb((GoogleApiClient)null);
         }

      }
   }
}
