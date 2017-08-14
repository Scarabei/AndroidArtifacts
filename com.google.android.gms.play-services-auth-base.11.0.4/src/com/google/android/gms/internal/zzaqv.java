package com.google.android.gms.internal;

import android.accounts.Account;
import com.google.android.gms.auth.account.WorkAccountApi;
import com.google.android.gms.common.api.Status;

final class zzaqv implements WorkAccountApi.AddAccountResult {
   private final Status mStatus;
   private final Account zzajb;

   public zzaqv(Status var1, Account var2) {
      this.mStatus = var1;
      this.zzajb = var2;
   }

   public final Status getStatus() {
      return this.mStatus;
   }

   public final Account getAccount() {
      return this.zzajb;
   }
}
