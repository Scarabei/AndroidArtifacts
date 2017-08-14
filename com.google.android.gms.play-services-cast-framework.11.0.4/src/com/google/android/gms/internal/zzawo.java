package com.google.android.gms.internal;

import android.view.View;
import android.view.View.OnClickListener;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;

final class zzawo implements OnClickListener {
   // $FF: synthetic field
   private long zzavU;
   // $FF: synthetic field
   private zzawn zzavV;

   zzawo(zzawn var1, long var2) {
      this.zzavV = var1;
      this.zzavU = var2;
      super();
   }

   public final void onClick(View var1) {
      RemoteMediaClient var2;
      if ((var2 = zzawn.zza(this.zzavV)) != null && var2.hasMediaSession()) {
         var2.seek(var2.getApproximateStreamPosition() + this.zzavU);
      }

   }
}
