package com.google.android.gms.internal;

import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.Wallet;

final class gs extends Wallet.zza {
   gs(gl var1, GoogleApiClient var2) {
      super(var2);
   }

   protected final void zza(gu var1) {
      var1.zza(IsReadyToPayRequest.newBuilder().build(), this);
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return new BooleanResult(var1, false);
   }
}
