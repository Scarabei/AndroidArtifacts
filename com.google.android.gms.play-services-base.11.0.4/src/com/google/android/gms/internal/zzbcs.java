package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

final class zzbcs implements GoogleApiClient.OnConnectionFailedListener {
   // $FF: synthetic field
   private zzben zzaDP;

   zzbcs(zzbcp var1, zzben var2) {
      this.zzaDP = var2;
      super();
   }

   public final void onConnectionFailed(@NonNull ConnectionResult var1) {
      this.zzaDP.setResult(new Status(8));
   }
}
