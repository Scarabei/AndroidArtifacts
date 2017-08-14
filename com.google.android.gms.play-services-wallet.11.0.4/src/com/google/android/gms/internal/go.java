package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.Wallet;

final class go extends Wallet.zzb {
   // $FF: synthetic field
   private FullWalletRequest zzbQA;
   // $FF: synthetic field
   private int val$requestCode;

   go(gl var1, GoogleApiClient var2, FullWalletRequest var3, int var4) {
      this.zzbQA = var3;
      this.val$requestCode = var4;
      super(var2);
   }

   protected final void zza(gu var1) {
      var1.zza(this.zzbQA, this.val$requestCode);
      this.setResult(Status.zzaBm);
   }
}
