package com.google.android.gms.cast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

final class zzq implements OnConnectionFailedListener {
   // $FF: synthetic field
   private CastRemoteDisplayLocalService zzapJ;

   zzq(CastRemoteDisplayLocalService var1) {
      this.zzapJ = var1;
      super();
   }

   public final void onConnectionFailed(ConnectionResult var1) {
      CastRemoteDisplayLocalService var10000 = this.zzapJ;
      String var2 = String.valueOf(var1);
      CastRemoteDisplayLocalService.zzb(var10000, (new StringBuilder(19 + String.valueOf(var2).length())).append("Connection failed: ").append(var2).toString());
      CastRemoteDisplayLocalService.zzc(this.zzapJ);
   }
}
