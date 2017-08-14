package com.google.android.gms.cast.framework.media;

import com.google.android.gms.common.api.Status;
import org.json.JSONObject;

final class zzah implements RemoteMediaClient.MediaChannelResult {
   // $FF: synthetic field
   private Status zzakB;

   zzah(RemoteMediaClient.zzb var1, Status var2) {
      this.zzakB = var2;
      super();
   }

   public final Status getStatus() {
      return this.zzakB;
   }

   public final JSONObject getCustomData() {
      return null;
   }
}
