package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.Channel;
import com.google.android.gms.wearable.ChannelApi;

final class zzaf implements ChannelApi.OpenChannelResult {
   private final Status mStatus;
   private final Channel zzbSf;

   zzaf(Status var1, Channel var2) {
      this.mStatus = (Status)com.google.android.gms.common.internal.zzbo.zzu(var1);
      this.zzbSf = var2;
   }

   public final Status getStatus() {
      return this.mStatus;
   }

   public final Channel getChannel() {
      return this.zzbSf;
   }
}
