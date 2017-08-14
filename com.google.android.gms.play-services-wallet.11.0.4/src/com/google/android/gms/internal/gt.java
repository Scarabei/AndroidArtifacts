package com.google.android.gms.internal;

import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.Wallet;

final class gt extends Wallet.zza {
   // $FF: synthetic field
   private IsReadyToPayRequest zzbQE;

   gt(gl var1, GoogleApiClient var2, IsReadyToPayRequest var3) {
      this.zzbQE = var3;
      super(var2);
   }

   protected final void zza(gu var1) {
      var1.zza(this.zzbQE, this);
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return new BooleanResult(var1, false);
   }
}
