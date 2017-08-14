package com.google.android.gms.auth.api;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.Nullable;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.internal.zzd;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzq;
import java.util.Collections;
import java.util.List;

final class zzc extends com.google.android.gms.common.api.Api.zza {
   // $FF: synthetic method
   public final zze zza(Context var1, Looper var2, zzq var3, @Nullable Object var4, ConnectionCallbacks var5, OnConnectionFailedListener var6) {
      GoogleSignInOptions var10 = (GoogleSignInOptions)var4;
      return new zzd(var1, var2, var3, var10, var5, var6);
   }

   // $FF: synthetic method
   public final List zzn(@Nullable Object var1) {
      GoogleSignInOptions var2;
      return (List)((var2 = (GoogleSignInOptions)var1) == null ? Collections.emptyList() : var2.zzmA());
   }
}
