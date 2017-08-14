package com.google.android.gms.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzbo;

public final class zzbbi implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
   public final Api zzayW;
   private final boolean zzaCj;
   private zzbbj zzaCk;

   public zzbbi(Api var1, boolean var2) {
      this.zzayW = var1;
      this.zzaCj = var2;
   }

   public final void onConnected(@Nullable Bundle var1) {
      this.zzpD();
      this.zzaCk.onConnected(var1);
   }

   public final void onConnectionSuspended(int var1) {
      this.zzpD();
      this.zzaCk.onConnectionSuspended(var1);
   }

   public final void onConnectionFailed(@NonNull ConnectionResult var1) {
      this.zzpD();
      this.zzaCk.zza(var1, this.zzayW, this.zzaCj);
   }

   public final void zza(zzbbj var1) {
      this.zzaCk = var1;
   }

   private final void zzpD() {
      zzbo.zzb(this.zzaCk, "Callbacks must be attached to a ClientConnectionHelper instance before connecting the client.");
   }
}
