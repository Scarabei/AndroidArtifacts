package com.google.android.gms.wallet;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.internal.gu;

final class zzaa extends com.google.android.gms.common.api.Api.zza {
   // $FF: synthetic method
   public final com.google.android.gms.common.api.Api.zze zza(Context var1, Looper var2, com.google.android.gms.common.internal.zzq var3, Object var4, ConnectionCallbacks var5, OnConnectionFailedListener var6) {
      Wallet.WalletOptions var10 = (Wallet.WalletOptions)var4;
      Wallet.WalletOptions var13 = var10 != null ? var10 : new Wallet.WalletOptions((zzaa)null);
      return new gu(var1, var2, var3, var5, var6, var13.environment, var13.theme, var13.zzbPS);
   }
}
