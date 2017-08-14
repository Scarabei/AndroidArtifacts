package com.google.android.gms.location.places.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.places.PlacesOptions;

public final class zzo extends com.google.android.gms.common.api.Api.zza {
   // $FF: synthetic method
   public final com.google.android.gms.common.api.Api.zze zza(Context var1, Looper var2, com.google.android.gms.common.internal.zzq var3, Object var4, ConnectionCallbacks var5, OnConnectionFailedListener var6) {
      PlacesOptions var10 = (PlacesOptions)var4;
      if (var10 == null) {
         var10 = (new PlacesOptions.Builder()).build();
      }

      String var13 = var1.getPackageName();
      return new zzm(var1, var2, var3, var5, var6, var13, var10, (zzn)null);
   }
}
