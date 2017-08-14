package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzaa implements zzf {
   // $FF: synthetic field
   private GoogleApiClient.ConnectionCallbacks zzaHC;

   zzaa(GoogleApiClient.ConnectionCallbacks var1) {
      this.zzaHC = var1;
      super();
   }

   public final void onConnected(@Nullable Bundle var1) {
      this.zzaHC.onConnected(var1);
   }

   public final void onConnectionSuspended(int var1) {
      this.zzaHC.onConnectionSuspended(var1);
   }
}
