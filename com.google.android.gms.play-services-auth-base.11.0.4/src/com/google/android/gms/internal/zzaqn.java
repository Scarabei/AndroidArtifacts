package com.google.android.gms.internal;

import android.accounts.Account;
import com.google.android.gms.auth.account.WorkAccount;
import com.google.android.gms.auth.account.WorkAccountApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

public final class zzaqn implements WorkAccountApi {
   private static final Status zzakA = new Status(13);

   public final void setWorkAuthenticatorEnabled(GoogleApiClient var1, boolean var2) {
      var1.zze(new zzaqo(this, WorkAccount.API, var1, var2));
   }

   public final PendingResult addWorkAccount(GoogleApiClient var1, String var2) {
      return var1.zze(new zzaqq(this, WorkAccount.API, var1, var2));
   }

   public final PendingResult removeWorkAccount(GoogleApiClient var1, Account var2) {
      return var1.zze(new zzaqs(this, WorkAccount.API, var1, var2));
   }

   // $FF: synthetic method
   static Status zzmq() {
      return zzakA;
   }
}
