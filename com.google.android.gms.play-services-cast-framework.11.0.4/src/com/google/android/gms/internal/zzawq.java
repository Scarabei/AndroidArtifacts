package com.google.android.gms.internal;

import android.view.View;
import android.view.View.OnClickListener;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import org.json.JSONObject;

final class zzawq implements OnClickListener {
   // $FF: synthetic field
   private zzawp zzavX;

   zzawq(zzawp var1) {
      this.zzavX = var1;
      super();
   }

   public final void onClick(View var1) {
      RemoteMediaClient var2;
      if ((var2 = zzawp.zza(this.zzavX)) != null && var2.hasMediaSession()) {
         var2.queueNext((JSONObject)null);
      }

   }
}
