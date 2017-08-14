package com.google.android.gms.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzbcm implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
   // $FF: synthetic field
   private zzbcd zzaDp;

   private zzbcm(zzbcd var1) {
      this.zzaDp = var1;
      super();
   }

   public final void onConnected(Bundle var1) {
      zzbcd.zzf(this.zzaDp).zza(new zzbck(this.zzaDp));
   }

   public final void onConnectionSuspended(int var1) {
   }

   public final void onConnectionFailed(@NonNull ConnectionResult var1) {
      zzbcd.zzc(this.zzaDp).lock();

      try {
         if (zzbcd.zzb(this.zzaDp, var1)) {
            zzbcd.zzi(this.zzaDp);
            zzbcd.zzj(this.zzaDp);
         } else {
            zzbcd.zza(this.zzaDp, var1);
         }
      } finally {
         zzbcd.zzc(this.zzaDp).unlock();
      }

   }

   // $FF: synthetic method
   zzbcm(zzbcd var1, zzbce var2) {
      this(var1);
   }
}
