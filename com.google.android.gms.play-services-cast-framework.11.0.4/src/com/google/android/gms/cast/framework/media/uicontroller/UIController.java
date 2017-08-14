package com.google.android.gms.cast.framework.media.uicontroller;

import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;

public class UIController {
   private RemoteMediaClient zzase;

   protected RemoteMediaClient getRemoteMediaClient() {
      return this.zzase;
   }

   public void onSessionConnected(CastSession var1) {
      if (var1 != null) {
         this.zzase = var1.getRemoteMediaClient();
      } else {
         this.zzase = null;
      }
   }

   public void onSessionEnded() {
      this.zzase = null;
   }

   public void onMediaStatusUpdated() {
   }

   public void onSendingRemoteMediaRequest() {
   }
}
