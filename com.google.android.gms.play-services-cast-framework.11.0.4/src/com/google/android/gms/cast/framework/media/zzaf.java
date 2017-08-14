package com.google.android.gms.cast.framework.media;

import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

final class zzaf implements ResultCallback {
   private final long zzaru;
   // $FF: synthetic field
   private RemoteMediaClient.zza zzauz;

   zzaf(RemoteMediaClient.zza var1, long var2) {
      this.zzauz = var1;
      super();
      this.zzaru = var2;
   }

   // $FF: synthetic method
   public final void onResult(@NonNull Result var1) {
      Status var3 = (Status)var1;
      if (!var3.isSuccess()) {
         RemoteMediaClient.zze(this.zzauz.zzauy).zzc(this.zzaru, var3.getStatusCode());
      }

   }
}
