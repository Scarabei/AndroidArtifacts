package com.google.android.gms.internal;

import android.annotation.SuppressLint;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.plus.Account;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.internal.zzh;

public final class zzcqv implements Account {
   @SuppressLint({"MissingRemoteException"})
   public final PendingResult revokeAccessAndDisconnect(GoogleApiClient var1) {
      return var1.zze(new zzcqw(this, var1));
   }

   public final void clearDefaultAccount(GoogleApiClient var1) {
      zzh var2;
      if ((var2 = Plus.zzc(var1, false)) != null) {
         var2.zzAe();
      }

   }

   public final String getAccountName(GoogleApiClient var1) {
      return Plus.zzc(var1, true).getAccountName();
   }
}
