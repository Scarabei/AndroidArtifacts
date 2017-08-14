package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

final class zzaem implements OnConnectionFailedListener {
   // $FF: synthetic field
   private zzajg zzXk;

   zzaem(zzael var1, zzajg var2) {
      this.zzXk = var2;
      super();
   }

   public final void onConnectionFailed(@NonNull ConnectionResult var1) {
      zzaes.zzaC("Failed to connect to SafetyNet API");
      this.zzXk.zzg((Object)null);
   }
}
