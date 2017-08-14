package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.NotifyTransactionStatusRequest;
import com.google.android.gms.wallet.Wallet;

final class gq extends Wallet.zzb {
   // $FF: synthetic field
   private NotifyTransactionStatusRequest zzbQD;

   gq(gl var1, GoogleApiClient var2, NotifyTransactionStatusRequest var3) {
      this.zzbQD = var3;
      super(var2);
   }

   protected final void zza(gu var1) {
      var1.zza(this.zzbQD);
      this.setResult(Status.zzaBm);
   }
}
