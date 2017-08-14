package com.google.android.gms.internal;

import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.auth.api.proxy.ProxyResponse;
import com.google.android.gms.common.api.Status;

final class zzasq implements ProxyApi.ProxyResult {
   private Status mStatus;
   private ProxyResponse zzalM;

   public zzasq(ProxyResponse var1) {
      this.zzalM = var1;
      this.mStatus = Status.zzaBm;
   }

   public zzasq(Status var1) {
      this.mStatus = var1;
   }

   public final Status getStatus() {
      return this.mStatus;
   }

   public final ProxyResponse getResponse() {
      return this.zzalM;
   }
}
