package com.google.android.gms.internal;

import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.auth.api.proxy.ProxyRequest;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.zzbo;

public final class zzasn implements ProxyApi {
   public final PendingResult performProxyRequest(GoogleApiClient var1, ProxyRequest var2) {
      zzbo.zzu(var1);
      zzbo.zzu(var2);
      return var1.zze(new zzaso(this, var1, var2));
   }
}
