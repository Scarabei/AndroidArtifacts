package com.google.android.gms.cast;

import com.google.android.gms.common.api.Status;

final class zzv implements CastRemoteDisplay.CastRemoteDisplaySessionCallbacks {
   zzv(CastRemoteDisplayLocalService var1) {
   }

   public final void onRemoteDisplayEnded(Status var1) {
      CastRemoteDisplayLocalService.zznf().zzb(String.format("Cast screen has ended: %d", var1.getStatusCode()));
      CastRemoteDisplayLocalService.zzT(false);
   }
}
