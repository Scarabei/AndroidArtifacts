package com.google.android.gms.ads;

import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzajc;
import com.google.android.gms.internal.zzks;
import com.google.android.gms.internal.zzlw;
import com.google.android.gms.internal.zzzn;

@zzzn
public final class VideoController {
   @KeepForSdk
   public static final int PLAYBACK_STATE_UNKNOWN = 0;
   @KeepForSdk
   public static final int PLAYBACK_STATE_PLAYING = 1;
   @KeepForSdk
   public static final int PLAYBACK_STATE_PAUSED = 2;
   @KeepForSdk
   public static final int PLAYBACK_STATE_ENDED = 3;
   @KeepForSdk
   public static final int PLAYBACK_STATE_READY = 5;
   private final Object mLock = new Object();
   @Nullable
   private zzks zzsd;
   @Nullable
   private VideoController.VideoLifecycleCallbacks zzse;

   public final void zza(zzks var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzsd = var1;
         if (this.zzse != null) {
            this.setVideoLifecycleCallbacks(this.zzse);
         }

      }
   }

   public final zzks zzae() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzsd;
      }
   }

   @KeepForSdk
   public final void play() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzsd != null) {
            try {
               this.zzsd.play();
            } catch (RemoteException var4) {
               zzajc.zzb("Unable to call play on video controller.", var4);
            }

         }
      }
   }

   @KeepForSdk
   public final void pause() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzsd != null) {
            try {
               this.zzsd.pause();
            } catch (RemoteException var4) {
               zzajc.zzb("Unable to call pause on video controller.", var4);
            }

         }
      }
   }

   @KeepForSdk
   public final void mute(boolean var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzsd != null) {
            try {
               this.zzsd.mute(var1);
            } catch (RemoteException var5) {
               zzajc.zzb("Unable to call mute on video controller.", var5);
            }

         }
      }
   }

   @KeepForSdk
   public final boolean isMuted() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzsd == null) {
            return true;
         } else {
            boolean var10000;
            try {
               var10000 = this.zzsd.isMuted();
            } catch (RemoteException var4) {
               zzajc.zzb("Unable to call isMuted on video controller.", var4);
               return true;
            }

            return var10000;
         }
      }
   }

   @KeepForSdk
   public final int getPlaybackState() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzsd == null) {
            return 0;
         } else {
            int var10000;
            try {
               var10000 = this.zzsd.getPlaybackState();
            } catch (RemoteException var4) {
               zzajc.zzb("Unable to call getPlaybackState on video controller.", var4);
               return 0;
            }

            return var10000;
         }
      }
   }

   @KeepForSdk
   public final boolean isCustomControlsEnabled() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzsd == null) {
            return false;
         } else {
            boolean var10000;
            try {
               var10000 = this.zzsd.isCustomControlsEnabled();
            } catch (RemoteException var4) {
               zzajc.zzb("Unable to call isUsingCustomPlayerControls.", var4);
               return false;
            }

            return var10000;
         }
      }
   }

   public final void setVideoLifecycleCallbacks(VideoController.VideoLifecycleCallbacks var1) {
      zzbo.zzb(var1, "VideoLifecycleCallbacks may not be null.");
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzse = var1;
         if (this.zzsd != null) {
            try {
               this.zzsd.zza(new zzlw(var1));
            } catch (RemoteException var5) {
               zzajc.zzb("Unable to call setVideoLifecycleCallbacks on video controller.", var5);
            }

         }
      }
   }

   @Nullable
   public final VideoController.VideoLifecycleCallbacks getVideoLifecycleCallbacks() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzse;
      }
   }

   public final boolean hasVideoContent() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzsd != null;
      }
   }

   public final float getAspectRatio() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzsd == null) {
            return 0.0F;
         } else {
            float var10000;
            try {
               var10000 = this.zzsd.getAspectRatio();
            } catch (RemoteException var4) {
               zzajc.zzb("Unable to call getAspectRatio on video controller.", var4);
               return 0.0F;
            }

            return var10000;
         }
      }
   }

   public abstract static class VideoLifecycleCallbacks {
      public void onVideoStart() {
      }

      public void onVideoPlay() {
      }

      public void onVideoPause() {
      }

      public void onVideoEnd() {
      }

      public void onVideoMute(boolean var1) {
      }
   }
}
