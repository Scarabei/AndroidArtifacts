package com.google.android.gms.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.internal.zzz;
import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.NotifyTransactionStatusRequest;

public final class gu extends zzz {
   private final Context mContext;
   private final int zzbPT;
   private final String zzakh;
   private final int mTheme;
   private final boolean zzbPU;

   public gu(Context var1, Looper var2, zzq var3, ConnectionCallbacks var4, OnConnectionFailedListener var5, int var6, int var7, boolean var8) {
      super(var1, var2, 4, var3, var4, var5);
      this.mContext = var1;
      this.zzbPT = var6;
      this.zzakh = var3.getAccountName();
      this.mTheme = var7;
      this.zzbPU = var8;
   }

   public final boolean zzrg() {
      return true;
   }

   protected final String zzdb() {
      return "com.google.android.gms.wallet.service.BIND";
   }

   protected final String zzdc() {
      return "com.google.android.gms.wallet.internal.IOwService";
   }

   public final void zzbP(int var1) {
      Bundle var2 = this.zzDT();
      gy var3 = new gy(this.mContext, var1);

      try {
         ((gf)this.zzrf()).zza((Bundle)var2, (gj)var3);
      } catch (RemoteException var5) {
         Log.e("WalletClientImpl", "RemoteException during checkForPreAuthorization", var5);
         var3.zza(8, false, Bundle.EMPTY);
      }
   }

   public final void zzc(String var1, String var2, int var3) {
      Bundle var4 = this.zzDT();
      gy var5 = new gy(this.mContext, var3);

      try {
         ((gf)this.zzrf()).zza(var1, var2, var4, var5);
      } catch (RemoteException var7) {
         Log.e("WalletClientImpl", "RemoteException changing masked wallet", var7);
         var5.zza(8, (MaskedWallet)null, Bundle.EMPTY);
      }
   }

   public final void zza(MaskedWalletRequest var1, int var2) {
      Bundle var3 = this.zzDT();
      gy var4 = new gy(this.mContext, var2);

      try {
         ((gf)this.zzrf()).zza((MaskedWalletRequest)var1, var3, var4);
      } catch (RemoteException var6) {
         Log.e("WalletClientImpl", "RemoteException getting masked wallet", var6);
         var4.zza(8, (MaskedWallet)null, Bundle.EMPTY);
      }
   }

   public final void zza(FullWalletRequest var1, int var2) {
      gy var3 = new gy(this.mContext, var2);
      Bundle var4 = this.zzDT();

      try {
         ((gf)this.zzrf()).zza((FullWalletRequest)var1, var4, var3);
      } catch (RemoteException var6) {
         Log.e("WalletClientImpl", "RemoteException getting full wallet", var6);
         var3.zza(8, (FullWallet)null, Bundle.EMPTY);
      }
   }

   public final void zza(NotifyTransactionStatusRequest var1) {
      Bundle var2 = this.zzDT();

      try {
         ((gf)this.zzrf()).zza(var1, var2);
      } catch (RemoteException var3) {
         ;
      }
   }

   public final void zzbQ(int var1) {
      Bundle var2 = this.zzDT();
      gy var3 = new gy(this.mContext, var1);

      try {
         ((gf)this.zzrf()).zzb(var2, var3);
      } catch (RemoteException var5) {
         Log.e("WalletClientImpl", "RemoteException during isNewUser", var5);
         var3.zzb(8, false, Bundle.EMPTY);
      }
   }

   public final void zza(IsReadyToPayRequest var1, zzbaz var2) {
      gx var3 = new gx(var2);
      Bundle var4 = this.zzDT();

      try {
         ((gf)this.zzrf()).zza((IsReadyToPayRequest)var1, var4, var3);
      } catch (RemoteException var6) {
         Log.e("WalletClientImpl", "RemoteException during isReadyToPay", var6);
         var3.zza(Status.zzaBo, false, Bundle.EMPTY);
      }
   }

   private final Bundle zzDT() {
      int var10000 = this.zzbPT;
      String var10001 = this.mContext.getPackageName();
      boolean var5 = this.zzbPU;
      int var4 = this.mTheme;
      String var3 = this.zzakh;
      String var2 = var10001;
      int var1 = var10000;
      Bundle var6;
      (var6 = new Bundle()).putInt("com.google.android.gms.wallet.EXTRA_ENVIRONMENT", var1);
      var6.putBoolean("com.google.android.gms.wallet.EXTRA_USING_ANDROID_PAY_BRAND", var5);
      var6.putString("androidPackageName", var2);
      if (!TextUtils.isEmpty(var3)) {
         var6.putParcelable("com.google.android.gms.wallet.EXTRA_BUYER_ACCOUNT", new Account(var3, "com.google"));
      }

      var6.putInt("com.google.android.gms.wallet.EXTRA_THEME", var4);
      return var6;
   }

   // $FF: synthetic method
   protected final IInterface zzd(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (IInterface)((var3 = var1.queryLocalInterface("com.google.android.gms.wallet.internal.IOwService")) instanceof gf ? (gf)var3 : new gg(var1));
      }
   }
}
