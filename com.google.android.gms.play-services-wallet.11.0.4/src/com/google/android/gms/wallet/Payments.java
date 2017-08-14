package com.google.android.gms.wallet;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

public interface Payments {
   /** @deprecated */
   @Deprecated
   void checkForPreAuthorization(GoogleApiClient var1, int var2);

   void loadMaskedWallet(GoogleApiClient var1, MaskedWalletRequest var2, int var3);

   void loadFullWallet(GoogleApiClient var1, FullWalletRequest var2, int var3);

   void changeMaskedWallet(GoogleApiClient var1, String var2, String var3, int var4);

   /** @deprecated */
   @Deprecated
   void notifyTransactionStatus(GoogleApiClient var1, NotifyTransactionStatusRequest var2);

   /** @deprecated */
   @Deprecated
   void isNewUser(GoogleApiClient var1, int var2);

   /** @deprecated */
   @Deprecated
   PendingResult isReadyToPay(GoogleApiClient var1);

   PendingResult isReadyToPay(GoogleApiClient var1, IsReadyToPayRequest var2);
}
