package com.google.android.gms.identity.intents;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.internal.zzcbe;

final class zza extends com.google.android.gms.common.api.Api.zza {
   // $FF: synthetic method
   public final zze zza(Context var1, Looper var2, zzq var3, Object var4, ConnectionCallbacks var5, OnConnectionFailedListener var6) {
      Address.AddressOptions var10 = (Address.AddressOptions)var4;
      zzbo.zzb(var1 instanceof Activity, "An Activity must be used for Address APIs");
      if (var10 == null) {
         var10 = new Address.AddressOptions();
      }

      return new zzcbe((Activity)var1, var2, var3, var10.theme, var5, var6);
   }
}
