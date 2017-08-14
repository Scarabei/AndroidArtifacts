package com.google.android.gms.wearable;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.wearable.internal.zzfw;

final class zzj extends com.google.android.gms.common.api.Api.zza {
   // $FF: synthetic method
   public final com.google.android.gms.common.api.Api.zze zza(Context var1, Looper var2, com.google.android.gms.common.internal.zzq var3, Object var4, ConnectionCallbacks var5, OnConnectionFailedListener var6) {
      Wearable.WearableOptions var10 = (Wearable.WearableOptions)var4;
      if (var10 == null) {
         new Wearable.WearableOptions(new Wearable.WearableOptions.Builder(), (zzj)null);
      }

      return new zzfw(var1, var2, var5, var6, var3);
   }
}
