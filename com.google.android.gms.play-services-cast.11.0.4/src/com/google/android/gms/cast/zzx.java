package com.google.android.gms.cast;

import android.view.Display;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;

final class zzx implements ResultCallback {
   // $FF: synthetic field
   private CastRemoteDisplayLocalService zzapJ;

   zzx(CastRemoteDisplayLocalService var1) {
      this.zzapJ = var1;
      super();
   }

   // $FF: synthetic method
   public final void onResult(Result var1) {
      CastRemoteDisplay.CastRemoteDisplaySessionResult var3 = (CastRemoteDisplay.CastRemoteDisplaySessionResult)var1;
      if (!var3.getStatus().isSuccess()) {
         CastRemoteDisplayLocalService.zza(this.zzapJ, "Unable to stop the remote display, result unsuccessful");
      } else {
         CastRemoteDisplayLocalService.zza(this.zzapJ, "remote display stopped");
      }

      CastRemoteDisplayLocalService.zzb(this.zzapJ, (Display)null);
   }
}
