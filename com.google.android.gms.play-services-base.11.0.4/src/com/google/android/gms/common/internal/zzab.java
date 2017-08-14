package com.google.android.gms.common.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzab implements zzg {
   // $FF: synthetic field
   private GoogleApiClient.OnConnectionFailedListener zzaHD;

   zzab(GoogleApiClient.OnConnectionFailedListener var1) {
      this.zzaHD = var1;
      super();
   }

   public final void onConnectionFailed(@NonNull ConnectionResult var1) {
      this.zzaHD.onConnectionFailed(var1);
   }
}
