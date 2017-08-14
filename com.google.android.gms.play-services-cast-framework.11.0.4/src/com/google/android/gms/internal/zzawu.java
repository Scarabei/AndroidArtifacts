package com.google.android.gms.internal;

import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

public final class zzawu extends UIController implements RemoteMediaClient.ProgressListener {
   private final TextView zzawa;
   private final String zzawb;
   private final View zzawc;

   public zzawu(TextView var1, String var2, View var3) {
      this.zzawa = var1;
      this.zzawb = var2;
      this.zzawc = var3;
   }

   public final void onSessionConnected(CastSession var1) {
      super.onSessionConnected(var1);
      if (this.getRemoteMediaClient() != null) {
         this.getRemoteMediaClient().addProgressListener(this, 1000L);
      }

      this.zza(-1L, true);
   }

   public final void onSessionEnded() {
      this.zzawa.setText(this.zzawb);
      if (this.getRemoteMediaClient() != null) {
         this.getRemoteMediaClient().removeProgressListener(this);
      }

      super.onSessionEnded();
   }

   public final void onMediaStatusUpdated() {
      this.zza(-1L, true);
   }

   public final void onProgressUpdated(long var1, long var3) {
      this.zza(var3, false);
   }

   private final void zza(long var1, boolean var3) {
      RemoteMediaClient var4;
      if ((var4 = this.getRemoteMediaClient()) != null && var4.hasMediaSession()) {
         if (!var4.isLiveStream()) {
            long var5 = var3 ? var4.getStreamDuration() : var1;
            this.zzawa.setVisibility(0);
            this.zzawa.setText(DateUtils.formatElapsedTime(var5 / 1000L));
            if (this.zzawc != null) {
               this.zzawc.setVisibility(4);
            }

            return;
         }

         this.zzawa.setText(this.zzawb);
         if (this.zzawc != null) {
            this.zzawa.setVisibility(4);
            this.zzawc.setVisibility(0);
            return;
         }
      } else {
         this.zzawa.setVisibility(0);
         this.zzawa.setText(this.zzawb);
         if (this.zzawc != null) {
            this.zzawc.setVisibility(4);
         }
      }

   }
}
