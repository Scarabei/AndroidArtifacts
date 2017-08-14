package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.concurrent.atomic.AtomicReference;

final class zzbcr implements GoogleApiClient.ConnectionCallbacks {
   // $FF: synthetic field
   private AtomicReference zzaDO;
   // $FF: synthetic field
   private zzben zzaDP;
   // $FF: synthetic field
   private zzbcp zzaDN;

   zzbcr(zzbcp var1, AtomicReference var2, zzben var3) {
      this.zzaDN = var1;
      this.zzaDO = var2;
      this.zzaDP = var3;
      super();
   }

   public final void onConnected(Bundle var1) {
      zzbcp.zza(this.zzaDN, (GoogleApiClient)this.zzaDO.get(), this.zzaDP, true);
   }

   public final void onConnectionSuspended(int var1) {
   }
}
