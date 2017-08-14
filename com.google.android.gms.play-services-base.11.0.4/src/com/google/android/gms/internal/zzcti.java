package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzq;

final class zzcti extends Api.zza {
   // $FF: synthetic method
   public final Api.zze zza(Context var1, Looper var2, zzq var3, Object var4, GoogleApiClient.ConnectionCallbacks var5, GoogleApiClient.OnConnectionFailedListener var6) {
      zzctj var10 = (zzctj)var4;
      return new zzctu(var1, var2, false, var3, var10.zzAp(), var5, var6);
   }
}
