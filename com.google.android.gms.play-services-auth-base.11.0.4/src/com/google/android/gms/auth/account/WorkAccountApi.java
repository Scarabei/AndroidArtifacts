package com.google.android.gms.auth.account;

import android.accounts.Account;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;

public interface WorkAccountApi {
   void setWorkAuthenticatorEnabled(GoogleApiClient var1, boolean var2);

   PendingResult addWorkAccount(GoogleApiClient var1, String var2);

   PendingResult removeWorkAccount(GoogleApiClient var1, Account var2);

   public interface AddAccountResult extends Result {
      Account getAccount();
   }
}
