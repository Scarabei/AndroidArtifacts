package com.google.android.gms.internal;

import android.view.View;
import android.view.View.OnClickListener;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

public final class zzawn extends UIController {
   private final View mView;
   private final OnClickListener zzavp;

   public zzawn(View var1, long var2) {
      this.mView = var1;
      this.zzavp = new zzawo(this, var2);
   }

   public final void onSessionConnected(CastSession var1) {
      super.onSessionConnected(var1);
      this.mView.setOnClickListener(this.zzavp);
      this.zzok();
   }

   public final void onSessionEnded() {
      this.mView.setOnClickListener((OnClickListener)null);
      super.onSessionEnded();
   }

   public final void onMediaStatusUpdated() {
      this.zzok();
   }

   public final void onSendingRemoteMediaRequest() {
      this.mView.setEnabled(false);
   }

   private final void zzok() {
      RemoteMediaClient var1;
      if ((var1 = this.getRemoteMediaClient()) != null && var1.hasMediaSession() && !var1.isLiveStream() && !var1.isPlayingAd()) {
         this.mView.setEnabled(true);
      } else {
         this.mView.setEnabled(false);
      }
   }

   // $FF: synthetic method
   static RemoteMediaClient zza(zzawn var0) {
      return var0.getRemoteMediaClient();
   }
}
