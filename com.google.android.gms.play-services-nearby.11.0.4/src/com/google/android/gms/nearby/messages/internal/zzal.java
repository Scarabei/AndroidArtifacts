package com.google.android.gms.nearby.messages.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.nearby.messages.MessagesOptions;

final class zzal extends com.google.android.gms.common.api.Api.zza {
   public final int getPriority() {
      return Integer.MAX_VALUE;
   }

   // $FF: synthetic method
   public final com.google.android.gms.common.api.Api.zze zza(Context var1, Looper var2, com.google.android.gms.common.internal.zzq var3, Object var4, ConnectionCallbacks var5, OnConnectionFailedListener var6) {
      MessagesOptions var10 = (MessagesOptions)var4;
      return new zzah(var1, var2, var5, var6, var3, var10);
   }
}
