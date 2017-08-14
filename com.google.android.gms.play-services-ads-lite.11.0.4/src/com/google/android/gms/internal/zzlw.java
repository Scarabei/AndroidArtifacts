package com.google.android.gms.internal;

import com.google.android.gms.ads.VideoController;

public final class zzlw extends zzkw {
   private final VideoController.VideoLifecycleCallbacks zzse;

   public zzlw(VideoController.VideoLifecycleCallbacks var1) {
      this.zzse = var1;
   }

   public final void onVideoStart() {
      this.zzse.onVideoStart();
   }

   public final void onVideoPlay() {
      this.zzse.onVideoPlay();
   }

   public final void onVideoPause() {
      this.zzse.onVideoPause();
   }

   public final void onVideoEnd() {
      this.zzse.onVideoEnd();
   }

   public final void onVideoMute(boolean var1) {
      this.zzse.onVideoMute(var1);
   }
}
