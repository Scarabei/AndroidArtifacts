package com.google.android.gms.internal;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.MaskedWallet;
import java.lang.ref.WeakReference;

final class gy extends gw {
   private final WeakReference zzaka;
   private final int zzaBk;

   public gy(Context var1, int var2) {
      super((gv)null);
      this.zzaka = new WeakReference((Activity)var1);
      this.zzaBk = var2;
   }

   public final void zza(int var1, MaskedWallet var2, Bundle var3) {
      Activity var4;
      if ((var4 = (Activity)this.zzaka.get()) == null) {
         Log.d("WalletClientImpl", "Ignoring onMaskedWalletLoaded, Activity has gone");
      } else {
         PendingIntent var5 = null;
         if (var3 != null) {
            var5 = (PendingIntent)var3.getParcelable("com.google.android.gms.wallet.EXTRA_PENDING_INTENT");
         }

         ConnectionResult var6;
         if ((var6 = new ConnectionResult(var1, var5)).hasResolution()) {
            try {
               var6.startResolutionForResult(var4, this.zzaBk);
            } catch (SendIntentException var11) {
               Log.w("WalletClientImpl", "Exception starting pending intent", var11);
            }
         } else {
            Intent var7 = new Intent();
            byte var8;
            if (var6.isSuccess()) {
               var8 = -1;
               var7.putExtra("com.google.android.gms.wallet.EXTRA_MASKED_WALLET", var2);
            } else {
               if (var1 == 408) {
                  var8 = 0;
               } else {
                  var8 = 1;
               }

               var7.putExtra("com.google.android.gms.wallet.EXTRA_ERROR_CODE", var1);
            }

            PendingIntent var9;
            if ((var9 = var4.createPendingResult(this.zzaBk, var7, 1073741824)) == null) {
               Log.w("WalletClientImpl", "Null pending result returned for onMaskedWalletLoaded");
            } else {
               try {
                  var9.send(var8);
               } catch (CanceledException var12) {
                  Log.w("WalletClientImpl", "Exception setting pending result", var12);
               }
            }
         }
      }
   }

   public final void zza(int var1, FullWallet var2, Bundle var3) {
      Activity var4;
      if ((var4 = (Activity)this.zzaka.get()) == null) {
         Log.d("WalletClientImpl", "Ignoring onFullWalletLoaded, Activity has gone");
      } else {
         PendingIntent var5 = null;
         if (var3 != null) {
            var5 = (PendingIntent)var3.getParcelable("com.google.android.gms.wallet.EXTRA_PENDING_INTENT");
         }

         ConnectionResult var6;
         if ((var6 = new ConnectionResult(var1, var5)).hasResolution()) {
            try {
               var6.startResolutionForResult(var4, this.zzaBk);
            } catch (SendIntentException var11) {
               Log.w("WalletClientImpl", "Exception starting pending intent", var11);
            }
         } else {
            Intent var7 = new Intent();
            byte var8;
            if (var6.isSuccess()) {
               var8 = -1;
               var7.putExtra("com.google.android.gms.wallet.EXTRA_FULL_WALLET", var2);
            } else {
               if (var1 == 408) {
                  var8 = 0;
               } else {
                  var8 = 1;
               }

               var7.putExtra("com.google.android.gms.wallet.EXTRA_ERROR_CODE", var1);
            }

            PendingIntent var9;
            if ((var9 = var4.createPendingResult(this.zzaBk, var7, 1073741824)) == null) {
               Log.w("WalletClientImpl", "Null pending result returned for onFullWalletLoaded");
            } else {
               try {
                  var9.send(var8);
               } catch (CanceledException var12) {
                  Log.w("WalletClientImpl", "Exception setting pending result", var12);
               }
            }
         }
      }
   }

   public final void zza(int var1, boolean var2, Bundle var3) {
      Activity var4;
      if ((var4 = (Activity)this.zzaka.get()) == null) {
         Log.d("WalletClientImpl", "Ignoring onPreAuthorizationDetermined, Activity has gone");
      } else {
         Intent var5;
         (var5 = new Intent()).putExtra("com.google.android.gm.wallet.EXTRA_IS_USER_PREAUTHORIZED", var2);
         PendingIntent var6;
         if ((var6 = var4.createPendingResult(this.zzaBk, var5, 1073741824)) == null) {
            Log.w("WalletClientImpl", "Null pending result returned for onPreAuthorizationDetermined");
         } else {
            try {
               var6.send(-1);
            } catch (CanceledException var8) {
               Log.w("WalletClientImpl", "Exception setting pending result", var8);
            }
         }
      }
   }

   public final void zzg(int var1, Bundle var2) {
      zzbo.zzb(var2, "Bundle should not be null");
      Activity var3;
      if ((var3 = (Activity)this.zzaka.get()) == null) {
         Log.d("WalletClientImpl", "Ignoring onWalletObjectsCreated, Activity has gone");
      } else {
         PendingIntent var4 = (PendingIntent)var2.getParcelable("com.google.android.gms.wallet.EXTRA_PENDING_INTENT");
         ConnectionResult var5;
         if ((var5 = new ConnectionResult(var1, var4)).hasResolution()) {
            try {
               var5.startResolutionForResult(var3, this.zzaBk);
            } catch (SendIntentException var9) {
               Log.w("WalletClientImpl", "Exception starting pending intent", var9);
            }
         } else {
            String var6 = String.valueOf(var5);
            Log.e("WalletClientImpl", (new StringBuilder(75 + String.valueOf(var6).length())).append("Create Wallet Objects confirmation UI will not be shown connection result: ").append(var6).toString());
            Intent var11;
            (var11 = new Intent()).putExtra("com.google.android.gms.wallet.EXTRA_ERROR_CODE", 413);
            PendingIntent var7;
            if ((var7 = var3.createPendingResult(this.zzaBk, var11, 1073741824)) == null) {
               Log.w("WalletClientImpl", "Null pending result returned for onWalletObjectsCreated");
            } else {
               try {
                  var7.send(1);
               } catch (CanceledException var10) {
                  Log.w("WalletClientImpl", "Exception setting pending result", var10);
               }
            }
         }
      }
   }

   public final void zzb(int var1, boolean var2, Bundle var3) {
      Activity var4;
      if ((var4 = (Activity)this.zzaka.get()) == null) {
         Log.d("WalletClientImpl", "Ignoring onIsNewUserDetermined, Activity has gone");
      } else {
         Intent var5;
         (var5 = new Intent()).putExtra("com.google.android.gms.wallet.EXTRA_IS_NEW_USER", var2);
         PendingIntent var6;
         if ((var6 = var4.createPendingResult(this.zzaBk, var5, 1073741824)) == null) {
            Log.w("WalletClientImpl", "Null pending result returned for onIsNewUserDetermined");
         } else {
            try {
               var6.send(-1);
            } catch (CanceledException var8) {
               Log.w("WalletClientImpl", "Exception setting pending result", var8);
            }
         }
      }
   }

   public final void zza(Status var1, boolean var2, Bundle var3) {
      Activity var4;
      if ((var4 = (Activity)this.zzaka.get()) == null) {
         Log.d("WalletClientImpl", "Ignoring onIsReadyToPayDetermined, Activity has gone");
      } else {
         Intent var5;
         (var5 = new Intent()).putExtra("com.google.android.gms.wallet.EXTRA_IS_READY_TO_PAY", var2);
         PendingIntent var6;
         if ((var6 = var4.createPendingResult(this.zzaBk, var5, 1073741824)) == null) {
            Log.w("WalletClientImpl", "Null pending result returned for onIsReadyToPayDetermined");
         } else {
            try {
               var6.send(-1);
            } catch (CanceledException var8) {
               Log.w("WalletClientImpl", "Exception setting pending result in onIsReadyToPayDetermined", var8);
            }
         }
      }
   }
}
