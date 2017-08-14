package com.google.android.gms.cast;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzaxx;

final class zze extends com.google.android.gms.common.api.Api.zza {
   // $FF: synthetic method
   public final com.google.android.gms.common.api.Api.zze zza(Context var1, Looper var2, com.google.android.gms.common.internal.zzq var3, Object var4, ConnectionCallbacks var5, OnConnectionFailedListener var6) {
      Cast.CastOptions var10 = (Cast.CastOptions)var4;
      zzbo.zzb(var10, "Setting the API options is required.");
      return new zzaxx(var1, var2, var3, var10.zzaoU, (long)Cast.CastOptions.zza(var10), var10.zzaoV, var10.extras, var5, var6);
   }
}
