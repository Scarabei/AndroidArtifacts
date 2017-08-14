package com.google.android.gms.internal;

import android.accounts.Account;
import android.app.Activity;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.internal.zzz;
import com.google.android.gms.identity.intents.UserAddressRequest;

public final class zzcbe extends zzz {
   private Activity mActivity;
   private zzcbf zzbgD;
   private final String zzakh;
   private final int mTheme;

   public zzcbe(Activity var1, Looper var2, zzq var3, int var4, ConnectionCallbacks var5, OnConnectionFailedListener var6) {
      super(var1, var2, 12, var3, var5, var6);
      this.zzakh = var3.getAccountName();
      this.mActivity = var1;
      this.mTheme = var4;
   }

   public final boolean zzrg() {
      return true;
   }

   protected final String zzdb() {
      return "com.google.android.gms.identity.service.BIND";
   }

   protected final String zzdc() {
      return "com.google.android.gms.identity.intents.internal.IAddressService";
   }

   public final void disconnect() {
      super.disconnect();
      if (this.zzbgD != null) {
         zzcbf.zza(this.zzbgD, (Activity)null);
         this.zzbgD = null;
      }

   }

   public final void zza(UserAddressRequest var1, int var2) {
      super.zzre();
      this.zzbgD = new zzcbf(var2, this.mActivity);

      try {
         Bundle var3 = new Bundle();
         String var6 = this.getContext().getPackageName();
         var3.putString("com.google.android.gms.identity.intents.EXTRA_CALLING_PACKAGE_NAME", var6);
         if (!TextUtils.isEmpty(this.zzakh)) {
            var3.putParcelable("com.google.android.gms.identity.intents.EXTRA_ACCOUNT", new Account(this.zzakh, "com.google"));
         }

         var3.putInt("com.google.android.gms.identity.intents.EXTRA_THEME", this.mTheme);
         ((zzcbi)super.zzrf()).zza(this.zzbgD, var1, var3);
      } catch (RemoteException var5) {
         Log.e("AddressClientImpl", "Exception requesting user address", var5);
         Bundle var4;
         (var4 = new Bundle()).putInt("com.google.android.gms.identity.intents.EXTRA_ERROR_CODE", 555);
         this.zzbgD.zze(1, var4);
      }
   }

   // $FF: synthetic method
   protected final IInterface zzd(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (IInterface)((var3 = var1.queryLocalInterface("com.google.android.gms.identity.intents.internal.IAddressService")) instanceof zzcbi ? (zzcbi)var3 : new zzcbj(var1));
      }
   }
}
