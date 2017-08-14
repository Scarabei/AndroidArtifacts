package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.Wallet;

final class gn extends Wallet.zzb {
   // $FF: synthetic field
   private MaskedWalletRequest zzbQz;
   // $FF: synthetic field
   private int val$requestCode;

   gn(gl var1, GoogleApiClient var2, MaskedWalletRequest var3, int var4) {
      this.zzbQz = var3;
      this.val$requestCode = var4;
      super(var2);
   }

   protected final void zza(gu var1) {
      var1.zza(this.zzbQz, this.val$requestCode);
      this.setResult(Status.zzaBm);
   }
}
