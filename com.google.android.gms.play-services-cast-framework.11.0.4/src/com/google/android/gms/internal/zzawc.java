package com.google.android.gms.internal;

import android.view.View;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

public final class zzawc extends UIController {
   private final View mView;

   public zzawc(View var1) {
      this.mView = var1;
   }

   public final void onSessionConnected(CastSession var1) {
      super.onSessionConnected(var1);
      this.zzok();
   }

   public final void onSessionEnded() {
      this.mView.setVisibility(8);
      super.onSessionEnded();
   }

   public final void onMediaStatusUpdated() {
      this.zzok();
   }

   public final void onSendingRemoteMediaRequest() {
      this.mView.setVisibility(0);
   }

   private final void zzok() {
      RemoteMediaClient var1;
      if ((var1 = this.getRemoteMediaClient()) != null && var1.hasMediaSession() && var1.isBuffering()) {
         this.mView.setVisibility(0);
      } else {
         this.mView.setVisibility(8);
      }
   }
}
