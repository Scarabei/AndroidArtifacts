package com.google.android.gms.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;
import android.view.View;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.internal.zzy;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.zzbr;
import com.google.android.gms.common.internal.zzq;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class zzbej extends zzctp implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
   private static Api.zza zzaEV;
   private final Context mContext;
   private final Handler mHandler;
   private final Api.zza zzaAx;
   private final boolean zzaEW;
   private Set zzame;
   private zzq zzaCA;
   private zzctk zzaDh;
   private zzbel zzaEX;

   @WorkerThread
   public zzbej(Context var1, Handler var2) {
      this.mContext = var1;
      this.mHandler = var2;
      this.zzaAx = zzaEV;
      this.zzaEW = true;
   }

   @WorkerThread
   public zzbej(Context var1, Handler var2, @NonNull zzq var3, Api.zza var4) {
      this.mContext = var1;
      this.mHandler = var2;
      this.zzaCA = (zzq)zzbo.zzb(var3, "ClientSettings must not be null");
      this.zzame = var3.zzrn();
      this.zzaAx = var4;
      this.zzaEW = false;
   }

   @WorkerThread
   public final void zza(zzbel var1) {
      if (this.zzaDh != null) {
         this.zzaDh.disconnect();
      }

      if (this.zzaEW) {
         GoogleSignInOptions var2 = zzy.zzaj(this.mContext).zzmO();
         this.zzame = var2 == null ? new HashSet() : new HashSet(var2.zzmA());
         this.zzaCA = new zzq((Account)null, this.zzame, (Map)null, 0, (View)null, (String)null, (String)null, zzctl.zzbCM);
      }

      this.zzaCA.zzc(System.identityHashCode(this));
      this.zzaDh = (zzctk)this.zzaAx.zza(this.mContext, this.mHandler.getLooper(), this.zzaCA, this.zzaCA.zzrt(), this, this);
      this.zzaEX = var1;
      this.zzaDh.connect();
   }

   public final zzctk zzqy() {
      return this.zzaDh;
   }

   public final void zzqI() {
      if (this.zzaDh != null) {
         this.zzaDh.disconnect();
      }

   }

   @WorkerThread
   public final void onConnected(@Nullable Bundle var1) {
      this.zzaDh.zza(this);
   }

   @WorkerThread
   public final void onConnectionSuspended(int var1) {
      this.zzaDh.disconnect();
   }

   @WorkerThread
   public final void onConnectionFailed(@NonNull ConnectionResult var1) {
      this.zzaEX.zzh(var1);
   }

   @BinderThread
   public final void zzb(zzctx var1) {
      this.mHandler.post(new zzbek(this, var1));
   }

   @WorkerThread
   private final void zzc(zzctx var1) {
      ConnectionResult var2;
      if ((var2 = var1.zzpz()).isSuccess()) {
         zzbr var3;
         ConnectionResult var4;
         if (!(var4 = (var3 = var1.zzAx()).zzpz()).isSuccess()) {
            String var5 = String.valueOf(var4);
            Log.wtf("SignInCoordinator", (new StringBuilder(48 + String.valueOf(var5).length())).append("Sign-in succeeded with resolve account failure: ").append(var5).toString(), new Exception());
            this.zzaEX.zzh(var4);
            this.zzaDh.disconnect();
            return;
         }

         this.zzaEX.zzb(var3.zzrH(), this.zzame);
      } else {
         this.zzaEX.zzh(var2);
      }

      this.zzaDh.disconnect();
   }

   // $FF: synthetic method
   static void zza(zzbej var0, zzctx var1) {
      var0.zzc(var1);
   }

   static {
      zzaEV = zzctg.zzajS;
   }
}
