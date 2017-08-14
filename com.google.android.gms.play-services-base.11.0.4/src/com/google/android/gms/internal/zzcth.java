package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzq;

final class zzcth extends Api.zza {
   // $FF: synthetic method
   public final Api.zze zza(Context var1, Looper var2, zzq var3, Object var4, GoogleApiClient.ConnectionCallbacks var5, GoogleApiClient.OnConnectionFailedListener var6) {
      zzctl var10 = (zzctl)var4;
      if (var10 == null) {
         var10 = zzctl.zzbCM;
      }

      return new zzctu(var1, var2, true, var3, var10, var5, var6);
   }
}
