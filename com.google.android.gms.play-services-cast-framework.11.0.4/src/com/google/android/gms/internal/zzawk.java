package com.google.android.gms.internal;

import android.widget.ProgressBar;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

public final class zzawk extends UIController implements RemoteMediaClient.ProgressListener {
   private final ProgressBar zzavP;
   private final long zzavQ;

   public zzawk(ProgressBar var1, long var2) {
      this.zzavP = var1;
      this.zzavQ = var2;
   }

   public final void onSessionConnected(CastSession var1) {
      super.onSessionConnected(var1);
      RemoteMediaClient var2;
      if ((var2 = this.getRemoteMediaClient()) != null) {
         var2.addProgressListener(this, this.zzavQ);
         if (var2.hasMediaSession()) {
            this.zzavP.setMax((int)var2.getStreamDuration());
            this.zzavP.setProgress((int)var2.getApproximateStreamPosition());
            return;
         }

         this.zzavP.setMax(1);
         this.zzavP.setProgress(0);
      }

   }

   public final void onSessionEnded() {
      if (this.getRemoteMediaClient() != null) {
         this.getRemoteMediaClient().removeProgressListener(this);
      }

      this.zzavP.setMax(1);
      this.zzavP.setProgress(0);
      super.onSessionEnded();
   }

   public final void onMediaStatusUpdated() {
      RemoteMediaClient var1;
      if ((var1 = this.getRemoteMediaClient()) == null || !var1.hasMediaSession()) {
         this.zzavP.setMax(1);
         this.zzavP.setProgress(0);
      }

   }

   public final void onProgressUpdated(long var1, long var3) {
      this.zzavP.setMax((int)var3);
      this.zzavP.setProgress((int)var1);
   }
}
