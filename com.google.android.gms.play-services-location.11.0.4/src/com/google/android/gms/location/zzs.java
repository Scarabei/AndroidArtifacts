package com.google.android.gms.location;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.internal.zzcdj;

final class zzs extends com.google.android.gms.common.api.Api.zza {
   // $FF: synthetic method
   public final com.google.android.gms.common.api.Api.zze zza(Context var1, Looper var2, com.google.android.gms.common.internal.zzq var3, Object var4, ConnectionCallbacks var5, OnConnectionFailedListener var6) {
      return new zzcdj(var1, var2, var5, var6, "locationServices", var3);
   }
}
