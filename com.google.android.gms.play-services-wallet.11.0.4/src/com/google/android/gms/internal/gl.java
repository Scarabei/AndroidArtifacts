package com.google.android.gms.internal;

import android.annotation.SuppressLint;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.NotifyTransactionStatusRequest;
import com.google.android.gms.wallet.Payments;

@SuppressLint({"MissingRemoteException"})
public final class gl implements Payments {
   public final void checkForPreAuthorization(GoogleApiClient var1, int var2) {
      var1.zzd(new gm(this, var1, var2));
   }

   public final void loadMaskedWallet(GoogleApiClient var1, MaskedWalletRequest var2, int var3) {
      var1.zzd(new gn(this, var1, var2, var3));
   }

   public final void loadFullWallet(GoogleApiClient var1, FullWalletRequest var2, int var3) {
      var1.zzd(new go(this, var1, var2, var3));
   }

   public final void changeMaskedWallet(GoogleApiClient var1, String var2, String var3, int var4) {
      var1.zzd(new gp(this, var1, var2, var3, var4));
   }

   public final void notifyTransactionStatus(GoogleApiClient var1, NotifyTransactionStatusRequest var2) {
      var1.zzd(new gq(this, var1, var2));
   }

   public final void isNewUser(GoogleApiClient var1, int var2) {
      var1.zzd(new gr(this, var1, var2));
   }

   public final PendingResult isReadyToPay(GoogleApiClient var1) {
      return var1.zzd(new gs(this, var1));
   }

   public final PendingResult isReadyToPay(GoogleApiClient var1, IsReadyToPayRequest var2) {
      return var1.zzd(new gt(this, var1, var2));
   }
}
