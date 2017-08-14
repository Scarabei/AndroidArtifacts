package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.internal.zzayq;
import java.util.Iterator;
import java.util.List;

final class zzi implements zzayq {
   // $FF: synthetic field
   private RemoteMediaClient zzauy;

   zzi(RemoteMediaClient var1) {
      this.zzauy = var1;
      super();
   }

   public final void onStatusUpdated() {
      this.zzoa();
      RemoteMediaClient.zza(this.zzauy);
      Iterator var1 = RemoteMediaClient.zzb(this.zzauy).iterator();

      while(var1.hasNext()) {
         ((RemoteMediaClient.Listener)var1.next()).onStatusUpdated();
      }

   }

   public final void onMetadataUpdated() {
      this.zzoa();
      Iterator var1 = RemoteMediaClient.zzb(this.zzauy).iterator();

      while(var1.hasNext()) {
         ((RemoteMediaClient.Listener)var1.next()).onMetadataUpdated();
      }

   }

   public final void onQueueStatusUpdated() {
      Iterator var1 = RemoteMediaClient.zzb(this.zzauy).iterator();

      while(var1.hasNext()) {
         ((RemoteMediaClient.Listener)var1.next()).onQueueStatusUpdated();
      }

   }

   public final void onPreloadStatusUpdated() {
      Iterator var1 = RemoteMediaClient.zzb(this.zzauy).iterator();

      while(var1.hasNext()) {
         ((RemoteMediaClient.Listener)var1.next()).onPreloadStatusUpdated();
      }

   }

   public final void onAdBreakStatusUpdated() {
      Iterator var1 = RemoteMediaClient.zzb(this.zzauy).iterator();

      while(var1.hasNext()) {
         ((RemoteMediaClient.Listener)var1.next()).onAdBreakStatusUpdated();
      }

   }

   private final void zzoa() {
      MediaStatus var1;
      if (RemoteMediaClient.zzc(this.zzauy) != null && (var1 = this.zzauy.getMediaStatus()) != null) {
         var1.zzV(RemoteMediaClient.zzc(this.zzauy).parseIsPlayingAdFromMediaStatus(var1));
         List var2 = RemoteMediaClient.zzc(this.zzauy).parseAdBreaksFromMediaStatus(var1);
         MediaInfo var3;
         if ((var3 = this.zzauy.getMediaInfo()) != null) {
            var3.zzz(var2);
         }
      }

   }
}
