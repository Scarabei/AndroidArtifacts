package com.google.android.gms.internal;

import android.text.format.DateUtils;
import android.widget.TextView;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

public final class zzawv extends UIController implements RemoteMediaClient.ProgressListener {
   private final TextView zzawa;
   private final long zzavQ;
   private final String zzawd;
   private boolean mIsAttached = true;

   public zzawv(TextView var1, long var2, String var4) {
      this.zzawa = var1;
      this.zzavQ = var2;
      this.zzawd = var4;
   }

   public final void onSessionConnected(CastSession var1) {
      super.onSessionConnected(var1);
      RemoteMediaClient var2;
      if ((var2 = this.getRemoteMediaClient()) != null) {
         var2.addProgressListener(this, this.zzavQ);
         if (var2.hasMediaSession()) {
            this.zzawa.setText(DateUtils.formatElapsedTime(var2.getApproximateStreamPosition() / 1000L));
            return;
         }

         this.zzawa.setText(this.zzawd);
      }

   }

   public final void onSessionEnded() {
      this.zzawa.setText(this.zzawd);
      if (this.getRemoteMediaClient() != null) {
         this.getRemoteMediaClient().removeProgressListener(this);
      }

      super.onSessionEnded();
   }

   public final void onProgressUpdated(long var1, long var3) {
      if (this.mIsAttached) {
         this.zzawa.setText(DateUtils.formatElapsedTime(var1 / 1000L));
      }

   }

   public final void zzy(long var1) {
      this.zzawa.setText(DateUtils.formatElapsedTime(var1 / 1000L));
   }

   public final void zzY(boolean var1) {
      this.mIsAttached = var1;
   }
}
