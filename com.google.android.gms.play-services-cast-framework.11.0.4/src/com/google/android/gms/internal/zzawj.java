package com.google.android.gms.internal;

import android.view.View;
import android.view.View.OnClickListener;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;

final class zzawj implements OnClickListener {
   // $FF: synthetic field
   private zzawi zzavO;

   zzawj(zzawi var1) {
      this.zzavO = var1;
      super();
   }

   public final void onClick(View var1) {
      RemoteMediaClient var2;
      if ((var2 = zzawi.zza(this.zzavO)) != null && var2.hasMediaSession()) {
         var2.togglePlayback();
      }

   }
}
