package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzaxx;
import java.io.IOException;
import org.json.JSONObject;

final class zzal extends RemoteMediaPlayer.zzb {
   // $FF: synthetic field
   private GoogleApiClient zzaqW;
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
   private RemoteMediaPlayer zzaqV;

   zzal(RemoteMediaPlayer var1, GoogleApiClient var2, GoogleApiClient var3, MediaQueueItem[] var4, int var5, int var6, long var7, JSONObject var9) {
      this.zzaqV = var1;
      this.zzaqW = var3;
      this.zzaqZ = var4;
      this.zzara = var5;
      this.val$repeatMode = var6;
      this.zzarb = var7;
      this.zzarc = var9;
      super(var2);
   }

   protected final void zza(zzaxx var1) {
      synchronized(RemoteMediaPlayer.zze(this.zzaqV)) {
         RemoteMediaPlayer.zzf(this.zzaqV).zzb(this.zzaqW);

         try {
            RemoteMediaPlayer.zzg(this.zzaqV).zza(this.zzarw, this.zzaqZ, this.zzara, this.val$repeatMode, this.zzarb, this.zzarc);
         } catch (IOException var8) {
            this.setResult((RemoteMediaPlayer.MediaChannelResult)this.zzb(new Status(2100)));
         } finally {
            RemoteMediaPlayer.zzf(this.zzaqV).zzb((GoogleApiClient)null);
         }

      }
   }
}
