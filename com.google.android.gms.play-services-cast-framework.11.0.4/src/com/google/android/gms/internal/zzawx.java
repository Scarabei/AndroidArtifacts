package com.google.android.gms.internal;

import android.view.View;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

public final class zzawx extends UIController {
   private final View mView;
   private final int zzavW;

   public zzawx(View var1, int var2) {
      this.mView = var1;
      this.zzavW = var2;
   }

   public final void onSessionConnected(CastSession var1) {
      super.onSessionConnected(var1);
      this.zzok();
   }

   public final void onSessionEnded() {
      this.mView.setVisibility(this.zzavW);
      super.onSessionEnded();
   }

   public final void onMediaStatusUpdated() {
      this.zzok();
   }

   private final void zzok() {
      RemoteMediaClient var1;
      if ((var1 = this.getRemoteMediaClient()) != null && var1.hasMediaSession()) {
         this.mView.setVisibility(0);
      } else {
         this.mView.setVisibility(this.zzavW);
      }
   }
}
