package com.google.android.gms.cast;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.internal.zzazf;

final class zzo extends com.google.android.gms.common.api.Api.zza {
   // $FF: synthetic method
   public final com.google.android.gms.common.api.Api.zze zza(Context var1, Looper var2, com.google.android.gms.common.internal.zzq var3, Object var4, ConnectionCallbacks var5, OnConnectionFailedListener var6) {
      CastRemoteDisplay.CastRemoteDisplayOptions var10 = (CastRemoteDisplay.CastRemoteDisplayOptions)var4;
      Bundle var13;
      (var13 = new Bundle()).putInt("configuration", var10.zzapn);
      return new zzazf(var1, var2, var3, var10.zzaoU, var13, var10.zzapm, var5, var6);
   }
}
