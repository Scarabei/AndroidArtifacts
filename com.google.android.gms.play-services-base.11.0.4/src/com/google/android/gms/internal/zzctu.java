package com.google.android.gms.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.internal.zzy;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzal;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.common.internal.zzm;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.internal.zzz;

public final class zzctu extends zzz implements zzctk {
   private final boolean zzbCT;
   private final zzq zzaCA;
   private final Bundle zzbCL;
   private Integer zzaHn;

   public zzctu(Context var1, Looper var2, boolean var3, zzq var4, Bundle var5, GoogleApiClient.ConnectionCallbacks var6, GoogleApiClient.OnConnectionFailedListener var7) {
      super(var1, var2, 44, var4, var6, var7);
      this.zzbCT = var3;
      this.zzaCA = var4;
      this.zzbCL = var5;
      this.zzaHn = var4.zzru();
   }

   public zzctu(Context var1, Looper var2, boolean var3, zzq var4, zzctl var5, GoogleApiClient.ConnectionCallbacks var6, GoogleApiClient.OnConnectionFailedListener var7) {
      this(var1, var2, true, var4, zza(var4), var6, var7);
   }

   public final boolean zzmv() {
      return this.zzbCT;
   }

   public final void zza(zzal var1, boolean var2) {
      try {
         ((zzcts)this.zzrf()).zza(var1, this.zzaHn.intValue(), var2);
      } catch (RemoteException var3) {
         Log.w("SignInClientImpl", "Remote service probably died when saveDefaultAccount is called");
      }
   }

   public final void zzAq() {
      try {
         ((zzcts)this.zzrf()).zzbv(this.zzaHn.intValue());
      } catch (RemoteException var1) {
         Log.w("SignInClientImpl", "Remote service probably died when clearAccountFromSessionStore is called");
      }
   }

   public final void zza(zzctq var1) {
      zzbo.zzb(var1, "Expecting a valid ISignInCallbacks");

      try {
         Account var4 = this.zzaCA.zzrl();
         GoogleSignInAccount var5 = null;
         if ("<<default account>>".equals(var4.name)) {
            var5 = zzy.zzaj(this.getContext()).zzmN();
         }

         zzbp var2 = new zzbp(var4, this.zzaHn.intValue(), var5);
         ((zzcts)this.zzrf()).zza(new zzctv(var2), var1);
      } catch (RemoteException var7) {
         Log.w("SignInClientImpl", "Remote service probably died when signIn is called");

         try {
            var1.zzb(new zzctx(8));
         } catch (RemoteException var6) {
            Log.wtf("SignInClientImpl", "ISignInCallbacks#onSignInComplete should be executed from the same process, unexpected RemoteException.", var7);
         }
      }
   }

   protected final String zzdb() {
      return "com.google.android.gms.signin.service.START";
   }

   protected final String zzdc() {
      return "com.google.android.gms.signin.internal.ISignInService";
   }

   protected final Bundle zzmo() {
      String var1 = this.zzaCA.zzrq();
      if (!this.getContext().getPackageName().equals(var1)) {
         this.zzbCL.putString("com.google.android.gms.signin.internal.realClientPackageName", this.zzaCA.zzrq());
      }

      return this.zzbCL;
   }

   public final void connect() {
      this.zza((zzj)(new zzm(this)));
   }

   public static Bundle zza(zzq var0) {
      zzctl var1 = var0.zzrt();
      Integer var2 = var0.zzru();
      Bundle var3;
      (var3 = new Bundle()).putParcelable("com.google.android.gms.signin.internal.clientRequestedAccount", var0.getAccount());
      if (var2 != null) {
         var3.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", var2.intValue());
      }

      if (var1 != null) {
         var3.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", var1.zzAr());
         var3.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", var1.isIdTokenRequested());
         var3.putString("com.google.android.gms.signin.internal.serverClientId", var1.getServerClientId());
         var3.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
         var3.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", var1.zzAs());
         var3.putString("com.google.android.gms.signin.internal.hostedDomain", var1.zzAt());
         var3.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", var1.zzAu());
         if (var1.zzAv() != null) {
            var3.putLong("com.google.android.gms.signin.internal.authApiSignInModuleVersion", var1.zzAv().longValue());
         }

         if (var1.zzAw() != null) {
            var3.putLong("com.google.android.gms.signin.internal.realClientLibraryVersion", var1.zzAw().longValue());
         }
      }

      return var3;
   }

   // $FF: synthetic method
   protected final IInterface zzd(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (IInterface)((var3 = var1.queryLocalInterface("com.google.android.gms.signin.internal.ISignInService")) instanceof zzcts ? (zzcts)var3 : new zzctt(var1));
      }
   }
}
