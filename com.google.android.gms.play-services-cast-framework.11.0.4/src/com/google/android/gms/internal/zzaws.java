package com.google.android.gms.internal;

import android.view.View;
import android.view.View.OnClickListener;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import org.json.JSONObject;

final class zzaws implements OnClickListener {
   // $FF: synthetic field
   private zzawr zzavY;

   zzaws(zzawr var1) {
      this.zzavY = var1;
      super();
   }

   public final void onClick(View var1) {
      RemoteMediaClient var2;
      if ((var2 = zzawr.zza(this.zzavY)) != null && var2.hasMediaSession()) {
         var2.queuePrev((JSONObject)null);
      }

   }
}
