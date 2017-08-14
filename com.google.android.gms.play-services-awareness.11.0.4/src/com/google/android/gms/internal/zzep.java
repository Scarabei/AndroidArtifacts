package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;

final class zzep implements ResultCallback {
   // $FF: synthetic field
   private ResultCallback zzrK;
   // $FF: synthetic field
   private zzen zzrL;

   zzep(zzen var1, ResultCallback var2) {
      this.zzrL = var1;
      this.zzrK = var2;
      super();
   }

   public final void onResult(@NonNull Result var1) {
      this.zzrK.onResult(this.zzrL.zza(var1));
   }
}
