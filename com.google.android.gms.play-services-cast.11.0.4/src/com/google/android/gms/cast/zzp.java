package com.google.android.gms.cast;

import android.support.v7.media.MediaRouter;
import android.support.v7.media.MediaRouter.Callback;
import android.support.v7.media.MediaRouter.RouteInfo;

final class zzp extends Callback {
   // $FF: synthetic field
   private CastRemoteDisplayLocalService zzapJ;

   zzp(CastRemoteDisplayLocalService var1) {
      this.zzapJ = var1;
      super();
   }

   public final void onRouteUnselected(MediaRouter var1, RouteInfo var2) {
      CastRemoteDisplayLocalService.zza(this.zzapJ, "onRouteUnselected");
      if (CastRemoteDisplayLocalService.zza(this.zzapJ) == null) {
         CastRemoteDisplayLocalService.zza(this.zzapJ, "onRouteUnselected, no device was selected");
      } else if (!CastDevice.getFromBundle(var2.getExtras()).getDeviceId().equals(CastRemoteDisplayLocalService.zza(this.zzapJ).getDeviceId())) {
         CastRemoteDisplayLocalService.zza(this.zzapJ, "onRouteUnselected, device does not match");
      } else {
         CastRemoteDisplayLocalService.stopService();
      }
   }
}
