package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.Wallet;

final class gm extends Wallet.zzb {
   // $FF: synthetic field
   private int val$requestCode;

   gm(gl var1, GoogleApiClient var2, int var3) {
      this.val$requestCode = var3;
      super(var2);
   }

   protected final void zza(gu var1) {
      var1.zzbP(this.val$requestCode);
      this.setResult(Status.zzaBm);
   }
}
