package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.Wallet;

final class gp extends Wallet.zzb {
   // $FF: synthetic field
   private String zzbQB;
   // $FF: synthetic field
   private String zzbQC;
   // $FF: synthetic field
   private int val$requestCode;

   gp(gl var1, GoogleApiClient var2, String var3, String var4, int var5) {
      this.zzbQB = var3;
      this.zzbQC = var4;
      this.val$requestCode = var5;
      super(var2);
   }

   protected final void zza(gu var1) {
      var1.zzc(this.zzbQB, this.zzbQC, this.val$requestCode);
      this.setResult(Status.zzaBm);
   }
}
