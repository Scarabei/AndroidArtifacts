package com.google.android.gms.internal;

import android.app.PendingIntent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.location.GeofencingApi;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.zzaa;
import java.util.List;

public final class zzccq implements GeofencingApi {
   /** @deprecated */
   @Deprecated
   public final PendingResult addGeofences(GoogleApiClient var1, List var2, PendingIntent var3) {
      GeofencingRequest.Builder var4;
      (var4 = new GeofencingRequest.Builder()).addGeofences(var2);
      var4.setInitialTrigger(5);
      return this.addGeofences(var1, var4.build(), var3);
   }

   public final PendingResult addGeofences(GoogleApiClient var1, GeofencingRequest var2, PendingIntent var3) {
      return var1.zze(new zzccr(this, var1, var2, var3));
   }

   private final PendingResult zza(GoogleApiClient var1, zzaa var2) {
      return var1.zze(new zzccs(this, var1, var2));
   }

   public final PendingResult removeGeofences(GoogleApiClient var1, PendingIntent var2) {
      zzaa var3 = zzaa.zzb(var2);
      return this.zza(var1, var3);
   }

   public final PendingResult removeGeofences(GoogleApiClient var1, List var2) {
      zzaa var3 = zzaa.zzB(var2);
      return this.zza(var1, var3);
   }
}
