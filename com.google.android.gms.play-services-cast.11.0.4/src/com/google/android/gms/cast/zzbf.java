package com.google.android.gms.cast;

import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

final class zzbf implements ResultCallback {
   private final long zzaru;
   // $FF: synthetic field
   private RemoteMediaPlayer.zza zzarv;

   zzbf(RemoteMediaPlayer.zza var1, long var2) {
      this.zzarv = var1;
      super();
      this.zzaru = var2;
   }

   // $FF: synthetic method
   public final void onResult(@NonNull Result var1) {
      Status var3 = (Status)var1;
      if (!var3.isSuccess()) {
         RemoteMediaPlayer.zzg(this.zzarv.zzaqV).zzc(this.zzaru, var3.getStatusCode());
      }

   }
}
