package com.google.android.gms.plus;

import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

/** @deprecated */
@Deprecated
public interface Account {
   /** @deprecated */
   @Deprecated
   PendingResult revokeAccessAndDisconnect(GoogleApiClient var1);

   /** @deprecated */
   @Deprecated
   void clearDefaultAccount(GoogleApiClient var1);

   /** @deprecated */
   @Deprecated
   @RequiresPermission("android.permission.GET_ACCOUNTS")
   String getAccountName(GoogleApiClient var1);
}
