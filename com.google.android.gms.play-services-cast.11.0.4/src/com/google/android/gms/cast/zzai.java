package com.google.android.gms.cast;

import com.google.android.gms.internal.zzayq;

final class zzai implements zzayq {
   // $FF: synthetic field
   private RemoteMediaPlayer zzaqV;

   zzai(RemoteMediaPlayer var1) {
      this.zzaqV = var1;
      super();
   }

   public final void onStatusUpdated() {
      RemoteMediaPlayer.zza(this.zzaqV);
   }

   public final void onMetadataUpdated() {
      RemoteMediaPlayer.zzb(this.zzaqV);
   }

   public final void onQueueStatusUpdated() {
      RemoteMediaPlayer.zzc(this.zzaqV);
   }

   public final void onPreloadStatusUpdated() {
      RemoteMediaPlayer.zzd(this.zzaqV);
   }

   public final void onAdBreakStatusUpdated() {
   }
}
