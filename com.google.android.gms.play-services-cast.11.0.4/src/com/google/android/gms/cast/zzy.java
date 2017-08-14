package com.google.android.gms.cast;

import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;

final class zzy implements ConnectionCallbacks {
   // $FF: synthetic field
   private CastRemoteDisplayLocalService zzapJ;

   zzy(CastRemoteDisplayLocalService var1) {
      this.zzapJ = var1;
      super();
   }

   public final void onConnected(Bundle var1) {
      CastRemoteDisplayLocalService.zza(this.zzapJ, "onConnected");
      CastRemoteDisplayLocalService.zzf(this.zzapJ);
   }

   public final void onConnectionSuspended(int var1) {
      CastRemoteDisplayLocalService.zznf().zzf(String.format("[Instance: %s] ConnectionSuspended %d", this, var1));
   }
}
