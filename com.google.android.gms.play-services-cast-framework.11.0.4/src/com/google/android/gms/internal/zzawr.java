package com.google.android.gms.internal;

import android.view.View;
import android.view.View.OnClickListener;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

public final class zzawr extends UIController {
   private final View mView;
   private final int zzavW;
   private final OnClickListener zzavp;

   public zzawr(View var1, int var2) {
      this.mView = var1;
      this.zzavW = var2;
      this.zzavp = new zzaws(this);
   }

   public final void onSessionConnected(CastSession var1) {
      super.onSessionConnected(var1);
      this.mView.setOnClickListener(this.zzavp);
      this.zzoo();
   }

   public final void onSessionEnded() {
      this.mView.setOnClickListener((OnClickListener)null);
      super.onSessionEnded();
   }

   public final void onMediaStatusUpdated() {
      this.zzoo();
   }

   public final void onSendingRemoteMediaRequest() {
      this.mView.setEnabled(false);
   }

   private final void zzoo() {
      RemoteMediaClient var1;
      if ((var1 = this.getRemoteMediaClient()) != null && var1.hasMediaSession()) {
         boolean var2 = true;
         MediaStatus var3;
         if ((var3 = var1.getMediaStatus()).getQueueRepeatMode() == 0) {
            Integer var4;
            var2 = (var4 = var3.getIndexById(var3.getCurrentItemId())) != null && var4.intValue() > 0;
         }

         if (var2 && !var1.isPlayingAd()) {
            this.mView.setVisibility(0);
            this.mView.setClickable(true);
            this.mView.setEnabled(true);
         } else {
            this.mView.setVisibility(this.zzavW);
            this.mView.setClickable(false);
            this.mView.setEnabled(false);
         }
      }
   }

   // $FF: synthetic method
   static RemoteMediaClient zza(zzawr var0) {
      return var0.getRemoteMediaClient();
   }
}
