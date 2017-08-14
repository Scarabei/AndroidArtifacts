package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

final class zzaxd implements ResultCallback {
   // $FF: synthetic field
   private long zzaxi;
   // $FF: synthetic field
   private zzawy zzaxd;

   zzaxd(zzawy var1, long var2) {
      this.zzaxd = var1;
      this.zzaxi = var2;
      super();
   }

   // $FF: synthetic method
   public final void onResult(Result var1) {
      Status var3 = (Status)var1;
      if (!var3.isSuccess()) {
         this.zzaxd.zzc(this.zzaxi, var3.getStatusCode());
      }

   }
}
