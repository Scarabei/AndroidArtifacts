package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.net.Uri;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.MediaMetadataCompat.Builder;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.MediaSessionCompat.Callback;
import android.text.TextUtils;
import com.google.android.gms.R.string;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.ReconnectionService;
import com.google.android.gms.cast.framework.media.MediaNotificationService;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.util.zzq;

public final class zzavn implements RemoteMediaClient.Listener {
   private final Context zzarM;
   private final CastOptions zzauY;
   private final zzavb zzasB;
   private final ComponentName zzauZ;
   private final zzavc zzava;
   private final zzavc zzavb;
   private RemoteMediaClient zzase;
   private CastDevice zzaoX;
   private MediaSessionCompat zzavc;
   private Callback zzavd;
   private boolean mIsAttached;

   public zzavn(Context var1, CastOptions var2, zzavb var3) {
      this.zzarM = var1;
      this.zzauY = var2;
      this.zzasB = var3;
      if (this.zzauY.getCastMediaOptions() != null && !TextUtils.isEmpty(this.zzauY.getCastMediaOptions().getExpandedControllerActivityClassName())) {
         this.zzauZ = new ComponentName(this.zzarM, this.zzauY.getCastMediaOptions().getExpandedControllerActivityClassName());
      } else {
         this.zzauZ = null;
      }

      this.zzava = new zzavc(this.zzarM);
      this.zzava.zza(new zzavo(this));
      this.zzavb = new zzavc(this.zzarM);
      this.zzavb.zza(new zzavp(this));
   }

   public final void zza(RemoteMediaClient var1, CastDevice var2) {
      if (!this.mIsAttached && this.zzauY != null && this.zzauY.getCastMediaOptions() != null && var1 != null && var2 != null) {
         this.zzase = var1;
         this.zzase.addListener(this);
         this.zzaoX = var2;
         if (!zzq.zzse()) {
            ((AudioManager)this.zzarM.getSystemService("audio")).requestAudioFocus((OnAudioFocusChangeListener)null, 3, 3);
         }

         ComponentName var3 = new ComponentName(this.zzarM, this.zzauY.getCastMediaOptions().getMediaIntentReceiverClassName());
         Intent var4;
         (var4 = new Intent("android.intent.action.MEDIA_BUTTON")).setComponent(var3);
         PendingIntent var5 = PendingIntent.getBroadcast(this.zzarM, 0, var4, 0);
         this.zzavc = new MediaSessionCompat(this.zzarM, "CastMediaSession", var3, var5);
         this.zzavc.setFlags(3);
         this.zza(0, (MediaInfo)null);
         if (this.zzaoX != null && !TextUtils.isEmpty(this.zzaoX.getFriendlyName())) {
            this.zzavc.setMetadata((new Builder()).putString("android.media.metadata.ALBUM_ARTIST", this.zzarM.getResources().getString(string.cast_casting_to_device, new Object[]{this.zzaoX.getFriendlyName()})).build());
         }

         this.zzavd = new zzavq(this);
         this.zzavc.setCallback(this.zzavd);
         this.zzavc.setActive(true);
         this.zzasB.setMediaSessionCompat(this.zzavc);
         this.mIsAttached = true;
         this.zzoe();
      }
   }

   public final void zzab(int var1) {
      if (this.mIsAttached) {
         this.mIsAttached = false;
         if (this.zzase != null) {
            this.zzase.removeListener(this);
         }

         if (!zzq.zzse()) {
            ((AudioManager)this.zzarM.getSystemService("audio")).abandonAudioFocus((OnAudioFocusChangeListener)null);
         }

         this.zzasB.setMediaSessionCompat((MediaSessionCompat)null);
         if (this.zzava != null) {
            this.zzava.clear();
         }

         if (this.zzavb != null) {
            this.zzavb.clear();
         }

         if (this.zzavc != null) {
            this.zzavc.setSessionActivity((PendingIntent)null);
            this.zzavc.setCallback((Callback)null);
            this.zzavc.setMetadata((new Builder()).build());
            this.zza(0, (MediaInfo)null);
            this.zzavc.setActive(false);
            this.zzavc.release();
            this.zzavc = null;
         }

         this.zzase = null;
         this.zzaoX = null;
         this.zzavd = null;
         this.zzog();
         if (var1 == 0) {
            this.zzoh();
         }

      }
   }

   public final void onQueueStatusUpdated() {
      this.zzoe();
   }

   public final void onStatusUpdated() {
      this.zzoe();
   }

   public final void onMetadataUpdated() {
      this.zzoe();
   }

   public final void onPreloadStatusUpdated() {
      this.zzoe();
   }

   public final void onAdBreakStatusUpdated() {
      this.zzoe();
   }

   public final void onSendingRemoteMediaRequest() {
   }

   private final void zzoe() {
      MediaStatus var1;
      MediaInfo var10000 = (var1 = this.zzase.getMediaStatus()) == null ? null : var1.getMediaInfo();
      MediaInfo var2 = var10000;
      MediaMetadata var3 = var10000 == null ? null : var2.getMetadata();
      boolean var5 = false;
      byte var4;
      if (var1 != null && var2 != null && var3 != null) {
         switch(this.zzase.getPlayerState()) {
         case 1:
            int var6 = var1.getIdleReason();
            boolean var7 = this.zzase.isLiveStream() && var6 == 2;
            int var8;
            var5 = (var8 = var1.getLoadingItemId()) != 0 && (var6 == 1 || var6 == 3);
            if (var7) {
               var4 = 2;
            } else {
               MediaQueueItem var9;
               if ((var9 = var1.getQueueItemById(var8)) != null) {
                  var2 = var9.getMedia();
                  var4 = 6;
               } else {
                  var4 = 0;
               }
            }
            break;
         case 2:
            var4 = 3;
            break;
         case 3:
            var4 = 2;
            break;
         case 4:
            var4 = 6;
            break;
         default:
            var4 = 0;
         }
      } else {
         var4 = 0;
      }

      this.zza(var4, var2);
      if (var4 == 0) {
         this.zzog();
         this.zzoh();
      } else {
         Intent var11;
         if (this.zzauY.getCastMediaOptions().getNotificationOptions() != null && this.zzase != null) {
            (var11 = new Intent(this.zzarM, MediaNotificationService.class)).setPackage(this.zzarM.getPackageName());
            var11.setAction("com.google.android.gms.cast.framework.action.UPDATE_NOTIFICATION");
            var11.putExtra("extra_media_info", this.zzase.getMediaInfo());
            var11.putExtra("extra_remote_media_client_player_state", this.zzase.getPlayerState());
            var11.putExtra("extra_cast_device", this.zzaoX);
            zzavn var16;
            var11.putExtra("extra_media_session_token", (var16 = this).zzavc == null ? null : var16.zzavc.getSessionToken());
            MediaStatus var12;
            if ((var12 = this.zzase.getMediaStatus()) != null) {
               boolean var13 = false;
               boolean var14 = false;
               switch(var12.getQueueRepeatMode()) {
               case 1:
               case 2:
               case 3:
                  var13 = true;
                  var14 = true;
                  break;
               default:
                  Integer var15;
                  if ((var15 = var12.getIndexById(var12.getCurrentItemId())) != null) {
                     if (var15.intValue() > 0) {
                        var14 = true;
                     }

                     if (var15.intValue() < var12.getQueueItemCount() - 1) {
                        var13 = true;
                     }
                  }
               }

               var11.putExtra("extra_can_skip_next", var13);
               var11.putExtra("extra_can_skip_prev", var14);
            }

            this.zzarM.startService(var11);
         }

         if (!var5 && this.zzauY.getEnableReconnectionService()) {
            (var11 = new Intent(this.zzarM, ReconnectionService.class)).setPackage(this.zzarM.getPackageName());
            this.zzarM.startService(var11);
         }

      }
   }

   private final void zza(int var1, MediaInfo var2) {
      if (var1 == 0) {
         this.zzavc.setPlaybackState((new android.support.v4.media.session.PlaybackStateCompat.Builder()).setState(0, 0L, 1.0F).build());
         this.zzavc.setMetadata((new Builder()).build());
      } else {
         this.zzavc.setPlaybackState((new android.support.v4.media.session.PlaybackStateCompat.Builder()).setState(var1, 0L, 1.0F).setActions(512L).build());
         MediaSessionCompat var10000 = this.zzavc;
         PendingIntent var10001;
         if (this.zzauZ == null) {
            var10001 = null;
         } else {
            Intent var4;
            (var4 = new Intent()).setComponent(this.zzauZ);
            var10001 = PendingIntent.getActivity(this.zzarM, 0, var4, 134217728);
         }

         var10000.setSessionActivity(var10001);
         MediaMetadata var5 = var2.getMetadata();
         Builder var6 = this.zzof().putString("android.media.metadata.TITLE", var5.getString("com.google.android.gms.cast.metadata.TITLE")).putString("android.media.metadata.DISPLAY_TITLE", var5.getString("com.google.android.gms.cast.metadata.TITLE")).putString("android.media.metadata.DISPLAY_SUBTITLE", var5.getString("com.google.android.gms.cast.metadata.SUBTITLE")).putLong("android.media.metadata.DURATION", var2.getStreamDuration());
         this.zzavc.setMetadata(var6.build());
         Uri var7;
         if ((var7 = this.zza((MediaMetadata)var5, 0)) != null) {
            this.zzava.zzm(var7);
         } else {
            this.zza((Bitmap)null, 0);
         }

         Uri var8;
         if ((var8 = this.zza((MediaMetadata)var5, 3)) != null) {
            this.zzavb.zzm(var8);
         } else {
            this.zza((Bitmap)null, 3);
         }
      }
   }

   private final void zza(Bitmap var1, int var2) {
      if (var2 == 0) {
         if (var1 != null) {
            this.zzavc.setMetadata(this.zzof().putBitmap("android.media.metadata.DISPLAY_ICON", var1).build());
         } else {
            Bitmap var3;
            (var3 = Bitmap.createBitmap(1, 1, Config.ARGB_8888)).eraseColor(0);
            this.zzavc.setMetadata(this.zzof().putBitmap("android.media.metadata.DISPLAY_ICON", var3).build());
         }
      } else {
         if (var2 == 3) {
            this.zzavc.setMetadata(this.zzof().putBitmap("android.media.metadata.ALBUM_ART", var1).build());
         }

      }
   }

   private final Uri zza(MediaMetadata var1, int var2) {
      WebImage var3;
      return (var3 = this.zzauY.getCastMediaOptions().getImagePicker() != null ? this.zzauY.getCastMediaOptions().getImagePicker().onPickImage(var1, var2) : (var1.hasImages() ? (WebImage)var1.getImages().get(0) : null)) == null ? null : var3.getUrl();
   }

   private final Builder zzof() {
      MediaMetadataCompat var1;
      return (var1 = this.zzavc.getController().getMetadata()) == null ? new Builder() : new Builder(var1);
   }

   private final void zzog() {
      if (this.zzauY.getCastMediaOptions().getNotificationOptions() != null) {
         Intent var1;
         (var1 = new Intent(this.zzarM, MediaNotificationService.class)).setPackage(this.zzarM.getPackageName());
         var1.setAction("com.google.android.gms.cast.framework.action.UPDATE_NOTIFICATION");
         this.zzarM.stopService(var1);
      }
   }

   private final void zzoh() {
      if (this.zzauY.getEnableReconnectionService()) {
         Intent var1;
         (var1 = new Intent(this.zzarM, ReconnectionService.class)).setPackage(this.zzarM.getPackageName());
         this.zzarM.stopService(var1);
      }
   }

   // $FF: synthetic method
   static void zza(zzavn var0, Bitmap var1, int var2) {
      var0.zza(var1, var2);
   }

   // $FF: synthetic method
   static RemoteMediaClient zza(zzavn var0) {
      return var0.zzase;
   }
}
