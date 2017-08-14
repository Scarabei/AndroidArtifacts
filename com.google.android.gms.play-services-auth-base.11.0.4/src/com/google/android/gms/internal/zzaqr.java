package com.google.android.gms.internal;

import android.accounts.Account;
import com.google.android.gms.common.api.Status;

final class zzaqr extends zzaqu {
   // $FF: synthetic field
   private zzaqq zzakC;

   zzaqr(zzaqq var1) {
      this.zzakC = var1;
      super();
   }

   public final void zzd(Account var1) {
      this.zzakC.setResult(new zzaqv(var1 != null ? Status.zzaBm : zzaqn.zzmq(), var1));
   }
}
