package com.google.android.gms.wallet;

import android.os.RemoteException;
import android.support.annotation.VisibleForTesting;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api.ApiOptions.HasOptions;
import com.google.android.gms.internal.fv;
import com.google.android.gms.internal.gl;
import com.google.android.gms.internal.gu;
import com.google.android.gms.internal.ha;
import com.google.android.gms.internal.hb;
import com.google.android.gms.internal.zzbay;
import java.util.Locale;

public final class Wallet {
   private static final com.google.android.gms.common.api.Api.zzf zzajR = new com.google.android.gms.common.api.Api.zzf();
   private static final com.google.android.gms.common.api.Api.zza zzajS = new zzaa();
   public static final Api API;
   public static final Payments Payments;
   private static com.google.android.gms.wallet.wobs.zzs zzbPQ;
   private static fv zzbPR;

   /** @deprecated */
   @Deprecated
   public static void checkForPreAuthorization(GoogleApiClient var0, int var1) {
      Payments.checkForPreAuthorization(var0, var1);
   }

   /** @deprecated */
   @Deprecated
   public static void loadMaskedWallet(GoogleApiClient var0, MaskedWalletRequest var1, int var2) {
      Payments.loadMaskedWallet(var0, var1, var2);
   }

   /** @deprecated */
   @Deprecated
   public static void loadFullWallet(GoogleApiClient var0, FullWalletRequest var1, int var2) {
      Payments.loadFullWallet(var0, var1, var2);
   }

   /** @deprecated */
   @Deprecated
   public static void changeMaskedWallet(GoogleApiClient var0, String var1, String var2, int var3) {
      Payments.changeMaskedWallet(var0, var1, var2, var3);
   }

   /** @deprecated */
   @Deprecated
   public static void notifyTransactionStatus(GoogleApiClient var0, NotifyTransactionStatusRequest var1) {
      Payments.notifyTransactionStatus(var0, var1);
   }

   static {
      API = new Api("Wallet.API", zzajS, zzajR);
      Payments = new gl();
      zzbPQ = new hb();
      zzbPR = new ha();
   }

   public abstract static class zzb extends Wallet.zza {
      public zzb(GoogleApiClient var1) {
         super(var1);
      }

      // $FF: synthetic method
      protected final Result zzb(Status var1) {
         return var1;
      }
   }

   public abstract static class zza extends zzbay {
      public zza(GoogleApiClient var1) {
         super(Wallet.API, var1);
      }

      @VisibleForTesting
      protected abstract void zza(gu var1) throws RemoteException;
   }

   public static final class WalletOptions implements HasOptions {
      public final int environment;
      public final int theme;
      @VisibleForTesting
      final boolean zzbPS;

      private WalletOptions() {
         this(new Wallet.WalletOptions.Builder());
      }

      private WalletOptions(Wallet.WalletOptions.Builder var1) {
         this.environment = var1.zzbPT;
         this.theme = var1.mTheme;
         this.zzbPS = var1.zzbPU;
      }

      // $FF: synthetic method
      WalletOptions(Wallet.WalletOptions.Builder var1, zzaa var2) {
         this(var1);
      }

      // $FF: synthetic method
      WalletOptions(zzaa var1) {
         this();
      }

      public static final class Builder {
         private int zzbPT = 3;
         private int mTheme = 0;
         private boolean zzbPU = true;

         public final Wallet.WalletOptions.Builder setEnvironment(int var1) {
            if (var1 != 0 && var1 != 0 && var1 != 2 && var1 != 1 && var1 != 3) {
               String var2 = String.format(Locale.US, "Invalid environment value %d", var1);
               throw new IllegalArgumentException(var2);
            } else {
               this.zzbPT = var1;
               return this;
            }
         }

         public final Wallet.WalletOptions.Builder setTheme(int var1) {
            if (var1 != 0 && var1 != 1) {
               String var2 = String.format(Locale.US, "Invalid theme value %d", var1);
               throw new IllegalArgumentException(var2);
            } else {
               this.mTheme = var1;
               return this;
            }
         }

         /** @deprecated */
         @Deprecated
         public final Wallet.WalletOptions.Builder useGoogleWallet() {
            this.zzbPU = false;
            return this;
         }

         public final Wallet.WalletOptions build() {
            return new Wallet.WalletOptions(this, (zzaa)null);
         }
      }
   }
}
